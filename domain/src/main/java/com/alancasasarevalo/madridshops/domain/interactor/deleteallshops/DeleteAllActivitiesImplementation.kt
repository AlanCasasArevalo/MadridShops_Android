package com.alancasasarevalo.madridshops.domain.interactor.deleteallshops

import android.content.Context
import com.alancasasarevalo.madridshops.domain.interactor.CodeClousure
import com.alancasasarevalo.madridshops.domain.interactor.ErrorClosure
import com.alancasasarevalo.madridshops.repository.RepositoryImplementation
import java.lang.ref.WeakReference

class DeleteAllActivitiesImplementation (context: Context) : DeleteAllActivitiesInteractor{

    var weakContext = WeakReference<Context>(context)

    override fun execute(successClosure: CodeClousure, errorClosure: ErrorClosure) {
        val repository = RepositoryImplementation(weakContext.get()!!)

        repository.deleteAllActivities(successClosure, errorClosure)
    }
}