package com.airatlovesmusic.kamp.ui.articles

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.kamp.ui.articles.adapter.ArticlesAdapter
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.ArticlesFeatureComponent
import com.airatlovesmusic.shared.presentation.ArticlesFeatureComponent.*

class ArticlesFragment: BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_articles

    private lateinit var featureComponent: ArticlesFeatureComponent

    private val adapter by lazy { ArticlesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        featureComponent = ArticlesFeatureComponent(
            stateListener = ::renderState,
            newsListener = ::handleNews
        )
        initRecycler()
    }

    private fun initRecycler() {
        view?.findViewById<RecyclerView>(R.id.rv_list)?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ArticlesFragment.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        featureComponent.dispose()
    }

    private fun handleNews(news: News) {
        when (news) {
            is News.GetArticlesFailure -> Toast.makeText(requireContext(), news.error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderState(state: State) {
        state.articles?.let { adapter.updateList(it) }
        view?.findViewById<ProgressBar>(R.id.pb_loading)?.isVisible = state.isLoading
    }

}