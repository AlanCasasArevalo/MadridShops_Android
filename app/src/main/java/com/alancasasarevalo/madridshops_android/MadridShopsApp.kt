package com.alancasasarevalo.madridshops_android

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.model.Shops

class MadridShopsApp: MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        //codigo de iniciacion de la aplicacion

        Log.d("App","onCreate de MadridShopsApp")

        val allShopsInteractor = GetAllShopsInteractorFakeImpl()

//        //Kotlin Style
//        allShopsInteractor.execute(success = { shops: Shops ->
//
//        }, error = { msg: String ->
//
//        })
//
        //Kotlin Style
        allShopsInteractor.execute({ shops: Shops ->

        }, { msg: String ->

        })


        //Java Style.
        allShopsInteractor.execute(object : SuccessCompletion<Shops>{
            override fun successCompletion(shops: Shops) {

                Log.d("allShopsInteractor", "Numero de tiendas ${shops.count()}")

            }
        } , object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })
    }

    override fun onLowMemory() {
        super.onLowMemory()

    }

}









