package com.alancasasarevalo.madridshops.domain.interactor.internetstatus

import com.alancasasarevalo.madridshops.domain.interactor.CodeClousure
import com.alancasasarevalo.madridshops.domain.interactor.ErrorClosure

class InterenetStatusInteractorImpl : InterenetStatusInteractor {
    // TODO: comprobar realmente la conexion de internete
    override fun execute(success: CodeClousure, error: ErrorClosure) {
        success()
    }
}