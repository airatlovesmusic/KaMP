package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.base

import com.airatlovesmusic.shared.router.Router
import com.airatlovesmusic.shared.router.Screen
import kotlinext.js.asJsObject
import kotlinext.js.jsObject
import react.*

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
        child(state.currentScreen.getComponent().asJsObject() as RClass<*>())
    }

}

interface FlowState: RState {
    var currentScreen: Screen
}