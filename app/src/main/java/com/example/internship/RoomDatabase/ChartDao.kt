package com.example.internship.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChartDao {

    @Insert
    suspend fun insertChart(chart: Chart)

    //Don't need to make it a suspend function as Live Data is default executed in background thread
    @Query("SELECT * FROM chart")
    fun getChart(): LiveData<List<Chart>>

}