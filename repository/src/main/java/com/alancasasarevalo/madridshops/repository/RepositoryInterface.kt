package com.alancasasarevalo.madridshops.repository

interface RepositoryInterface {
    fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit)
}