package com.alancasasarevalo.madridshops_android.fragment


import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.alancasasarevalo.commons.BaseListFragment
import com.alancasasarevalo.databinding.DataBindingRecyclerAdapter
import com.alancasasarevalo.madridshops.domain.model.MadridActivity
import com.alancasasarevalo.madridshops_android.BR
import com.alancasasarevalo.madridshops_android.R

class ActivitiesListFragment : BaseListFragment() {

    override fun getAdapter(): RecyclerView.Adapter<*> {
        val adapter = DataBindingRecyclerAdapter<MadridActivity>(BR.activity, R.layout.item_activity)
        return adapter
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (listAdapter as DataBindingRecyclerAdapter<MadridActivity>).items.addAll(getDummyActivities())
        listAdapter.notifyDataSetChanged()

    }

    fun getDummyActivities() : ArrayList<MadridActivity>{

        var dummyArrayActivity = ArrayList<MadridActivity>()

        (0..9).forEach { i ->
            val dummyActivity = MadridActivity(1,
                    "Activity $i",
                    "http://madrid-shops.com/json_new/getShops.php",
                    "https://madrid-shops.com/media/shops/logo-cortefiel-200.jpg",
                    "Activity $i",
                    "Activity $i",
                    "Activity $i",
                    "40.4180563",
                    "-3.7010172999999895",
                    "Activity $i"
            )

            dummyArrayActivity.add(dummyActivity)
        }

        return dummyArrayActivity

    }
}