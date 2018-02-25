package com.alancasasarevalo.madridshops_android.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import com.alancasasarevalo.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImplementation
import com.alancasasarevalo.madridshops.domain.model.MadridActivities
import com.alancasasarevalo.madridshops.domain.model.Shops
import com.alancasasarevalo.madridshops_android.R
import com.alancasasarevalo.madridshops_android.fragment.ActivitiesFragment
import com.alancasasarevalo.madridshops_android.fragment.ShopsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class VIEW_SWITCHER_INDEX(val index : Int){
        LOADING(0),
        DOWNLOADED(1)
    }

    lateinit var shops: Shops
    lateinit var activities: MadridActivities

    private val fragments: HashMap<Int, Fragment> = hashMapOf(
            Pair(R.id.shops, ShopsFragment()),
            Pair(R.id.activities, ActivitiesFragment())
    )

    companion object {
        const val EXTRA_SHOPS = "EXTRA_SHOPS"
        const val EXTRA_ACTIVITIES = "EXTRA_ACTIVITIES"
        const val DEFAULT_OPTION_SELECTED = R.id.shops

        fun intent(context: Context, shops: Shops?, madridActivities: MadridActivities?): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_SHOPS, shops)
            intent.putExtra(EXTRA_ACTIVITIES, madridActivities)

            return intent
        }

    }

    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("App","onCreate de MainActivity")
//
//        activity_main_view_switcher.setInAnimation(this, android.R.anim.fade_in)
//
//        activity_main_view_switcher.setOutAnimation(this, android.R.anim.fade_out)

        navigation_view.selectedItemId = DEFAULT_OPTION_SELECTED
        navigation_view.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment? = fragments[item.itemId]

            if (fragment != null) {
                replaceErrorFragment(fragment)
            }

            true
        }



        // TODO:Hacer esto despues de que haya descargado toda la informacion de las actividades y las tiendas.
//        activity_main_view_switcher.displayedChild = VIEW_SWITCHER_INDEX.DOWNLOADED.index
//        activity_main_view_switcher.displayedChild = VIEW_SWITCHER_INDEX.LOADING.index





        setupMapFragment()

    }

    private fun setupMapFragment() {

        val getAllShopsInteractor : GetAllShopsInteractor = GetAllShopsInteractorImplementation(this)
        getAllShopsInteractor.execute(object : SuccessCompletion<Shops>{
            override fun successCompletion(element: Shops) {
//                initializeMap(element)
//                activity_main_view_switcher.displayedChild = VIEW_SWITCHER_INDEX.DOWNLOADED.index
            }

        }, object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error de carga", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun initializeMap(element: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as SupportMapFragment
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

    private fun replaceErrorFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

    private fun initViewWithDefaultFragment() {
        val currentFragment = supportFragmentManager
                .findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragments[DEFAULT_OPTION_SELECTED])
                    .commit()
        }
    }


}






















