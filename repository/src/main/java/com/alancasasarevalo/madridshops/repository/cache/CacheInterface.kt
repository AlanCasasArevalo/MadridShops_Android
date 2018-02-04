package com.alancasasarevalo.madridshops.repository.cache

internal interface CacheInterface {
    fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit)
}