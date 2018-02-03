package com.alancasasarevalo.madridshops.repository.network

import com.alancasasarevalo.madridshops.repository.ErrorCompletion
import com.alancasasarevalo.madridshops.repository.SuccessCompletion

interface GetJSONManager {
    fun execute(url: String, successCompletion: SuccessCompletion<String>, errorCompletion: ErrorCompletion)
}


