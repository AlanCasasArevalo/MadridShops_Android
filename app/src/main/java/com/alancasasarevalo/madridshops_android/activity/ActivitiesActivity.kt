package com.alancasasarevalo.madridshops_android.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.alancasasarevalo.madridshops_android.R
import com.alancasasarevalo.madridshops_android.fragment.ActivitiesListFragment
import kotlinx.android.synthetic.main.activity_shops.*

class ActivitiesActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ACTIVITY= "EXTRA_ACTIVITY"

//        fun intent (context: Context, activities: MadridActivities) : Intent {
        fun intent (context: Context) : Intent {
            val intent = Intent(context, ActivitiesActivity::class.java)
//            intent.putExtra(EXTRA_ACTIVITY, activities)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        shops_navigation_view.setOnNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId){
                R.id.presentation -> startActivity(PresentationActivity.intent(this))
                R.id.shops -> startActivity(ShopsActivity.intent(this))
                R.id.activities -> startActivity(ActivitiesActivity.intent(this))

                else -> startActivity(PresentationActivity.intent(this))
            }
            true
        }

        val fragment = supportFragmentManager.findFragmentById(R.id.activity_activities_fragment_list) as ActivitiesListFragment



    }

}




























