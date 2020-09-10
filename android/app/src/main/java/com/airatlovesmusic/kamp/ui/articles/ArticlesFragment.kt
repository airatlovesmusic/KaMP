package com.airatlovesmusic.kamp.ui.articles

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.kamp.databinding.FragmentArticlesBinding
import com.airatlovesmusic.kamp.ui.articles.adapter.ArticlesAdapter
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.ArticlesFeatureComponent
import com.airatlovesmusic.shared.presentation.ArticlesFeatureComponent.*

class ArticlesFragment: BaseFragment(R.layout.fragment_articles) {

    private lateinit var featureComponent: ArticlesFeatureComponent

    private var binding: FragmentArticlesBinding? = null

    private val adapter by lazy { ArticlesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticlesBinding.bind(view)
        featureComponent = ArticlesFeatureComponent(
            stateListener = ::renderState,
            newsListener = ::handleNews
        )
        initRecycler()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initRecycler() {
        binding?.rvList?.apply {
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
        binding?.pbLoading?.isVisible = state.isLoading
    }

}