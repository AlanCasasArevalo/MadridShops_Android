package com.alancasasarevalo.madridshops.repository

import com.alancasasarevalo.madridshops.repository.model.ShopEntity

interface RepositoryInterface {
    fun getAllShops(successCompletion: (element: List<ShopEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit)
    fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit)
}