package com.example.internship.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.internship.Item

@Entity(tableName = "Chart")
data class Chart(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val item: Item
)
