package com.mashup.myunvely.di

import com.mashup.myunvely.data.sharedpreferences.MyunvelySharedPreferences
import com.mashup.myunvely.data.sharedpreferences.MyunvelySharedPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Provides
    fun provideMyunvelySharedPreferences(): MyunvelySharedPreferences = MyunvelySharedPreferencesImpl
}