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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CustomDialogue.DialogListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { ItemAdapter(list, rvListener) }
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

        var count = 0

        database.itemDao().getItem().observe(this) {

            if (count == 0) {
                for (item in it) {
                    list.add(item)
                }
                count++
            } else list.add(it[it.size - 1])
            adapter.updateData(list)
        }
        binding.itemList.layoutManager = LinearLayoutManager(this)
        binding.itemList.adapter = adapter
    }

    fun onClickAdd(view: View) {
        val customDialogue = CustomDialogue()
        customDialogue.show(supportFragmentManager, "Custom Dialogue")
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun applyTexts(name: String, price: Int) {
        val item = Item(0, name, price)
        GlobalScope.launch(Dispatchers.IO) {
            database.itemDao().insertItem(item)
        }
//        list.add(item)
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private val rvListener by lazy {
        object : ItemRvListener {
            override fun onItemClicked(item: Item?) {
                GlobalScope.launch {
                    database.itemDao().deleteItem(item)
                }
                Toast.makeText(applicationContext, item?.name, Toast.LENGTH_SHORT).show()
            }

        }
    }
}