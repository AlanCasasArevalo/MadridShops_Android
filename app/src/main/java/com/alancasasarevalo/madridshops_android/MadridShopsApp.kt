package com.alancasasarevalo.madridshops_android

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllActivitiesInteractorImplementation
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImplementation
import com.alancasasarevalo.madridshops.domain.model.MadridActivities
import com.alancasasarevalo.madridshops.domain.model.Shops

class MadridShopsApp: MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        //codigo de iniciacion de la aplicacion

        Log.d("App","onCreate de MadridShopsApp")


        val allActivitiesInteractor = GetAllActivitiesInteractorImplementation(this)

        val allShopsInteractor = GetAllShopsInteractorImplementation(this)

        //Java Style.
        allShopsInteractor.execute(object : SuccessCompletion<Shops>{
            override fun successCompletion(shops: Shops) {

                Log.d("allShopsInteractor", "Numero de tiendas ${shops.count()}")

                shops.shops.forEach{
                    Log.d("Shop", "${it.name}  ${shops.shops.size} ")
                }
            }
        } , object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d("Error en implementation",errorMessage)
            }
        })

        allActivitiesInteractor.execute(object : SuccessCompletion<MadridActivities>{
            override fun successCompletion(element: MadridActivities) {
                element.activities.forEach {
                    Log.d("Activities", "${it.name} ${element.activities.size}")
                }
            }

        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }

        })

    }

    override fun onLowMemory() {
        super.onLowMemory()

    }

}









