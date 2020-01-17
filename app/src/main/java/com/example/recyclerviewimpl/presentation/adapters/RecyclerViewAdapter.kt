package com.example.recyclerviewimpl.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.presentation.adapters.viewholders.ImgViewHolder
import com.example.recyclerviewimpl.presentation.adapters.viewholders.TextViewHolder
import com.example.recyclerviewimpl.utils.ViewHolderTypes

class RecyclerViewAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(TextItemsDiffUtilsCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewHolderTypes.TYPE_TEXT.value -> {TextViewHolder.from(parent)}
            ViewHolderTypes.IMG_TYPE.value -> {ImgViewHolder.from(parent)}
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (getItemViewType(position)) {
            ViewHolderTypes.TYPE_TEXT.value -> {(holder as TextViewHolder).bind(item)}
            else -> (holder as ImgViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).order) {
            0 -> ViewHolderTypes.TYPE_TEXT.value
            else -> ViewHolderTypes.IMG_TYPE.value
        }
    }

}