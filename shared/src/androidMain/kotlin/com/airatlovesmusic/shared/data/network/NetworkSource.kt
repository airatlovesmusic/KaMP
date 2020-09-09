package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.Constants
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.single.subscribeOn
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.io.readText

internal actual fun networkSource(): NetworkSource = NetworkSourceImpl()

internal class NetworkSourceImpl: NetworkSource {

    override fun getArticles(): Single<List<Article>> =
        singleFromFunction {
            val url = URL("${Constants.BaseUrl.GITHUB}/${NetworkConstants.ARTICLES_ENDPOINT}")
            val connection = url.openConnection() as HttpURLConnection

            connection
                .inputStream
                .bufferedReader()
                .use(BufferedReader::readText)
                .let { println(it); Json.decodeFromString(ListSerializer(Article.serializer()), it) }
        }
        .subscribeOn(ioScheduler)

}