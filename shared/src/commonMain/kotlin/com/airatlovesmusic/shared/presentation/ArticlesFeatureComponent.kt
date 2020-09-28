package com.airatlovesmusic.shared.presentation

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.data.repository.ArticlesRepository
import com.airatlovesmusic.shared.mvi.Feature
import com.airatlovesmusic.shared.mvi.SideEffect
import com.airatlovesmusic.shared.mvi.Update
import com.airatlovesmusic.shared.router.Router
import com.airatlovesmusic.shared.router.Screen
import com.airatlovesmusic.shared.router.Screens
import com.badoo.reaktive.maybe.asObservable
import com.badoo.reaktive.maybe.map
import com.badoo.reaktive.maybe.observeOn
import com.badoo.reaktive.maybe.onErrorReturn
import com.badoo.reaktive.observable.defaultIfEmpty
import com.badoo.reaktive.observable.startWithValue
import com.badoo.reaktive.observable.toObservable
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.asObservable
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.onErrorReturn
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.contracts.Effect

class ArticlesFeatureComponent(
    private val router: Router? = null
): KoinComponent {

    private val articlesRepository by inject<ArticlesRepository>()
    private val screens by inject<Screens>()

    private var stateListener: ((State) -> Unit)? = null
    private var newsListener: ((News) -> Unit)? = null

    private val feature = Feature<State, Cmd, Msg, News>(
        initialState = State(),
        initialMessages = setOf(Msg.GetArticles),
        reducer = { msg, state ->
            when (msg) {
                is Msg.GetArticles -> Update(
                    cmd = Cmd.GetArticles,
                    state = state.copy(isLoading = true)
                )
                is Msg.NewArticles -> Update.state(
                    state.copy(
                        articles = msg.list,
                        isLoading = false
                    )
                )
                is Msg.GetArticlesFailure -> Update.state(state.copy(isLoading = false))
            }
        },
        commandHandler = { cmd: Cmd ->
            when (cmd) {
                is Cmd.GetArticles ->
                    articlesRepository.getArticles()
                        .map { SideEffect.msg<Msg, News>(Msg.NewArticles(it)) }
                        .onErrorReturn {
                            SideEffect(
                                msg = Msg.GetArticlesFailure,
                                news = News.GetArticlesFailure("error")
                            )
                        }
                        .asObservable()
            }
        },
        stateListener = { stateListener?.invoke(it) },
        newsListener = { newsListener?.invoke(it) }
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

    fun bindListeners(
        stateListener: (State) -> Unit,
        newsListener: (News) -> Unit
    ) {
        stateListener.invoke(feature.getCurrentState())
        this.stateListener = stateListener
        this.newsListener = newsListener
    }

    fun dispose() {
        feature.dispose()
        stateListener = {}
        newsListener = {}
    }

    fun dispatch(msg: Msg) {
        feature.accept(msg)
    }

    fun goToArticle(id: Int) {
        router?.goTo(screens.article(id))
    }

}