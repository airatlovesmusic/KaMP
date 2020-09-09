package com.airatlovesmusic.kamp.ui.article

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.ArticleFeatureComponent
import com.airatlovesmusic.shared.presentation.ArticleFeatureComponent.*

class ArticleFragment: BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_article

    private val url by lazy { requireArguments().getString(ARG_URL, "") }
    private lateinit var featureComponent: ArticleFeatureComponent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        featureComponent = ArticleFeatureComponent(url, ::renderState, ::renderNews)
    }

    private fun renderNews(news: News) {
        when (news) {
            is News.GetArticleFailure ->
                Toast.makeText(requireContext(), news.throwable.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderState(state: State) {}

    companion object {
        fun create(url: String) = ArticleFragment().apply {
            arguments = bundleOf(
                ARG_URL to url
            )
        }
        const val ARG_URL = "url"
    }

}