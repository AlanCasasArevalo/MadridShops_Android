package com.alancasasarevalo.madridshops.domain.interactor.internetstatus

import com.alancasasarevalo.madridshops.domain.interactor.CodeClousure
import com.alancasasarevalo.madridshops.domain.interactor.ErrorClosure

interface InterenetStatusInteractor {
    fun execute(success: CodeClousure, error: ErrorClosure)
}