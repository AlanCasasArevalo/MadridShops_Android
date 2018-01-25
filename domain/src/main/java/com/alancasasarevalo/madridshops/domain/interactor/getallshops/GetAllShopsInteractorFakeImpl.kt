package com.alancasasarevalo.madridshops.domain.interactor.getallshops

import com.alancasasarevalo.madridshops.domain.interactor.ErrorClosure
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessClosure
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.model.Shop
import com.alancasasarevalo.madridshops.domain.model.Shops

class GetAllShopsInteractorFakeImpl : GetAllShopsInteractor {
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        var allOk = false

        // Connect to the repository

        if (allOk){
            val shops = createFakeListOfShops()

            success.successCompletion(shops)
        }else{
            error.errorCompletion("Error while accessing the Repository")
        }

    }

    fun execute (success : SuccessClosure, error: ErrorClosure) {
        var allOk = false

        if (allOk){
            val shops = createFakeListOfShops()

            success(shops)
        }else{
            error("Error while accessing the Repository")
        }
    }

    fun createFakeListOfShops(): Shops{
        val list = ArrayList<Shop>()

//        for (i in 0..100){
//            val shop = Shop(i, "Shop " + i , "Address  " + i )
//            list.add(shop)
//        }
//

        (0..100).forEach { i ->
            val shop = Shop(i, "Shop $i" , "Address $i y esta en ${list.size}" )
            list.add(shop)
        }

        val shops = Shops(list)

        return shops
    }
}