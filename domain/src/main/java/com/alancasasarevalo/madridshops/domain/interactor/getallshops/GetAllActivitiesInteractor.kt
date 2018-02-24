package com.alancasasarevalo.madridshops.domain.interactor.getallshops

import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.model.MadridActivities

interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<MadridActivities>, error: ErrorCompletion)
}