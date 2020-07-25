package com.mashup.myunvely.network.interceptors

import com.mashup.myunvely.data.sharedpreferences.MyunvelySharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val preferences: MyunvelySharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .apply {
                    //header("authorization", "preferences.getAuthToken()")
                }
                .build()
        )
    }
}