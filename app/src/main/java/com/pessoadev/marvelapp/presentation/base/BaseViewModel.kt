package com.pessoadev.marvelapp.presentation.base

import androidx.lifecycle.*
import com.pessoadev.marvelapp.data.model.ErrorModel
import com.pessoadev.marvelapp.data.source.remote.ApiErrorHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {

    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

    fun getError(e: Exception) : ErrorModel{
        return ApiErrorHandle.traceErrorException(e)
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

}