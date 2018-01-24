package com.alancasasarevalo.madridshops.domain.interactor

import com.alancasasarevalo.madridshops.domain.model.Shop
import com.alancasasarevalo.madridshops.domain.model.Shops

class GetAllShopsInteractorFakeImpl : GetAllShopsInteractor{
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

    fun createFakeListOfShops(): Shops{
        val list = ArrayList<Shop>()

        for (i in 0..100){
            val shop = Shop(i, "Shop " + i, "Address " + 1)
            list.add(shop)
        }

        val shops = Shops(list)

        return shops
    }
}