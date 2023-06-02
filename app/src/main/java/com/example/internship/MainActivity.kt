package com.example.internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.internship.RoomDatabase.Chart
import com.example.internship.RoomDatabase.ChartDatabase
import com.example.internship.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.LinkedList

class MainActivity : AppCompatActivity(), CustomDialogue.DialogListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { ItemAdapter(list) }
    private var list: MyLinkedList<Item> = MyLinkedList()
    lateinit var database: ChartDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Create database object
        database = Room.databaseBuilder(
            applicationContext,
            ChartDatabase::class.java,
            "chartDB"
        ).build()



        // Create some sample items
        val item1 = Item("Sample Item 1", 0)
        val item2 = Item("Sample Item 2", 45)
        val item3 = Item("Sample Item 3", 3)


        //Insert items
        list.add(item1)
        list.add(item2)
        list.add(item3)


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
        val item = Item(name, price)
        GlobalScope.launch {
            database.chartDao().insertChart(Chart(0,item))
        }
        list.add(item)
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
    }
}