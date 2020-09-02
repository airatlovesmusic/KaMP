package com.airatlovesmusic.kamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airatlovesmusic.shared.data.network.ApiClient
import kotlinx.android.synthetic.main.activity_main.*

class AppActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = ApiClient().getArticles().joinToString()
    }

}