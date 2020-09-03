package com.airatlovesmusic.shared.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airatlovesmusic.android.R
import com.airatlovesmusic.shared.router.Router
import com.airatlovesmusic.shared.router.Screen

abstract class BaseFlowFragment: Fragment() {

    abstract val launchScreen: Screen

    val router: Router by lazy {
        Router(childFragmentManager, R.id.container)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router.goTo(launchScreen)
    }

}