package com.example.internship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internship.databinding.ActivityMainBinding
import java.util.LinkedList

class MainActivity : AppCompatActivity(), CustomDialogue.DialogListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { ItemAdapter(list) }
    private var list: MyLinkedList<Item> = MyLinkedList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Create some sample items
        val item1 = Item("Sample Item 1", 0)
        val item2 = Item("Sample Item 2", 45)
        val item3 = Item("Sample Item 3", 3)

        //Initialise list

        //Insert items
        list.add(item1)
        list.add(item2)
        list.add(item3)


        binding.itemList.adapter = adapter
        adapter.updateData(list)
        binding.itemList.layoutManager =LinearLayoutManager(this)
    }

    fun onClickAdd(view: View) {
        val customDialogue = CustomDialogue()
        customDialogue.show(supportFragmentManager, "Custom Dialogue")
    }

    override fun applyTexts(name: String, price: Int) {
        val item = Item(name, price)
        list.add(item)
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
    }
}