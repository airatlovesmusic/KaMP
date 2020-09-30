package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components

import kotlinx.browser.window
import react.RComponent
import react.RProps
import react.RState

abstract class BaseComponent<Props : RProps, State : RState>(props: Props) : RComponent<Props, State>(props) {

    fun showError(error: String) {
        window.alert("Error: $error")
    }
}