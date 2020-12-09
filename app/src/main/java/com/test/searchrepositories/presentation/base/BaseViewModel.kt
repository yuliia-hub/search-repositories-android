package com.test.searchrepositories.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel() : ViewModel(), CoroutineScope {

    private val listProcess: MutableList<Any> = mutableListOf()
    private var job: Job = SupervisorJob()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + job + exceptionHandler

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    fun executeOnUI(block: suspend () -> Unit) : Job =
        launch(coroutineContext) { block() }

    suspend fun <T> executePending(block: suspend () -> T): T =
        withContext(Dispatchers.IO) { block() }
}