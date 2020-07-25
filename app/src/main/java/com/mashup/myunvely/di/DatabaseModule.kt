package com.mashup.myunvely.di

import android.content.Context
import com.mashup.myunvely.room.MyunvelyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
object DatabaseModule {
    @Provides
    fun getMyunvelyDatabase(@ApplicationContext context: Context): MyunvelyDatabase =
        MyunvelyDatabase.getInstance(context)
}