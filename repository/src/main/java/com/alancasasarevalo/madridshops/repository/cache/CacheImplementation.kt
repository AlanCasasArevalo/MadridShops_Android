package com.alancasasarevalo.madridshops.repository.cache

import android.content.Context
import com.alancasasarevalo.madridshops.repository.db.DBHelper
import com.alancasasarevalo.madridshops.repository.db.buildDBHelper
import com.alancasasarevalo.madridshops.repository.db.dao.ShopDAO
import com.alancasasarevalo.madridshops.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference

internal class CacheImplementation(context: Context) :CacheInterface {

    val context = WeakReference<Context>(context)

    override fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit) {

        Thread(Runnable {
            var successDeleting = ShopDAO(cacheDBHelper()).deleteAll()

            DispatchOnMainThread(Runnable {
                if (successDeleting){
                    successCompletion()
                }else{
                    errorCompletion("Error deleting")
                }
            })

        }).run()

    }

    private fun cacheDBHelper(): DBHelper {
        return  buildDBHelper(context.get()!!, "MadridShops.sqlite", 1)
    }

}








