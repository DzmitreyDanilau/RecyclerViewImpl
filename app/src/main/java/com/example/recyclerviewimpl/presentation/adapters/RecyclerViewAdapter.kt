package com.example.recyclerviewimpl.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.presentation.adapters.viewholders.ImgViewHolder
import com.example.recyclerviewimpl.presentation.adapters.viewholders.TextViewHolder
import com.example.recyclerviewimpl.utils.TYPE_IMG
import com.example.recyclerviewimpl.utils.TYPE_TEXT

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemsList = mutableListOf<Item>()
    fun getItemList() = itemsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_TEXT -> {
                return TextViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.text_item,
                        parent,
                        false
                    )
                )
            }
            else -> {
                return ImgViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.img_item,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_TEXT -> (holder as TextViewHolder).bind(itemsList[position])
            else -> (holder as ImgViewHolder).bind(itemsList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemsList[position].order) {
            0 -> TYPE_TEXT
            else -> TYPE_IMG
        }
    }

}