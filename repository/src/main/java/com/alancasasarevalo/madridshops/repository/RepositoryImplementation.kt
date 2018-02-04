package com.alancasasarevalo.madridshops.repository

import android.content.Context
import com.alancasasarevalo.madridshops.repository.cache.CacheImplementation
import com.alancasasarevalo.madridshops.repository.cache.CacheInterface
import java.lang.ref.WeakReference

class RepositoryImplementation (context: Context) : RepositoryInterface {
    val weakContext = WeakReference<Context>(context)
    internal val cache: CacheInterface = CacheImplementation(weakContext.get()!!)

    override fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: (errorMessage: String) -> Unit) {
        cache.deleteAllShops(successCompletion, errorCompletion)
    }
}