package com.example.recyclerviewimpl.presentation.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.data.models.Item
import kotlinx.android.synthetic.main.text_item.view.*
import java.math.BigDecimal

class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val itemImage = view.img_item_image
    private val itemTitle = view.tv_item_title
    private val itemCost = view.tv_item_cost

    fun bind(item: Item) {
        val glideRequest = RequestOptions().placeholder(R.drawable.ic_launcher_background)
        val picturePath = item.img
        Glide.with(itemImage.context)
            .setDefaultRequestOptions(glideRequest)
            .load(picturePath)
            .error(R.drawable.ic_error_black_24dp)
            .into(itemImage)
        itemTitle.text = item.title
        val a = BigDecimal(item.cost).setScale(2, BigDecimal.ROUND_DOWN).toString()
        itemCost.text = a
    }
}