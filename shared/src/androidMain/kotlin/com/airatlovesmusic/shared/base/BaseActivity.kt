package com.airatlovesmusic.shared.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airatlovesmusic.shared.R
import com.airatlovesmusic.shared.router.Router
import com.airatlovesmusic.shared.router.Screen
import org.koin.core.KoinComponent

abstract class BaseActivity: AppCompatActivity(), KoinComponent {

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    abstract val launchScreen: Screen

    val router by lazy { Router(supportFragmentManager, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)
        router.goTo(launchScreen)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

}