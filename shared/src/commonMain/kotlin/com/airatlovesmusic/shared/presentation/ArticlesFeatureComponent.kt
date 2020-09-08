package com.airatlovesmusic.shared.presentation

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.data.repository.ArticlesRepository
import com.airatlovesmusic.shared.mvi.Feature
import com.airatlovesmusic.shared.mvi.SideEffect
import com.airatlovesmusic.shared.mvi.Update
import org.koin.core.KoinComponent
import org.koin.core.inject

class ArticlesFeatureComponent(
    stateListener: (State) -> Unit,
    newsListener: (News) -> Unit
): KoinComponent {

    private val articlesRepository by inject<ArticlesRepository>()

    private val feature = Feature<State, Cmd, Msg, News>(
        initialState = State(),
        initialMessages = setOf(Msg.GetArticles),
        reducer = { msg, state ->
            when (msg) {
                is Msg.GetArticles -> Update(cmd = Cmd.GetArticles, state = state.copy(isLoading = true))
                is Msg.NewArticles -> Update.state(state.copy(articles = msg.list, isLoading = false))
                is Msg.GetArticlesFailure -> Update.state(state.copy(isLoading = false))
            }
        },
        commandHandler = { cmd: Cmd ->
            when (cmd) {
                is Cmd.GetArticles ->
                    runCatching { articlesRepository.getArticles() }
                        .fold(
                            { SideEffect.msg<Msg, News>(Msg.NewArticles(it)) },
                            { SideEffect(msg = Msg.GetArticlesFailure, news = News.GetArticlesFailure(it.message ?: "error")) }
                        ).also { emit(it) }
            }
        },
        stateListener = stateListener,
        newsListener = newsListener
    )

    data class State(
        val articles: List<Article>? = null,
        val isLoading: Boolean = false
    )

    sealed class Msg {
        object GetArticles: Msg()
        // Effects
        data class NewArticles(val list: List<Article>): Msg()
        object GetArticlesFailure: Msg()
    }

    sealed class Cmd {
        object GetArticles: Cmd()
    }

    sealed class News {
        data class GetArticlesFailure(val error: String): News()
    }

    fun dispose() {
        feature.dispose()
    }

    fun dispatch(msg: Msg) {
        feature.accept(msg)
    }

}