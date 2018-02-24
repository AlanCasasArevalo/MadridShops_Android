package com.alancasasarevalo.madridshops.domain.interactor

interface SuccessCompletion <in T>{
    fun successCompletion( element: T )
}

interface ErrorCompletion {
    fun errorCompletion( errorMessage: String)
}



