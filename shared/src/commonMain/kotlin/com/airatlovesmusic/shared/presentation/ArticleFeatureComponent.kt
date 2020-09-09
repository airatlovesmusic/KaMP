package com.airatlovesmusic.shared.presentation

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.data.repository.ArticlesRepository
import com.airatlovesmusic.shared.mvi.Feature
import com.airatlovesmusic.shared.mvi.SideEffect
import com.airatlovesmusic.shared.mvi.Update
import com.badoo.reaktive.single.asObservable
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.onErrorReturn
import org.koin.core.KoinComponent
import org.koin.core.inject

class ArticleFeatureComponent(
    url: String,
    stateListener: (State) -> Unit,
    newsListener: (News) -> Unit
): KoinComponent {

    private val articlesRepository by inject<ArticlesRepository>()

    private val feature = Feature<State, Cmd, Msg, News>(
        initialState = State(),
        initialMessages = setOf(Msg.GetArticle(url)),
        reducer = { msg, state ->
            when (msg) {
                is Msg.GetArticle -> Update(cmd = Cmd.GetArticle(msg.url), state = state.copy(isLoading = true))
                is Msg.GetArticleFailure -> Update.state(state.copy(isLoading = false))
                is Msg.NewArticle -> Update.state(state.copy(article = msg.article))
            }
        },
        commandHandler = { cmd ->
            when (cmd) {
                is Cmd.GetArticle ->
                    articlesRepository.getArticle(cmd.url)
                        .map { SideEffect<Msg, News>(msg = Msg.NewArticle(it)) }
                        .onErrorReturn { SideEffect<Msg, News>(msg = Msg.GetArticleFailure(it), news = News.GetArticleFailure(it)) }
                        .asObservable()
            }
        },
        stateListener = stateListener,
        newsListener = newsListener
    )

    data class State(
        val isLoading: Boolean = false,
        val article: Article? = null
    )

    sealed class Msg {
        data class GetArticle(val url: String): Msg()
        data class NewArticle(val article: Article): Msg()
        data class GetArticleFailure(val throwable: Throwable): Msg()
    }

    sealed class Cmd {
        data class GetArticle(val url: String): Cmd()
    }

    sealed class News {
        data class GetArticleFailure(val throwable: Throwable): News()
    }

    fun dispose() {
        feature.dispose()
    }

}