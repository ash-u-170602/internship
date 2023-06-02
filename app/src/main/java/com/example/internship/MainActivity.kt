package com.example.internship

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.internship.RoomDatabase.ItemDatabase
import com.example.internship.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CustomDialogue.DialogListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { ItemAdapter(list) }
    private var list: MyLinkedList<Item> = MyLinkedList()
    lateinit var database: ItemDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Create database object
        database = Room.databaseBuilder(
            applicationContext,
            ItemDatabase::class.java,
            "itemDB"
        ).build()


        //Insert item from database
        list.add(Item(0, "Sample item 1", 34))
        list.add(Item(1, "Sample item 2", 343))

        val items = database.itemDao().getChart().value
        if (items != null) {
            for (item in items) {
                list.add(item)
            }
        }
            binding.itemList.adapter = adapter
            adapter.updateData(list)
            binding.itemList.layoutManager = LinearLayoutManager(this)

    }

    fun onClickAdd(view: View) {
        val customDialogue = CustomDialogue()
        customDialogue.show(supportFragmentManager, "Custom Dialogue")
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun applyTexts(name: String, price: Int) {
        val item = Item(0, name, price)
        GlobalScope.launch {
            database.itemDao().insertChart(item)
        }
//        list.add(item)
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
    }
}