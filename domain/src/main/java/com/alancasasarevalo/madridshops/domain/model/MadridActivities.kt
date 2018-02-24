package com.alancasasarevalo.madridshops.domain.model

data class MadridActivity(val id: Int, val name: String, val address: String)

class MadridActivities(var activities: MutableList<MadridActivity>) : Aggregate <MadridActivity> {
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

