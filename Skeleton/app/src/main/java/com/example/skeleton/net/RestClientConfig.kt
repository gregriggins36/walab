package com.example.skeleton.net

import com.example.skeleton.BuildConfig
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter

val API_TIME_OUT = if (BuildConfig.DEBUG) 60L else 20L

data class RestClientConfig(
    val converterFactory: Converter.Factory,
    val callAdapterFactory: CallAdapter.Factory,
    val readTimeOutValue: Long = API_TIME_OUT,
    val writeTimeOutValue: Long = API_TIME_OUT,
    val connectTimeOutValue: Long = API_TIME_OUT) {
    private var interceptors: MutableList<Interceptor> = mutableListOf()

    fun addInterceptor(interceptor: Interceptor) = interceptors.add(interceptor)

    fun interceptors() = interceptors.asIterable()
}