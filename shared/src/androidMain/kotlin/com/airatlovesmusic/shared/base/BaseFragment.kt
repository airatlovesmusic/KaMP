package com.airatlovesmusic.shared.base

import androidx.fragment.app.Fragment
import com.airatlovesmusic.shared.router.Router
import org.koin.core.KoinComponent

abstract class BaseFragment(
    layoutRes: Int
) : Fragment(layoutRes), KoinComponent {

    protected val router: Router by lazy {
        (parentFragment as BaseFlowFragment).router
    }

    open fun onBackPressed() {}

}