package com.alancasasarevalo.madridshops.repository.cache

import android.content.Context
import com.alancasasarevalo.madridshops.repository.db.DBHelper
import com.alancasasarevalo.madridshops.repository.db.buildDBHelper
import com.alancasasarevalo.madridshops.repository.db.dao.ShopDAO
import com.alancasasarevalo.madridshops.repository.model.ShopEntity
import com.alancasasarevalo.madridshops.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference

internal class CacheImplementation(context: Context) :CacheInterface {

    private val context = WeakReference<Context>(context)

    override fun getAllShops(successCompletion: (element: List<ShopEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit) {

        Thread(Runnable {

            var shops = ShopDAO(cacheDBHelper()).query()

            DispatchOnMainThread(Runnable {
                if (shops.count() > 0){
                    successCompletion(shops)
                }else{
                    errorCompletion("Error to query shops")
                }
            })

        }).run()
    }

    override fun saveAllShops(shops: List<ShopEntity>, successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit) {
        Thread(Runnable {

            try {
                shops.forEach{ ShopDAO(cacheDBHelper()).insert(it) }
                DispatchOnMainThread(Runnable{
                    successCompletion()
                })

            }catch(e: Exception){
                DispatchOnMainThread(Runnable {
                    errorCompletion("Error inserting shops")
                })
            }

        }).run()
    }

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








