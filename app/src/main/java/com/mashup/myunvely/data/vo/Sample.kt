package com.mashup.myunvely.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sample_table")
data class Sample(
    @PrimaryKey val id: String
)