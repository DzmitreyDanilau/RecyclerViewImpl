package com.example.recyclerviewimpl.presentation.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.databinding.ImageItemBinding
import com.example.recyclerviewimpl.databinding.TextItemBinding

class ImgViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.imageItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ImgViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ImageItemBinding.inflate(layoutInflater, parent, false)
            return ImgViewHolder(binding)
        }
    }

}