package com.alancasasarevalo.madridshops.domain.interactor.getallshops

import android.content.Context
import android.util.Log
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.model.Shop
import com.alancasasarevalo.madridshops.domain.model.Shops
import com.alancasasarevalo.madridshops.repository.RepositoryImplementation
import com.alancasasarevalo.madridshops.repository.RepositoryInterface
import com.alancasasarevalo.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetAllShopsInteractorImplementation (context: Context) : GetAllShopsInteractor  {

    val weakReference = WeakReference(context)

    private var repository : RepositoryInterface = RepositoryImplementation(weakReference.get()!!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {

        repository.getAllShops(successCompletion = {
            val shops:Shops = entityMapper(it)
            success.successCompletion(shops)
        }, errorCompletion = {
            error(it)
        })
    }

    private fun entityMapper(list : List<ShopEntity>): Shops{

        val tempList = ArrayList<Shop>()

        list.forEach{
            val shop = Shop(it.id.toInt(),
                    it.name,
                    it.img,
                    it.logoImg,

                    it.address,
                    it.url,
                    it.description,

                    getCorrectCoordinateComponent(it.latitude),
                    getCorrectCoordinateComponent(it.longitude),
                    it.openingHours
            )

            tempList.add(shop)
        }

        val shops = Shops(tempList)
        return  shops
    }

    private fun getCorrectCoordinateComponent(coordinateComponent: String): String {
        var coordinate = 0.0f
        val s = coordinateComponent.replace(",", "")
        try {
            coordinate = java.lang.Float.parseFloat(s)
        } catch (e: Exception) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent))
        }
        return coordinate.toString()
    }

}