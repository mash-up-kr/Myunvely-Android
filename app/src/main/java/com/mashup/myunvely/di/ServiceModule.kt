package com.mashup.myunvely.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ServiceModule {

//    @Provides
//    fun provideAuthService(): AuthService = MoimRetrofit.create()

}