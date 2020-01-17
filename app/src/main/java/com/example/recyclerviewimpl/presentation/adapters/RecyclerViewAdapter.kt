package com.example.recyclerviewimpl.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.presentation.adapters.viewholders.ImgViewHolder
import com.example.recyclerviewimpl.presentation.adapters.viewholders.TextViewHolder
import com.example.recyclerviewimpl.utils.ViewHolderTypes

class RecyclerViewAdapter :
    ListAdapter<Item, RecyclerView.ViewHolder>(TextItemsDiffUtilsCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ViewHolderTypes.TYPE_TEXT.value -> {
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (getItemViewType(position)) {
            ViewHolderTypes.IMG_TYPE.value -> {
                (holder as TextViewHolder).bind(item)
            }
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