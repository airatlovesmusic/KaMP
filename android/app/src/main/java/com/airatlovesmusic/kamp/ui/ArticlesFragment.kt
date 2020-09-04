package com.airatlovesmusic.kamp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.shared.base.BaseFlowFragment
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.observe
import com.airatlovesmusic.shared.presentation.ArticlesViewModel

class ArticlesFragment: BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    private val viewModel by lazy { ArticlesViewModel(router) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articles.observe {
            println("list " + it)
        }
        viewModel.isLoading.observe {
            println("isLoading " + it)
        }
        viewModel.errorMessage.observe {
            println("error " + it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onCleared()
    }

}