package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.Constants
import com.badoo.reaktive.base.setCancellable
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.onErrorReturn
import com.badoo.reaktive.single.single
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import platform.Foundation.*
import kotlin.native.concurrent.freeze

internal class NetworkSourceImpl: NetworkSource {
    override fun getArticles(): Single<List<Article>> {
        return single<List<Article>> { emitter ->
            val callback: (NSData?, NSURLResponse?, NSError?) -> Unit =
                { data: NSData?, _, error: NSError? ->
                    if (data != null) {
                        emitter.onSuccess(
                            Json.decodeFromString(
                                ListSerializer(Article.serializer()),
                                NSString.create(data, NSUTF8StringEncoding).toString()
                            )
                        )
                    } else { emitter.onError(Exception(error?.description() ?: "Error")) }
                }
            val task = NSURLSession.sharedSession.dataTaskWithURL(
                NSURL(string = "${Constants.BaseUrl.GITHUB}/${NetworkConstants.ARTICLES_ENDPOINT}"),
                callback.freeze()
            )
            task.resume()
            emitter.setCancellable(task::cancel)
        }.onErrorReturn { listOf() }
    }
}

internal actual fun networkSource(): NetworkSource = NetworkSourceImpl()
