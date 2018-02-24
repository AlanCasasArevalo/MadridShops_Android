package com.alancasasarevalo.madridshops.repository

import com.alancasasarevalo.madridshops.repository.model.ShopEntity
import com.alancasasarevalo.madridshops.repository.model.ShopsResponseEntity
import com.alancasasarevalo.madridshops.repository.network.json.JsonEntitiesParser
import com.alancasasarevalo.madridshops.util.ReadJsonFile
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import junit.framework.Assert.*
import org.junit.Test

class JSONParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_when_containing_json_then_it_parse_one_shop_correctly() {
        val shopJson = ReadJsonFile().loadJSONFromAsset("MadridShop.json")
        assertTrue(false == shopJson.isEmpty())
        assertTrue(!shopJson.isEmpty())

        //parseo
        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)
        assertEquals(40.4180563f, shop.latitude.toFloat(), 0.1f)

    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_when_containing_json_then_it_parse_shops_correctly_all_shops() {
        val shopJson = ReadJsonFile().loadJSONFromAsset("MadridShops.json")

        assertTrue(!shopJson.isEmpty())

        //parseo
        val parser = JsonEntitiesParser()
        val shops = parser.parse<ShopsResponseEntity>(shopJson)

        assertEquals(6, shops.result.count())
        assertEquals("Cortefiel - Preciados", shops.result[0].name)
        assertEquals(40.4180563f, shops.result[0].latitude.toFloat(), 0.1f)

        assertNotNull(shops)


    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_string_when_containing_json_with_wrong_latitude_then_it_parse_one_shop_correctly() {
        val shopJson = ReadJsonFile().loadJSONFromAsset("shopWrongLatitude.json")
        assertTrue(!shopJson.isEmpty())

        //parseo
        val parser = JsonEntitiesParser()
        var shop : ShopEntity

        shop = try {
            parser.parse<ShopEntity>(shopJson)
        }catch (e: InvalidFormatException){
            ShopEntity(1,1,"Parsing failed CRASH","",10f.toString(),10f.toString(),"","","","","","","","","","","","","","")
        }

        assertNotNull(shop)
        assertEquals("Parsing failed CRASH", shop.name)
        assertEquals(40.4180563f, shop.latitude.toFloat(), 0.1f)

    }


}