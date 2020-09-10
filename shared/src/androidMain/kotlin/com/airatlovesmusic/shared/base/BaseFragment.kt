package com.airatlovesmusic.shared.base

import androidx.fragment.app.Fragment
import com.airatlovesmusic.shared.router.Router
import org.koin.core.KoinComponent

abstract class BaseFragment(
    layoutRes: Int
) : Fragment(layoutRes), KoinComponent {

    protected val parentRouter: Router by lazy {
        when (val parentFragment = parentFragment) {
            is BaseFlowFragment -> parentFragment.router
            else -> (activity as BaseActivity).router
        }
    }

    open fun onBackPressed() {}

}