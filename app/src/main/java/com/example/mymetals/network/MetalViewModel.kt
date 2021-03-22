package com.example.mymetals.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymetals.model.MetalApiResponse
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class MetalViewModel : ViewModel(), KoinComponent {
    private val service: MetalRepository by inject()

    private val metalListMutableLiveData = MutableLiveData<MetalApiResponse>()
    val metalListLiveData: LiveData<MetalApiResponse> get() = metalListMutableLiveData

    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<String?>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }


    fun onError(message: String) {
        loadingError.value = message
        loading.value = false
        Log.d("TAG", "onError: $message")
    }

    fun reset() {

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        //Cancels job when ViewModel is terminated
    }


    fun basicCoroutineFetch() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response: Response<MetalApiResponse> =
                service.getPairs("USDXAU,USDXAG")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    metalListMutableLiveData.postValue(response.body())
                    Log.d("GOLD", "fetchNews: ${metalListMutableLiveData.value} ")
                    Log.d("GOLD", "fetchNews: ${response.body()} ")
                    loadingError.value = null
                    loading.value = false
                } else {
                    onError("Error ${response.message()}")
                }
            }
            service.getExotics()
        }


    }


}