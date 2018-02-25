package com.alancasasarevalo.madridshops_android.fragment


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.alancasasarevalo.commons.BaseFragment
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImplementation
import com.alancasasarevalo.madridshops.domain.model.Shops
import com.alancasasarevalo.madridshops_android.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class ShopMapFragment : BaseFragment() {

    private var googleMap: GoogleMap? = null

    override fun getLayoutResId(): Int {
        return R.layout.fragment_shop_map
    }

    private fun setupMapFragment() {

        val getAllShopsInteractor : GetAllShopsInteractor = GetAllShopsInteractorImplementation(activity)
        getAllShopsInteractor.execute(object : SuccessCompletion<Shops> {
            override fun successCompletion(element: Shops) {
//                initializeMap(element)
//                activity_main_view_switcher.displayedChild = VIEW_SWITCHER_INDEX.DOWNLOADED.index
            }

        }, object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(activity, "Error de carga", Toast.LENGTH_LONG).show()
            }

        })
    }


    // TODO: Habilitar el mapa
    private fun initializeMap(element: Shops) {
//        val mapFragment =  supportFragmentManager.findFragmentById(R.id.fragment_container) as SupportMapFragment
//        mapFragment.getMapAsync{
//            Log.d("MAPSUCCESS","Habemus Maps")
//
//            centerMapInPosition(it, 40.416775, -3.703790)
//            it.uiSettings.isRotateGesturesEnabled = false
//            it.uiSettings.isZoomControlsEnabled = true
//            showUserPosition(activity, it)
//
//            googleMap = it
//            addAllShopsPinToMap(element)
//        }
    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double){

        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition
                .builder()
                .target(coordinate)
                .zoom(15f)
                .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun showUserPosition(context: Context, map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 10)

            return
        }

        map.isMyLocationEnabled = true
    }

    fun addPinToMap(map: GoogleMap, latitude: Double, longitude: Double, title: String){

        val coordinate = LatLng(latitude, longitude)
        googleMap?.addMarker(MarkerOptions()
                .position(coordinate)
                .title(title)
        )

    }

    fun addAllShopsPinToMap (shops: Shops){
        for (i in 0 until shops.count()) {
            val shop = shops.get(i)
            addPinToMap(this.googleMap !!, 40.416775,-3.703790 , shop.name)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10){
            try {
                googleMap?.isMyLocationEnabled = true
            }catch (securityException: SecurityException){
                Log.d("Error de seguridad", securityException.localizedMessage)
            }
        }
    }



}
