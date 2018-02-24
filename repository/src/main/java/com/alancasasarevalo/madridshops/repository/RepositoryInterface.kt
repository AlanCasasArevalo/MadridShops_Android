package com.alancasasarevalo.madridshops.repository

import com.alancasasarevalo.madridshops.repository.model.ActivityEntity
import com.alancasasarevalo.madridshops.repository.model.ShopEntity

interface RepositoryInterface {
    fun getAllShops(successCompletion: (element: List<ShopEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit)
    fun getAllActivities(successCompletion: (element: List<ActivityEntity>) -> Unit, errorCompletion: (errorMessage: String) -> Unit)
    fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit)
    fun deleteAllActivities(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit)
}