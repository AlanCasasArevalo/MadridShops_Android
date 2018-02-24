package com.alancasasarevalo.madridshops.domain.interactor.getallshops

import android.content.Context
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.model.MadridActivities
import com.alancasasarevalo.madridshops.domain.model.MadridActivity
import com.alancasasarevalo.madridshops.repository.RepositoryImplementation
import com.alancasasarevalo.madridshops.repository.RepositoryInterface
import com.alancasasarevalo.madridshops.repository.model.ActivityEntity
import java.lang.ref.WeakReference

class GetAllActivitiesInteractorImplementation (context: Context) : GetAllActivitiesInteractor  {

    val weakReference = WeakReference(context)
    private var repository : RepositoryInterface = RepositoryImplementation(weakReference.get()!!)

    override fun execute(success: SuccessCompletion<MadridActivities>, error: ErrorCompletion) {

        repository.getAllActivities(successCompletion = {
            val activities: MadridActivities = entityMapper(it)
            success.successCompletion(activities)
        }, errorCompletion = {
            error(it)
        })
    }

    private fun entityMapper(list : List<ActivityEntity>): MadridActivities {

        val tempList = ArrayList<MadridActivity>()

        list.forEach{
            val activity = MadridActivity(it.id.toInt(), it.name, it.address)


            tempList.add(activity)
        }

        val activities = MadridActivities(tempList)
        return  activities
    }
}