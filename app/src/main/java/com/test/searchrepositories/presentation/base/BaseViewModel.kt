package com.test.searchrepositories.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.searchrepositories.common.SingleLiveData
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    var errorEvent = SingleLiveData<String>()
    private var job: Job = SupervisorJob()
    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
    }

    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + job + exceptionHandler

    protected fun executeOnUI(block: suspend () -> Unit) : Job =
        viewModelScope.launch(coroutineContext) { block() }

    protected suspend fun <T> executeOnBackground(block: suspend () -> T): T =
        withContext(backgroundContext) { block() }

    protected fun showError(errorMessage: String?) {
        errorEvent.value = errorMessage ?: "api error"
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
        backgroundContext.cancel()
    }
}