package com.alancasasarevalo.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.alancasasarevalo.madridshops.repository.db.buildDBHelper
import com.alancasasarevalo.madridshops.repository.db.dao.ActivityDAO
import com.alancasasarevalo.madridshops.repository.model.ActivityEntity
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityDAOTests {
    val appContext = InstrumentationRegistry.getTargetContext()
    internal val dbhelper = buildDBHelper(appContext, "mydb.sqlite",1)

    @Test
    @Throws(Exception::class)
    fun given_valid_activity_entity_it_gets_inserted_correctly() {
        // Context of the app under test.

        val activityEntityDAO = ActivityDAO(dbhelper)
        activityEntityDAO.deleteAll()
        val activity = ActivityEntity(1,
                1,
                "activity1",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")


        val id = activityEntityDAO.insert(activity)

        Assert.assertTrue(id > 0)
        Assert.assertTrue(-1 < id)

    }

    @Test
    @Throws(Exception::class)
    fun given_validactivityEntityDAO_when_weUseQuery_them_queryGiveUsAllactivitys() {
        val activityEntityDAO = ActivityDAO(dbhelper)

        activityEntityDAO.deleteAll()

        val activity = ActivityEntity(1,
                2,
                "activity1",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")

        val activity2 = ActivityEntity(2,
                3,
                "activity2",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")

        val activity3 = ActivityEntity(3,
                4,
                "activity3",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")


        val id = activityEntityDAO.insert(activity)
        val id2 = activityEntityDAO.insert(activity2)
        val id3 = activityEntityDAO.insert(activity3)

        activityEntityDAO.query().forEach{
            Log.d("Activity", it.name)
        }

    }

    @Test
    @Throws(Exception::class)
    fun given_valid_activity_entityDAO_when_weUseDeleteMethod_them_queryGiveUs0activity() {

        val activityEntityDAO = ActivityDAO(dbhelper)

        activityEntityDAO.deleteAll()
        val activity = ActivityEntity(1,
                2,
                "activity",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")

        val activity2 = ActivityEntity(2,
                3,
                "activity",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")

        val activity3 = ActivityEntity(3,
                4,
                "activity",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")


        val id = activityEntityDAO.insert(activity)
        val id2 = activityEntityDAO.insert(activity2)
        val id3 = activityEntityDAO.insert(activity3)

        activityEntityDAO.query().forEach{
            Log.d("Activity3", it.name)
        }


        activityEntityDAO.delete(id)

    }

    @Test
    @Throws(Exception::class)
    fun given_activityAO_when_queryWithId_then_it_return_a_activityEntity() {
        val activityEntityDAO = ActivityDAO(dbhelper)
        val activity = ActivityEntity(3,
                4,
                "Activity3",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")
        val id = activityEntityDAO.insert(activity)
        val query = activityEntityDAO.query(1)
        Assert.assertTrue(query.name == "activity3")
    }


}




































