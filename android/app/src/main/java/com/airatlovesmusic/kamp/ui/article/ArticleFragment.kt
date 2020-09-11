package com.airatlovesmusic.kamp.ui.article

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.kamp.databinding.FragmentArticleBinding
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.ArticleFeatureComponent
import com.airatlovesmusic.shared.presentation.ArticleFeatureComponent.*

class ArticleFragment: BaseFragment(R.layout.fragment_article) {

    private val url by lazy { requireArguments().getString(ARG_URL, "") }
    private val featureComponent by lazy { ArticleFeatureComponent(url, parentRouter) }

    private var binding: FragmentArticleBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        featureComponent.bindListeners(
            stateListener = ::renderState,
            newsListener = ::renderNews
        )
    }

    override fun onDestroyView() {
        binding = null
        featureComponent.dispose()
        super.onDestroyView()
    }

    override fun onBackPressed() {
        parentRouter.goBack()
    }

    private fun renderNews(news: News) {
        when (news) {
            is News.GetArticleFailure -> Toast.makeText(requireContext(), news.throwable.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderState(state: State) {
        binding?.tvUrl?.text = state.article?.url ?: ""
        binding?.tvTitle?.text = state.article?.title ?: ""
        binding?.pbLoading?.isVisible = state.isLoading
    }

    companion object {
        fun create(url: String) = ArticleFragment().apply {
            arguments = bundleOf(
                ARG_URL to url
            )
        }
        const val ARG_URL = "url"
    }

}