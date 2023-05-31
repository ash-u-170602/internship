package com.example.internship

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.internship.databinding.ItemLayoutBinding
import java.util.LinkedList

class ItemAdapter(var itemList: LinkedList<Item>): Adapter<ItemAdapter.HomeViewHolder>() {

inner class HomeViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.binding.name.text = currentItem.name
        holder.binding.price.text = currentItem.price.toString()
    }

    fun updateData(data: LinkedList<Item>){
        itemList=data
        notifyItemRangeChanged(0,itemCount)
    }

}