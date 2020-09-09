package com.airatlovesmusic.kamp.ui.articles.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.model.Article

class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(article: Article) {
        itemView.findViewById<TextView>(R.id.tv_title).text = article.title
        itemView.findViewById<TextView>(R.id.tv_url).text = article.url
    }
}