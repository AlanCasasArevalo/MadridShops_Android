package com.alancasasarevalo.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.alancasasarevalo.repository.db.DBHelper
import com.alancasasarevalo.repository.db.buildDBHelper
import com.alancasasarevalo.repository.db.dao.ShopDAO
import com.alancasasarevalo.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbhelper = buildDBHelper(appContext, "mydb.sqlite",1)

    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_gets_inserted_correctly() {
        // Context of the app under test.

        val shop = ShopEntity(1,
                1,
                "Shop1",
                "",
                1.0f,
                2.0f,
                "",
                "",
                "",
                "")

        val shopEntityDAO = ShopDAO(dbhelper)

        val id = shopEntityDAO.insert(shop)

        assertTrue(id > 0)
        assertTrue(-1 < id)

    }
}
