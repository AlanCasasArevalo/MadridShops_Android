package com.alancasasarevalo.madridshops_android.activity

import android.os.Bundle
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
import com.alancasasarevalo.madridshops_android.fragment.MapFragment
import com.alancasasarevalo.madridshops_android.fragment.ShopListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mapFragment : MapFragment? = null
    var shopListFragment: ShopListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.d("App","onCreate de MainActivity")

        setupMapFragment()

        setupShopListFragment()

    }

    private fun setupShopListFragment() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as MapFragment

        val mapFragmentInmutable = mapFragment
        val getAllShopsInteractor : GetAllShopsInteractor = GetAllShopsInteractorImplementation(this)
        getAllShopsInteractor.execute(object : SuccessCompletion<Shops>{
            override fun successCompletion(element: Shops) {
                mapFragmentInmutable?.setShops(element)
            }

        }, object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error de carga", Toast.LENGTH_LONG).show()
            }

        })


    }

    private fun setupMapFragment() {
        shopListFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as ShopListFragment

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
