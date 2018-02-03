package com.alancasasarevalo.madridshops.repository

import com.alancasasarevalo.madridshops.repository.model.ShopEntity
import com.alancasasarevalo.madridshops.repository.network.json.JsonEntitiesParser
import com.alancasasarevalo.madridshops.util.ReadJsonFile
import junit.framework.Assert.*
import org.junit.Test

class JSONParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_when_containing_json_then_it_parses_correctly() {
        val shopJson = ReadJsonFile().loadJSONFromAsset("MadridShop.json")
//        assertTrue(false == shopJson.isEmpty())
        assertTrue(!shopJson.isEmpty())

        //parseo
        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)

    }

}