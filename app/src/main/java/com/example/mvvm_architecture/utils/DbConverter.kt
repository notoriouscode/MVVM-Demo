package com.example.mvvm_architecture.utils

import androidx.room.TypeConverter
import java.util.*

class DbConverter {

    @TypeConverter
    fun fromDateToLong(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }
}