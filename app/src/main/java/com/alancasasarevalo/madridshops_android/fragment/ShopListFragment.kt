package com.alancasasarevalo.madridshops_android.fragment


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alancasasarevalo.madridshops_android.R

class ShopListFragment : Fragment() {

    companion object {
        fun newInstance(): ShopListFragment{
            val fragment = ShopListFragment()

            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_shop_list, container, false)
    }

}// Required empty public constructor
