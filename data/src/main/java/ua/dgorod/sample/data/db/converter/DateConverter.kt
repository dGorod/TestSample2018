package ua.dgorod.sample.data.db.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?) = value?.let { Date(it) }

    @TypeConverter
    fun toTimestamp(value: Date?) = value?.time
}