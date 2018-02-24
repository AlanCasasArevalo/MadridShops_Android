package com.alancasasarevalo.madridshops_android

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImplementation
import com.alancasasarevalo.madridshops.domain.model.Shops

class MadridShopsApp: MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        //codigo de iniciacion de la aplicacion

        Log.d("App","onCreate de MadridShopsApp")


        val allShopsInteractor = GetAllShopsInteractorImplementation(this)

//        //Kotlin Style
//        allShopsInteractor.execute(success = { activities: Shops ->
//
//        }, error = { msg: String ->
//
//        })
//
        //Kotlin Style
//        allShopsInteractor.execute({ activities: Shops ->
//
//        }, { msg: String ->
//
//        })
//

        Log.d("AppBuildConfig", BuildConfig.MADRID_SHOPS_SERVER_URL)


        //Java Style.
        allShopsInteractor.execute(object : SuccessCompletion<Shops>{
            override fun successCompletion(shops: Shops) {

                Log.d("allShopsInteractor", "Numero de tiendas ${shops.count()}")

                shops.shops.forEach{
                    Log.d("Shop", it.name)
                }
            }
        } , object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d("Error en implementation",errorMessage)
            }
        })

//        DeleteAllShopsImplementation(this).execute(successClosure = {
//            Log.d("Success", "All activities were deleted")
//        }, errorClosure = {
//            Log.d("Error","All activities did not were deleted")
//        })

    }

    override fun onLowMemory() {
        super.onLowMemory()

    }

}









