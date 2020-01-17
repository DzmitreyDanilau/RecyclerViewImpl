package com.example.recyclerviewimpl.presentation.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.databinding.TextItemBinding
import com.example.recyclerviewimpl.utils.roundAndCastToInt
import com.example.recyclerviewimpl.utils.roundAndCastToString

class TextViewHolder(private val binding: TextItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): TextViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TextItemBinding.inflate(layoutInflater, parent, false)
            return TextViewHolder(binding)
        }
    }
}