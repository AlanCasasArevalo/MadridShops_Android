package com.alancasasarevalo.madridshops_android.fragment


import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.alancasasarevalo.commons.BaseListFragment
import com.alancasasarevalo.databinding.DataBindingRecyclerAdapter
import com.alancasasarevalo.madridshops.domain.model.Shop
import com.alancasasarevalo.madridshops_android.BR
import com.alancasasarevalo.madridshops_android.R

class ShopListFragment : BaseListFragment() {

    companion object {

        fun newInstance() : ShopListFragment{
            val fragment = ShopListFragment()
            return fragment
        }
    }

    override fun getAdapter(): RecyclerView.Adapter<*> {
        val adapter = DataBindingRecyclerAdapter<Shop>(BR.shop, R.layout.item_shop)
        return adapter
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (listAdapter as DataBindingRecyclerAdapter<Shop>).items.addAll(getDummyShops())
        listAdapter.notifyDataSetChanged()

    }

    fun getDummyShops() : ArrayList<Shop>{

        var dummyArrayShop = ArrayList<Shop>()

        (0..9).forEach { i ->
            val dummyShop = Shop(1,
                    "Shop $i",
                    "http://madrid-shops.com/json_new/getShops.php",
                    "https://madrid-shops.com/media/shops/logo-cortefiel-200.jpg",
                    "Shop $i",
                    "Shop $i",
                    "Shop $i",
                    "40.4180563",
                    "-3.7010172999999895",
                    "Shop $i"
            )

            dummyArrayShop.add(dummyShop)
        }

        return dummyArrayShop

    }}
