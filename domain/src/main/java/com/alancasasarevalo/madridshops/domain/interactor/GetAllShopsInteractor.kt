package com.alancasasarevalo.madridshops.domain.interactor

import com.alancasasarevalo.madridshops.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion <Shops>, error: ErrorCompletion)
}



