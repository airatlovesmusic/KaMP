package com.airatlovesmusic.shared.base

import androidx.fragment.app.Fragment
import com.airatlovesmusic.android.R
import com.airatlovesmusic.shared.router.Router

class BaseFlowFragment: Fragment() {

    val router: Router by lazy {
        Router(childFragmentManager, R.id.container)
    }

}