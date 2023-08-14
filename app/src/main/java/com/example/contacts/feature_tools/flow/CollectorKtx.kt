package com.example.contacts.feature_tools.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> CoroutineScope.observeFor(
    flow: Flow<T>,
    collector: (T) -> Unit = {},
) = launch {
    flow.collectLatest(collector)
}