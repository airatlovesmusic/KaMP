package com.airatlovesmusic.shared.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airatlovesmusic.shared.router.Router

abstract class BaseFragment : Fragment() {

    abstract val layoutRes: Int

    protected val router: Router by lazy {
        (parentFragment as BaseFlowFragment).router
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() {}

}