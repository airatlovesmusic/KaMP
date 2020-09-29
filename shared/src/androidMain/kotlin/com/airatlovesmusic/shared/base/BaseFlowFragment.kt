package com.airatlovesmusic.shared.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airatlovesmusic.shared.R
import com.airatlovesmusic.shared.router.Router
import com.airatlovesmusic.shared.router.Screen
import org.koin.core.KoinComponent

abstract class BaseFlowFragment: BaseFragment(R.layout.layout_container) {

    abstract val launchScreen: Screen

    val router: Router by lazy {
        Router(childFragmentManager, R.id.container, parentRouter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router.goTo(launchScreen)
    }

    override fun onBackPressed() {
        (childFragmentManager.findFragmentById(R.id.container) as? BaseFragment)?.onBackPressed() ?: parentRouter.goBack()
    }

}