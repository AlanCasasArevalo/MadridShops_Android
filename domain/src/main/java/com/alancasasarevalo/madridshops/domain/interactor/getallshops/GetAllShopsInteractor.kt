package com.alancasasarevalo.madridshops.domain.interactor.getallshops

import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
}



