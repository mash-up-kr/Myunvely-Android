package com.mashup.myunvely.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mashup.myunvely.data.vo.Sample

@Database(
    entities = [Sample::class],
    version = 1
)
abstract class MyunvelyDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "myunvely.db"

        @Volatile
        private var INSTANCE: MyunvelyDatabase? = null

        fun getInstance(context: Context): MyunvelyDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context): MyunvelyDatabase =
            Room.databaseBuilder(context, MyunvelyDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }


}