package com.mashup.myunvely.network

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
object MyunvelyRetrofit {

    val baseUrl get() = "https://api.github.com/"

    fun <T> create(
        service: Class<T>,
        httpUrl: String = baseUrl,
        client: OkHttpClient = OkHttpClientProvider.okHttpClient
    ): T = Retrofit.Builder()
        .baseUrl(httpUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(service)

    inline fun <reified T : Any> create(
        httpUrl: String = baseUrl,
        client: OkHttpClient = OkHttpClientProvider.okHttpClient
    ): T {
        require(httpUrl.isNotBlank()) { "Parameter httpUrl cannot be blank." }
        return create(service = T::class.java, httpUrl = httpUrl, client = client)
    }
}