package com.mxcsyounes.earthquaketracker.utils

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitLiveData<T>(var call: Call<T>) : LiveData<T>(), Callback<T> {

    companion object {
        private const val TAG = "RetrofitLiveData"
    }

    //this method get called when the first observer start observing.
    override fun onActive() {
        Log.d(TAG, "onActive : is Active")
        if (!call.isCanceled && !call.isExecuted) call.enqueue(this)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.d(TAG, "onFailure: ${t.message}")
        value = null
    }

    // get called when Call get back a response, here w set the data of LiveData
    override fun onResponse(call: Call<T>, response: Response<T>) {
        val res = response.body()
        Log.d(TAG, "onResponse is called ${res.toString()}")
        value = res
    }

    fun reloadData(call: Call<T>) {
        this.call = call
        call.enqueue(this)
    }

    fun cancel() = if (!call.isCanceled) call.cancel() else Unit

    fun retry() {
        call.clone().enqueue(this)
    }
}