package com.alancasasarevalo.madridshops.domain.interactor.getallshops

import android.content.Context
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.model.Shop
import com.alancasasarevalo.madridshops.domain.model.Shops
import com.alancasasarevalo.madridshops.repository.RepositoryImplementation
import com.alancasasarevalo.madridshops.repository.RepositoryInterface
import com.alancasasarevalo.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetAllShopsInteractorImplementation (context: Context) : GetAllShopsInteractor {

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
            val shop = Shop(it.id.toInt(), it.name, it.address)


            tempList.add(shop)
        }

        val shops = Shops(tempList)
        return  shops
    }
}