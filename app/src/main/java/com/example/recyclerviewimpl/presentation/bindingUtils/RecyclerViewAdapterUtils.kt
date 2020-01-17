package com.example.recyclerviewimpl.presentation.bindingUtils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.presentation.views.Arrow
import com.example.recyclerviewimpl.utils.roundAndCastToInt
import com.example.recyclerviewimpl.utils.roundAndCastToString


@BindingAdapter("textItemTitle")
fun TextView.setItemTitle(item: Item?) {
    item?.let {
        text = it.title
    }
}

@BindingAdapter("textItemCost")
fun TextView.setItemCostValue(item: Item?) {
    item?.let {
        text = it.cost.roundAndCastToString()
    }
}

@BindingAdapter("viewHolderImageSetup")
fun ImageView.setImage(item: Item?) {
    item?.img?.let {
        setupGlide(this, it)
    }

}

@BindingAdapter("rotateArrow")
fun Arrow.rotateArrow(item: Item) {
    this.setArrowRotation(item.cost.roundAndCastToInt())
}

private fun setupGlide(view: ImageView, path: String) {
    val glideRequest = RequestOptions().placeholder(R.drawable.ic_launcher_background)
    Glide.with(view)
        .setDefaultRequestOptions(glideRequest)
        .load(path)
        .error(R.drawable.ic_error_black_24dp)
        .into(view)
}