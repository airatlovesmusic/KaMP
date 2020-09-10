package com.airatlovesmusic.kamp.ui.articles.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.kamp.databinding.ItemArticleBinding
import com.airatlovesmusic.model.Article

class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = ItemArticleBinding.bind(itemView)

    fun bind(article: Article) {
        binding.tvTitle.text = article.title
        binding.tvUrl.text = article.url
    }
}