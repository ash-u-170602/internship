package com.example.internship.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Chart")
data class Chart(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price: Int
)
