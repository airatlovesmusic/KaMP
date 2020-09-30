package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.base

import com.airatlovesmusic.shared.router.Router
import com.airatlovesmusic.shared.router.Screen
import kotlinx.browser.document
import react.RBuilder
import react.RProps
import react.RState
import react.setState

class BaseFlowComponent(
    private val launchScreen: Screen
): BaseComponent<RProps, FlowState>(object : RProps{}) {

    override fun FlowState.init() {
        currentScreen = launchScreen
    }

    private val router: Router = object : Router() {
        override fun goTo(screen: Screen) {
            setState {
                currentScreen = screen
            }
        }
    }

    override fun RBuilder.render() {
        react.dom.render(document.getElementById("root")) {
            state.currentScreen.getComponent(router)
        }
    }

}

interface FlowState: RState {
    var currentScreen: Screen
}