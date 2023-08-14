package com.example.contacts.feature_tools.flow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun Fragment.launchAndRepeatOnLifecycle(
    lifecycleCoroutineScope: LifecycleCoroutineScope = lifecycleScope,
    state: Lifecycle.State = Lifecycle.State.CREATED,
    block: CoroutineScope.() -> Unit,
) : Job = lifecycleCoroutineScope.launch {
    repeatOnLifecycle(state, block)
}

fun FragmentActivity.launchAndRepeatOnLifecycle(
    lifecycleCoroutineScope: LifecycleCoroutineScope = lifecycleScope,
    state: Lifecycle.State = Lifecycle.State.CREATED,
    block: CoroutineScope.() -> Unit,
) : Job = lifecycleCoroutineScope.launch {
    repeatOnLifecycle(state, block)
}