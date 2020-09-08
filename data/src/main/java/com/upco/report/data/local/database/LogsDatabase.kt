package com.upco.report.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.upco.report.data.local.model.LogCache

@Database(version = 1, entities = [LogCache::class])
@TypeConverters(Converters::class)
abstract class LogsDatabase: RoomDatabase() {

    abstract fun logsDao(): LogsDao

    companion object {
        fun createDatabase(context: Context): LogsDao {
            return Room
                .databaseBuilder(context, LogsDatabase::class.java, "Logs.db")
                .build()
                .logsDao()
        }
    }
}