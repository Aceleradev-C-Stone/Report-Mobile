package com.upco.report.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.upco.report.data.local.model.LogCache
import kotlinx.coroutines.flow.Flow

@Dao
interface LogsDao {

    @Query("SELECT * FROM logs")
    fun getLogs(): Flow<List<LogCache>>

    @Transaction
    fun updateData(logs: List<LogCache>) {
        deleteAll()
        insertAll(logs)
    }

    @Insert
    fun insert(vararg log: LogCache)

    @Insert
    fun insertAll(logs: List<LogCache>)

    @Query("DELETE FROM logs")
    fun deleteAll()
}