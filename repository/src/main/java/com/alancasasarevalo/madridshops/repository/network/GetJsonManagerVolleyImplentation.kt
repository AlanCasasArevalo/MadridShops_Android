package com.alancasasarevalo.madridshops.repository.network

import android.content.Context
import android.util.Log
import com.alancasasarevalo.madridshops.repository.ErrorCompletion
import com.alancasasarevalo.madridshops.repository.SuccessCompletion
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.lang.ref.WeakReference

class GetJsonManagerVolleyImplentation(context: Context) : GetJSONManager{

    var weakContext: WeakReference<Context> = WeakReference(context)
    var requestQueue: RequestQueue? = null

    override fun execute(url: String, successCompletion: SuccessCompletion<String>, errorCompletion: ErrorCompletion) {
        // create request (success, failure)
        val request = StringRequest(url,
        Response.Listener {

            Log.d("JSON", it)

            successCompletion.successCompletion(it)

        }, Response.ErrorListener {

            errorCompletion.errorCompletion(it.localizedMessage)

        })

        // add request to queue
        requestQueue().add(request)
    }

    //get request queue
    fun requestQueue() : RequestQueue{
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(weakContext.get())
        }
        return requestQueue!!
    }

}





































