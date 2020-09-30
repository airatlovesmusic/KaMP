package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.base

import com.airatlovesmusic.shared.router.Router
import kotlinx.browser.window
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

abstract class BaseComponent<Props : RProps, State : RState>(
    props: Props,
    protected val parentRouter: Router? = null
) : RComponent<Props, State>(props) {

    override fun RBuilder.render() {}

    fun showError(error: String) {
        window.alert("Error: $error")
    }
}