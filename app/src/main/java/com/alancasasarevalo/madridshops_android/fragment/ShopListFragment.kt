package com.alancasasarevalo.madridshops_android.fragment


import com.alancasasarevalo.commons.BaseFragment
import com.alancasasarevalo.madridshops_android.R

class ShopListFragment : BaseFragment() {

    companion object {
        fun newInstance(): ShopListFragment{
            val fragment = ShopListFragment()

            return fragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_shop_list
    }

}
