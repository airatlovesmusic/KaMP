package com.airatlovesmusic.shared.presentation

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.data.network.ApiClient
import com.airatlovesmusic.shared.data.repository.ArticlesRepository
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticlesViewModel: ViewModel() {

    private val apiClient = ApiClient()
    private val articlesRepository = ArticlesRepository(apiClient)

    var isLoading = MutableLiveData(false)
    val errorMessage = MutableLiveData<String?>(null)
    val articles = MutableLiveData<List<Article>?>(null)

    init { getArticles() }

    private fun getArticles() {
        GlobalScope.launch {
            isLoading.postValue(true)
            runCatching { articlesRepository.getArticles() }
                .onSuccess { articles.postValue(it) }
                .onFailure { errorMessage.postValue(it.message) }
            isLoading.postValue(false)
        }
    }

}