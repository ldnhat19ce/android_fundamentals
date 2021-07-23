package com.ldnhat.demoroomrecoutine.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepNightDAO {
    @Insert
    fun insert(sleepNight: SleepNight?)

    @Update
    fun update(sleepNight: SleepNight?)

    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :id")
    fun findById(id: Long?): SleepNight?

    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    fun findTonight(): SleepNight?

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun findAll(): LiveData<List<SleepNight>>
}