package com.alancasasarevalo.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun ViewGroup.inflate(layoutResId: Int, attachToRoot: Boolean) : View {
    val  inflater = LayoutInflater.from(context)
    return inflater.inflate(layoutResId, this, attachToRoot)
}