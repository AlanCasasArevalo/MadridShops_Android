package com.alancasasarevalo.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.alancasasarevalo.madridshops.repository.network.GetJSONManager
import com.alancasasarevalo.madridshops.repository.network.GetJsonManagerVolleyImplentation
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VolleyNetworkTest {

    val appContext = InstrumentationRegistry.getContext()

    @Test
    @Throws(Exception::class)
    fun given_valid_url_when_we_get_non_null_json_as_sttring_then_url_is_correct() {
        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager: GetJSONManager = GetJsonManagerVolleyImplentation(appContext)

        jsonManager.execute(url, object : SuccessCompletion<String>{
            override fun successCompletion(element: String) {
                assertTrue(element.isNotEmpty())
            }

        }, object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                assertTrue(false)
            }
        })

    }

}




































