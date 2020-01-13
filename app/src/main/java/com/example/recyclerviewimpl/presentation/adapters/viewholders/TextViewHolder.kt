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
    private val arrow = view.arrow

    fun bind(item: Item) {
        val glideRequest = RequestOptions().placeholder(R.drawable.ic_launcher_background)
        val picturePath = item.img
        Glide.with(itemImage.context)
            .setDefaultRequestOptions(glideRequest)
            .load(picturePath)
            .error(R.drawable.ic_error_black_24dp)
            .into(itemImage)
        itemTitle.text = item.title
        //TODO refactor
        val a = BigDecimal(item.cost).setScale(2, BigDecimal.ROUND_DOWN).toString()
        setArrowRotation(BigDecimal(item.cost).setScale(2, BigDecimal.ROUND_DOWN).toInt())
        itemCost.text = a
    }

    private fun setArrowRotation(costValue: Int) {
        if (costValue > 0) {
            arrow.animate().rotation(180f).duration = 1000
            arrow.setNegativeColor()
        } else if (costValue == 0) {
            arrow.visibility = View.INVISIBLE
        }
    }
}