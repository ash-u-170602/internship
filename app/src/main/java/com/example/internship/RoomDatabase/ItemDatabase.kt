package com.example.internship.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.internship.Item

@Database(entities = [Item::class], version = 2)
abstract class ItemDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao

}