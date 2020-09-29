package com.airatlovesmusic.shared.presentation

import com.airatlovesmusic.shared.data.repository.AuthRepository
import com.airatlovesmusic.shared.mvi.Feature
import com.airatlovesmusic.shared.mvi.SideEffect
import com.airatlovesmusic.shared.mvi.Update
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginFeatureComponent: KoinComponent {

    private val authRepository by inject<AuthRepository>()

    private var stateListener: (State) -> Unit = {}
    private var newsListener: (News) -> Unit = {}

    private val feature = Feature<State, Cmd, Msg, News>(
        initialState = State(),
        reducer = { msg, state ->
            when (msg) {
                is Msg.Login -> Update(cmd = Cmd.Login(msg.username, msg.password), state = state.copy(isLoading = true))
                is Msg.LoginSuccess, is Msg.LoginFailure -> Update<State, Cmd>(state = state.copy(isLoading = false))
            }
        },
        commandHandler = { cmd ->
            when (cmd) {
                is Cmd.Login ->
                    authRepository.login(cmd.username, cmd.password)
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .map { SideEffect<Msg, News>(msg = Msg.LoginSuccess, news = News.LoginSuccess) }
                        .onErrorReturn { SideEffect<Msg, News>(msg = Msg.LoginFailure, news = News.LoginFailure(it)) }
                        .asObservable()
            }
        },
        stateListener = {
            println(it)
            stateListener.invoke(it) },
        newsListener = {
            println(it)
            newsListener.invoke(it) }
    )

    data class State(
        val isLoading: Boolean = false
    )

    sealed class Msg {
        data class Login(val username: String, val password: String): Msg()
        object LoginFailure: Msg()
        object LoginSuccess: Msg()
    }

    sealed class Cmd {
        data class Login(val username: String, val password: String): Cmd()
    }

    sealed class News {
        data class LoginFailure(val throwable: Throwable): News()
        object LoginSuccess: News()
    }

    fun bindListeners(
        stateListener: (State) -> Unit,
        newsListener: (News) -> Unit
    ) {
        stateListener.invoke(feature.getCurrentState())
        this.stateListener = stateListener
        this.newsListener = newsListener
    }

    fun dispatch(msg: Msg) {
        feature.accept(msg)
    }

    fun dispose() {
        feature.dispose()
        stateListener = {}
        newsListener = {}
    }

}