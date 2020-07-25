package com.mashup.myunvely.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val userAgent: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .header("User-Agent", userAgent)
                .build()
        )
    }
}
