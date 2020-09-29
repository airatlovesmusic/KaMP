package com.airatlovesmusic.kamp.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.kamp.databinding.FragmentRegisterBinding
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.RegisterFeatureComponent
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.inject

class RegisterFragment: BaseFragment(R.layout.fragment_register) {

    private val screens by inject<Screens>()
    private val featureComponent by lazy { RegisterFeatureComponent() }
    private lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        featureComponent.bindListeners(
            stateListener = ::renderState,
            newsListener = ::handleNews
        )
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnLogin.setOnClickListener { parentRouter.goBack() }
        binding.btnRegister.setOnClickListener {
            featureComponent.dispatch(
                RegisterFeatureComponent.Msg.Register(
                    username = binding.etUsername.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            )
        }
    }

    private fun handleNews(news: RegisterFeatureComponent.News) {
        when(news) {
            is RegisterFeatureComponent.News.RegisterSuccess -> {
                parentRouter.startFlow(screens.articles())
            }
            is RegisterFeatureComponent.News.RegisterFailure -> {
                Toast.makeText(requireContext(), news.throwable.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderState(state: RegisterFeatureComponent.State) {
        binding.pbLoading.isVisible = state.isLoading
    }
}