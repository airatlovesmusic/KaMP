package com.airatlovesmusic.shared.presentation

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.data.repository.ArticlesRepository
import com.airatlovesmusic.shared.mvi.Feature
import com.airatlovesmusic.shared.mvi.SideEffect
import com.airatlovesmusic.shared.mvi.Update

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
    object GetArticlesFailure: News()
}

class ArticlesFeature(
    private val articlesRepository: ArticlesRepository,
    stateListener: (State) -> Unit,
    newsListener: (News) -> Unit
): Feature<State, Cmd, Msg, News>(
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
                        { SideEffect(msg = Msg.GetArticlesFailure, news = News.GetArticlesFailure) }
                    ).also { emit(it) }
        }
    },
    stateListener = stateListener,
    newsListener = newsListener
)