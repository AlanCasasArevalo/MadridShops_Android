package com.alancasasarevalo.madridshops.domain.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class MadridActivities(val activities: MutableList<MadridActivity>) : Aggregate<MadridActivity>, Parcelable {
    override fun count(): Int {
        return activities.size
    }

    override fun all(): List<MadridActivity> {
        return activities
    }

    override fun get(position: Int): MadridActivity {
        return activities[position]
    }

    override fun add(element: MadridActivity) {
        activities.add(element)
    }

    override fun delete(position: Int) {
        activities.removeAt(position)
    }


    override fun delete(element: MadridActivity) {
        activities.remove(element)
    }
}