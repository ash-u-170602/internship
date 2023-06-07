package com.example.internship

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.internship.databinding.ItemLayoutBinding

class ItemAdapter(
    var itemList: MyLinkedList<Item>,
    val listener: ItemRvListener
) : Adapter<ItemAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

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
        return itemList.size()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = itemList.get(position)
        holder.binding.name.text = currentItem?.name
        holder.binding.price.text = currentItem?.price.toString()
        holder.binding.root.setOnClickListener {
            listener.onItemClicked(currentItem)
        }
    }

    fun updateData(data: MyLinkedList<Item>) {
        itemList = data
        notifyItemRangeChanged(0, itemCount)
    }

}

interface ItemRvListener {
    fun onItemClicked(item: Item?)
}
