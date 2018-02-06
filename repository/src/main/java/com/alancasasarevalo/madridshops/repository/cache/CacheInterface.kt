package com.alancasasarevalo.madridshops.repository.cache

import com.alancasasarevalo.madridshops.repository.model.ShopEntity

internal interface CacheInterface {

    fun getAllShops(successCompletion: (element: List<ShopEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit)
    fun saveAllShops( shops: List<ShopEntity>, successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit )
    fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit)

}