package com.alancasasarevalo.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.alancasasarevalo.madridshops.repository.db.buildDBHelper
import com.alancasasarevalo.madridshops.repository.db.dao.ShopDAO
import com.alancasasarevalo.madridshops.repository.model.ShopEntity
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShopDAOTests {
    val appContext = InstrumentationRegistry.getTargetContext()
    internal val dbhelper = buildDBHelper(appContext, "mydb.sqlite",1)

    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_gets_inserted_correctly() {
        // Context of the app under test.

        val shopEntityDAO = ShopDAO(dbhelper)
        shopEntityDAO.deleteAll()
        val shop = ShopEntity(1,
                1,
                "Shop1",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")


        val id = shopEntityDAO.insert(shop)

        assertTrue(id > 0)
        assertTrue(-1 < id)

    }

    @Test
    @Throws(Exception::class)
    fun given_validShopEntityDAO_when_weUseQuery_them_queryGiveUsAllShops() {
    val shopEntityDAO = ShopDAO(dbhelper)

    shopEntityDAO.deleteAll()

    val shop = ShopEntity(1,
                2,
                "Shop1",
                "",
            "1.0f",
            "2.0f",
                "",
                "",
                "",
                "")

        val shop2 = ShopEntity(2,
                3,
                "Shop2",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")

        val shop3 = ShopEntity(3,
                4,
                "Shop3",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")


        val id = shopEntityDAO.insert(shop)
        val id2 = shopEntityDAO.insert(shop2)
        val id3 = shopEntityDAO.insert(shop3)

        shopEntityDAO.query().forEach{
            Log.d("Shops", it.name)
        }

    }

    @Test
    @Throws(Exception::class)
    fun given_validShopEntityDAO_when_weUseDeleteMethod_them_queryGiveUs0Shops() {

        val shopEntityDAO = ShopDAO(dbhelper)

        shopEntityDAO.deleteAll()
        val shop = ShopEntity(1,
                2,
                "Shop1",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")

        val shop2 = ShopEntity(2,
                3,
                "Shop2",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")

        val shop3 = ShopEntity(3,
                4,
                "Shop3",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")


        val id = shopEntityDAO.insert(shop)
        val id2 = shopEntityDAO.insert(shop2)
        val id3 = shopEntityDAO.insert(shop3)

        shopEntityDAO.query().forEach{
            Log.d("Shops", it.name)
        }


        shopEntityDAO.delete(id)

    }

    @Test
    @Throws(Exception::class)
    fun given_shopDAO_when_queryWithId_then_it_return_a_shopEntity() {
        val shopEntityDAO = ShopDAO(dbhelper)
        val shop3 = ShopEntity(3,
                4,
                "Shop3",
                "",
                "1.0f",
                "2.0f",
                "",
                "",
                "",
                "")
        val id = shopEntityDAO.insert(shop3)
        val query = shopEntityDAO.query(1)
        assertTrue(query.name == "Shop3")
    }


}




































