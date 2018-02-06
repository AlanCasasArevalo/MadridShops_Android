package com.alancasasarevalo.madridshops_android.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alancasasarevalo.madridshops_android.R


/**
 * A simple [Fragment] subclass.
 */
class ShopListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_shop_list, container, false)
    }

}// Required empty public constructor
