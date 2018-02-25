package com.alancasasarevalo.madridshops_android.fragment


import android.support.v7.widget.RecyclerView
import com.alancasasarevalo.commons.BaseListFragment
import com.alancasasarevalo.madridshops_android.R

class ShopsFragment : BaseListFragment() {
    override fun getAdapter(): RecyclerView.Adapter<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_shops
    }
}
