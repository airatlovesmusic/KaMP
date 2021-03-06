package com.airatlovesmusic.kamp.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.kamp.databinding.FragmentLoginBinding
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.LoginFeatureComponent
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.inject

class LoginFragment: BaseFragment(R.layout.fragment_login) {

    private val screens by inject<Screens>()
    private val featureComponent by lazy { LoginFeatureComponent() }
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        featureComponent.bindListeners(
            stateListener = ::renderState,
            newsListener = ::handleNews
        )
        setOnClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        featureComponent.dispose()
    }

    private fun setOnClickListeners() {
        binding.btnLogin.setOnClickListener {
            featureComponent.dispatch(
                LoginFeatureComponent.Msg.Login(
                    username = binding.etUsername.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            )
        }
        binding.btnRegister.setOnClickListener {
            parentRouter.goTo(screens.register())
        }
    }

    private fun handleNews(news: LoginFeatureComponent.News) {
        when(news) {
            is LoginFeatureComponent.News.LoginSuccess -> {
                parentRouter.startFlow(screens.articles())
            }
            is LoginFeatureComponent.News.LoginFailure -> {
                Toast.makeText(requireContext(), news.throwable.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderState(state: LoginFeatureComponent.State) {
        binding.pbLoading.isVisible = state.isLoading
    }

}