package com.airatlovesmusic.shared.mvi

import com.badoo.reaktive.base.CompleteCallback
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.disposable.CompositeDisposable
import com.badoo.reaktive.disposable.plusAssign
import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.observable.*
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.subject.behavior.BehaviorSubject
import com.badoo.reaktive.subject.publish.PublishSubject
import com.badoo.reaktive.utils.ensureNeverFrozen

class Feature<out State, Cmd, Msg: Any, out News> (
    initialState: State,
    initialMessages: Set<Msg> = setOf(),
    private val reducer: (Msg, State) -> Update<State, Cmd>,
    private val commandHandler: (Cmd) -> Observable<SideEffect<Msg, News>>,
    stateListener: (State) -> Unit,
    newsListener: (News) -> Unit
): DisposableScope by DisposableScope() {

    private val stateSubject = BehaviorSubject(initialState).ensureNeverFrozen().scope(CompleteCallback::onComplete)
    private val newsSubject = PublishSubject<News>().scope(CompleteCallback::onComplete)
    private val msgSubject = PublishSubject<Msg>().scope(CompleteCallback::onComplete)
    private val cmdSubject = PublishSubject<Cmd>().scope(CompleteCallback::onComplete)

    init {
        cmdSubject.subscribe { cmd ->
            commandHandler.invoke(cmd)
                .observeOn(mainScheduler)
                .subscribeScoped(
                    isThreadLocal = true,
                    onNext = { (msg, news) ->
                        if (msg != null) msgSubject.onNext(msg)
                        if (news != null) newsSubject.onNext(news)
                    }
                )
        }
        stateSubject.subscribe { stateListener.invoke(it) }
        newsSubject.subscribe { newsListener.invoke(it) }
        msgSubject
            .subscribeScoped(
                isThreadLocal = true,
                onNext = { msg ->
                    val (state, cmd) = reducer.invoke(msg, stateSubject.value)
                    if (state != null) stateSubject.onNext(state)
                    if (cmd != null) cmdSubject.onNext(cmd)
                }
            )
        initialMessages.forEach { msgSubject.onNext(it) }
    }

    fun getCurrentState(): State = stateSubject.value

    fun accept(msg: Any) {
        msgSubject.onNext(msg as Msg)
    }

}