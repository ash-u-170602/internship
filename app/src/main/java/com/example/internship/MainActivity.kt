package com.example.internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internship.databinding.ActivityMainBinding
import java.util.LinkedList

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { ItemAdapter(list) }
    private lateinit var list: LinkedList<Item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Create some sample items
        val item1 = Item("Item 1", 9.99)
        val item2 = Item("Item 2", 14.99)
        val item3 = Item("Item 3", 19.99)
        val item4 = Item("Item 3", 19.99)
        val item5 = Item("Item 3", 19.99)
        val item6 = Item("Item 3", 19.99)
        val item7 = Item("Item 3", 19.99)
        val item8 = Item("Item 3", 19.99)
        val item9 = Item("Item 3", 19.99)
        val item10 = Item("Item 3", 19.99)
        val item11 = Item("Item 3", 19.99)

        list = LinkedList()
        list.add(item1)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(item3)
        list.add(item3)
        list.add(item3)
        list.add(item3)
        list.add(item3)
        list.add(item3)
        list.add(item3)

        binding.itemList.adapter = adapter
        adapter.updateData(list)
        binding.itemList.layoutManager =LinearLayoutManager(this)
    }
}