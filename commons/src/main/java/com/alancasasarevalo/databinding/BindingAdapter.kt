package com.alancasasarevalo.databinding

import android.content.Context
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("imageURL")

fun loadImageFromInternet(context: Context, imageView: ImageView, url:String){

    Picasso
            .with(context)
            .load(url)
            .into(imageView)

}
