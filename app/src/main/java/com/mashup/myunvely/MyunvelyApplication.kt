package com.mashup.myunvely

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mashup.myunvely.data.sharedpreferences.MyunvelySharedPreferencesImpl
import com.mashup.myunvely.network.OkHttpClientProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyunvelyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initThreeTenBackport()
        initSharedPreferences()
        initOkHttpClientProvider()
        initStetho()
    }

    private fun initThreeTenBackport() {
        AndroidThreeTen.init(this)
    }

    private fun initSharedPreferences() {
        MyunvelySharedPreferencesImpl.init(this)
    }

    private fun initOkHttpClientProvider() {
        OkHttpClientProvider.init(this, MyunvelySharedPreferencesImpl)
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}