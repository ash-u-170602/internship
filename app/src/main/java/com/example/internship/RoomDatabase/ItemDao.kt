package com.example.internship.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.internship.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Item)

    @Delete
    fun deleteItem(item: Item?)

    //Don't need to make it a suspend function as Live Data is default executed in background thread
    @Query("SELECT * FROM items")
    fun getItem(): LiveData<List<Item>>

}