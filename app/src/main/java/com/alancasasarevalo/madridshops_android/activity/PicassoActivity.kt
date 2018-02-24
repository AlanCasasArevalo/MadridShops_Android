package com.alancasasarevalo.madridshops_android.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alancasasarevalo.madridshops_android.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*
import kotlinx.android.synthetic.main.content_picasso.*

class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)
        setSupportActionBar(toolbar)

        //Esto se pone en el MadridShopsApp
        Picasso.with(this).setIndicatorsEnabled(true)
        Picasso.with(this)

        Picasso.with(this)
                .load("http://www.panorama.com.ve/__export/1513083935601/sites/panorama/img/pitoquito/2017/12/12/fotos-tigres.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(image1)

        Picasso.with(this)
                .load("https://cdn.elgrupoinformatico.com/img/w720/Noticias/2017/08/tinder-prohibe-los-selfies-con-tigres-720x360.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(image2)

        Picasso.with(this)
                .load("http://mundosalvaje.com.mx/wp-content/uploads/2017/09/Tigre-3-129102035.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(image3)

    }

}
