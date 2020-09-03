package com.airatlovesmusic.kamp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airatlovesmusic.kamp.R

class AppActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }

}