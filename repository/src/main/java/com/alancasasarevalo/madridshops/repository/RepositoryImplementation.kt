package com.alancasasarevalo.madridshops.repository

import android.content.Context
import com.alancasasarevalo.madridshops.repository.cache.CacheImplementation
import com.alancasasarevalo.madridshops.repository.cache.CacheInterface
import com.alancasasarevalo.madridshops.repository.model.ActivityEntity
import com.alancasasarevalo.madridshops.repository.model.ShopEntity
import com.alancasasarevalo.madridshops.repository.model.ShopsResponseEntity
import com.alancasasarevalo.madridshops.repository.network.GetJSONManager
import com.alancasasarevalo.madridshops.repository.network.GetJsonManagerVolleyImplentation
import com.alancasasarevalo.madridshops.repository.network.json.JsonEntitiesParser
import com.alancasasarevalo.repository.BuildConfig
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import java.lang.ref.WeakReference

class RepositoryImplementation (context: Context) : RepositoryInterface {


    private val weakContext = WeakReference<Context>(context)
    private val cache: CacheInterface = CacheImplementation(weakContext.get()!!)

    override fun getAllShops(successCompletion: (element: List<ShopEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit) {
        //Read all shops from cache
        cache.getAllShops(
                successCompletion = {

                    //if there's shops in cache --> return shops.
                    successCompletion(it)

                }, errorCompletion = {

                    //If not shops in caches -->network
                    populateCache(successCompletion, errorCompletion)

                })

    }

    override fun getAllActivities(successCompletion: (element: List<ActivityEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit) {
        cache.getAllActivities(
                successCompletion = {

                    //if there's shops in cache --> return shops.
                    successCompletion(it)

                }, errorCompletion = {

            //If not shops in caches -->network
            populateCache(successCompletion, errorCompletion)

        })
    }

    private fun populateCache(successCompletion: (element: List<ShopEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit) {

        val jsonManager: GetJSONManager = GetJsonManagerVolleyImplentation(weakContext.get()!!)

        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL, successCompletion = object : SuccessCompletion<String>{
            override fun successCompletion(element: String) {
                //perform network request
                val parser = JsonEntitiesParser()

                var responseEntity: ShopsResponseEntity
                responseEntity = try {
                    parser.parse<ShopsResponseEntity>(element)
                } catch (e: InvalidFormatException) {
                    error("ERROR PARSING")
                    return
                }

                //store result in cache
                cache.saveAllShops(responseEntity.result, successCompletion = {
                    successCompletion(responseEntity.result)
                }, errorCompletion = {
                    errorCompletion("Something happened on the way to heaven!!!!")
                })

            }
        }, errorCompletion = object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {

            }
        })

    }

    override fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit) {
        cache.deleteAllShops(successCompletion, errorCompletion)
    }

    override fun deleteAllActivities(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun populateActivitiesCache(successCompletion: (element: List<ShopEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit) {

        val jsonManager: GetJSONManager = GetJsonManagerVolleyImplentation(weakContext.get()!!)

        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL, successCompletion = object : SuccessCompletion<String>{
            override fun successCompletion(element: String) {
                //perform network request
                val parser = JsonEntitiesParser()

                var responseEntity: ShopsResponseEntity
                responseEntity = try {
                    parser.parse<ShopsResponseEntity>(element)
                } catch (e: InvalidFormatException) {
                    error("ERROR PARSING")
                    return
                }

                //store result in cache
                cache.saveAllShops(responseEntity.result, successCompletion = {
                    successCompletion(responseEntity.result)
                }, errorCompletion = {
                    errorCompletion("Something happened on the way to heaven!!!!")
                })

            }
        }, errorCompletion = object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {

            }
        })

    }

}





























