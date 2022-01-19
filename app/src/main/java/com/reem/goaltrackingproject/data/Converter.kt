package com.reem.goaltrackingproject.data

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromPeriod(period: Period): String {
        return period.name
    }

    @TypeConverter
    fun toPeriod(period: String): Period {
        return Period.valueOf(period)
    }
}