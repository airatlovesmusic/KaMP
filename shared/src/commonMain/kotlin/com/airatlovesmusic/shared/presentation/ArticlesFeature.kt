package com.airatlovesmusic.shared.presentation

import com.airatlovesmusic.shared.mvi.Feature
import com.airatlovesmusic.shared.mvi.Update
import kotlinx.coroutines.flow.flowOf

data class State(
    val isLoading: Boolean = false
)

sealed class Cmd

sealed class Msg

sealed class News

class ArticlesFeature(
    stateListener: (State) -> Unit,
    newsListener: (News) -> Unit
): Feature<State, Cmd, Msg, News>(
    initialState = State(),
    reducer = { _, state -> Update.state(state) },
    commandHandler = { flowOf() },
    stateListener = stateListener,
    newsListener = newsListener
)