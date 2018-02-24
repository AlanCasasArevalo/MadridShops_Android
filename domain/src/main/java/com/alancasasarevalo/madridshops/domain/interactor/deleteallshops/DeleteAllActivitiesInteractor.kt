package com.alancasasarevalo.madridshops.domain.interactor.deleteallshops

import com.alancasasarevalo.madridshops.domain.interactor.CodeClousure
import com.alancasasarevalo.madridshops.domain.interactor.ErrorClosure

interface DeleteAllActivitiesInteractor {
    fun execute(successClosure: CodeClousure, errorClosure: ErrorClosure)
}
