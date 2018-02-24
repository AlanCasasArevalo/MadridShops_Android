package com.alancasasarevalo.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = container?.inflate(getLayoutResId(), false)!!

        return rootView
    }

    abstract fun getLayoutResId(): Int

}