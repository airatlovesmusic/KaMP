package com.airatlovesmusic.shared

import dev.icerock.moko.mvvm.livedata.LiveData

fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
    addObserver { observer.invoke(it) }
}