package com.example.recyclerviewimpl.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.recyclerviewimpl.data.models.Item

class TextItemsDiffUtilsCallBack : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.cost == newItem.cost
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}