package com.alancasasarevalo.madridshops.repository

interface SuccessCompletion <T>{
    fun successCompletion( element: T )
}

interface ErrorCompletion {
    fun errorCompletion( errorMessage: String)
}



