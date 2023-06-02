package com.example.internship.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Chart::class], version = 1)
abstract class ChartDatabase: RoomDatabase() {

    abstract fun chartDao(): ChartDao

}