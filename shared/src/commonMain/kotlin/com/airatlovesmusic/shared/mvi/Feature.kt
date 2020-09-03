package com.airatlovesmusic.shared.mvi

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

open class Feature<out State, Cmd, Msg: Any, out News> (
    initialState: State,
    private val initialMessages: Set<Msg> = setOf(),
    private val reducer: (Msg, State) -> Update<State, Cmd>,
    private val commandHandler: suspend CoroutineScope.(Cmd) -> Flow<SideEffect<Msg, News>>,
    private val ioDispatcher: CoroutineDispatcher,
    mainDispatcher: CoroutineDispatcher,
    bootstrapper: Set<Flow<Msg>> = setOf(),
    stateListener: (State) -> Unit,
    newsListener: (News) -> Unit
) {

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(mainDispatcher + job)

    private val stateChannel = BroadcastChannel<State>(Channel.CONFLATED)
    private val msgChannel= BroadcastChannel<Msg>(Channel.BUFFERED)
    private val commandChannel = BroadcastChannel<Cmd>(Channel.BUFFERED)
    private val newsChannel = BroadcastChannel<News>(Channel.BUFFERED)

    init {
        stateChannel.offer(initialState)
        launchCmdChannel()
        bootstrapper.forEach {
            coroutineScope.launch() {
                it.collect {
                    accept(it)
                }
            }
        }
        coroutineScope.launch {
            stateChannel.asFlow()
                .collect { stateListener.invoke(it) }
        }
        coroutineScope.launch {
            newsChannel.asFlow()
                .collect { newsListener.invoke(it) }
        }
    }

    private fun launchCmdChannel() {
        coroutineScope.launch(ioDispatcher) {
            commandChannel.asFlow()
                .onStart { launchMsgChannel() }
                .collect { cmd ->
                    commandHandler.invoke(coroutineScope, cmd).collect { (msg, news) ->
                        msgChannel.waitingOffer(this, msg)
                        newsChannel.waitingOffer(this, news)
                    }
                }
        }
    }

    private fun launchMsgChannel() {
        coroutineScope.launch(ioDispatcher) {
            flow {
                initialMessages.forEach { emit(it) }
                emitAll(msgChannel.asFlow())
            }.collect { msg ->
                val (state, cmd) = reducer.invoke(msg, stateChannel.asFlow().first())
                stateChannel.waitingOffer(this, state)
                commandChannel.waitingOffer(this, cmd)
            }
        }
    }

    fun accept(msg: Any) {
        msgChannel.waitingOffer(coroutineScope, msg as? Msg)
    }

    open fun dispose() {
        job.complete()
        newsChannel.close()
        msgChannel.close()
        commandChannel.close()
    }

}

fun <T> BroadcastChannel<T>.safeOffer(item: T?) {
    item?.let {
        if (!isClosedForSend) offer(it)
    }
}

fun <T> BroadcastChannel<T>.waitingOffer(
    coroutineScope: CoroutineScope,
    item: T?,
    cancellationTime: Long = 500L
) {
    coroutineScope.launch {
        var delayTime = 0L
        while (isClosedForSend) {
            delay(50)
            delayTime+=50L
            if (delayTime == cancellationTime) cancel()
        }
    }.invokeOnCompletion {
        safeOffer(item)
    }
}