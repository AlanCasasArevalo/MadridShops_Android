package com.alancasasarevalo.madridshops_android.router

import android.content.Intent
import com.alancasasarevalo.madridshops_android.activity.MainActivity
import com.alancasasarevalo.madridshops_android.activity.PicassoActivity

class Router{
    fun navigationFromMainActivityToPicassoActivity(mainActivity: MainActivity){
        mainActivity.startActivity(Intent(mainActivity, PicassoActivity::class.java))
    }
}