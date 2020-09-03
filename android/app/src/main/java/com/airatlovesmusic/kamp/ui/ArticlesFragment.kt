package com.airatlovesmusic.kamp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.shared.base.BaseFlowFragment
import com.airatlovesmusic.shared.presentation.ArticlesViewModel

class ArticlesFragment: Fragment() {

    private val viewModel by lazy {
        ArticlesViewModel((parentFragment as BaseFlowFragment).router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articles.addObserver {
            println("list " + it)
        }
        viewModel.isLoading.addObserver {
            println("isLoading " + it)
        }
        viewModel.errorMessage.addObserver {
            println("error " + it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onCleared()
    }

}