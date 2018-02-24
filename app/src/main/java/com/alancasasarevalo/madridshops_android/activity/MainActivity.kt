package com.alancasasarevalo.madridshops_android.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImplementation
import com.alancasasarevalo.madridshops.domain.model.Shops
import com.alancasasarevalo.madridshops_android.R
import com.alancasasarevalo.madridshops_android.fragment.ShopListFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var googleMap: GoogleMap? = null
    var shopListFragment: ShopListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.d("App","onCreate de MainActivity")

        setupMapFragment()

        if (fragmentManager.findFragmentById(R.id.activity_main_shop_list_fragment) == null){
            fragmentManager.beginTransaction()
                    .add(R.id.activity_main_shop_list_fragment,ShopListFragment.newInstance())
                    .commit()
        }

//        shopListFragment = fragmentManager.findFragmentById(R.id.activity_main_shop_list_fragment) as? ShopListFragment

    }

    private fun setupMapFragment() {

        val getAllShopsInteractor : GetAllShopsInteractor = GetAllShopsInteractorImplementation(this)
        getAllShopsInteractor.execute(object : SuccessCompletion<Shops>{
            override fun successCompletion(element: Shops) {
                initializeMap(element)
            }

        }, object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error de carga", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun initializeMap(element: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync{
            Log.d("MAPSUCCESS","Habemus Maps")

            centerMapInPosition(it, 40.416775, -3.703790)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, it)

            googleMap = it
            addAllShopsPinToMap(element)
        }
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

    fun showUserPosition(context:Context, map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 10)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}






















