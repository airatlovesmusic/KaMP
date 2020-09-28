package com.airatlovesmusic.kamp.ui.auth

import android.os.Bundle
import android.view.View
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.kamp.databinding.FragmentLoginBinding
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.LoginFeatureComponent

class LoginFragment: BaseFragment(R.layout.fragment_login) {

    private val featureComponent by lazy { LoginFeatureComponent() }
    private var binding: FragmentLoginBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        featureComponent.bindListeners(
            stateListener = ::renderState,
            newsListener = ::handleNews
        )
    }

    override fun onDestroyView() {
        binding = null
        featureComponent.dispose()
        super.onDestroyView()
    }

    private fun handleNews(news: LoginFeatureComponent.News) {
        println(news)
    }

    private fun renderState(state: LoginFeatureComponent.State) {
        println(state)
    }

}