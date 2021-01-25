package com.goofy.goober.vyasa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch { block() }
}
