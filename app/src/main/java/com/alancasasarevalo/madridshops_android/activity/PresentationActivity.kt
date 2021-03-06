package com.alancasasarevalo.madridshops_android.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.alancasasarevalo.madridshops_android.R
import kotlinx.android.synthetic.main.activity_shops.*

class PresentationActivity : AppCompatActivity() {

    companion object {
        fun intent (context: Context) : Intent {
            val intent = Intent(context, PresentationActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)

        shops_navigation_view.setOnNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId){
                R.id.presentation -> startActivity(PresentationActivity.intent(this))
                R.id.shops -> startActivity(ShopsActivity.intent(this))
                R.id.activities -> startActivity(ActivitiesActivity.intent(this))

                else -> startActivity(PresentationActivity.intent(this))
            }
            true
        }

    }

}
