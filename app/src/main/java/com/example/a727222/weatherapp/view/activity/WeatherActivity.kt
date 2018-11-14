package com.example.a727222.weatherapp.view.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ScrollView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.view.fragment.WeatherCurrentAdditionalDetailsFragment
import com.example.a727222.weatherapp.view.fragment.WeatherCurrentPrincipalDetailsFragment
import com.example.a727222.weatherapp.view.fragment.WeatherForecastListFragment
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener

class WeatherActivity : AppCompatActivity(){

//    private val locationListener: LocationListener = object : LocationListener {
//        override fun onLocationChanged(location: Location) {
//            println(location)
//        }
//        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
//        override fun onProviderEnabled(provider: String) {}
//        override fun onProviderDisabled(provider: String) {}
//    }

    private lateinit var scrollView : ScrollView
    private lateinit var constraintlayout : ConstraintLayout
    private var cityCurrent : String? =  null

//    private var locationManager : LocationManager? = null

    companion object {
        var WEATHER_ACTIVITY_ARGUMENTS : String = "WEATHER_ACTIVITY_ARGUMENTS"
        private var WEATHER_ACTIVITY_ARGUMENTS_ORIENTATION : String = "WEATHER_ACTIVITY_ARGUMENTS_ORIENTATION"

    }






//    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        scrollView = findViewById(R.id.weather_activity_root_layout)
        constraintlayout = findViewById(R.id.constraintLayout);
        hideActionBar();
        displaySearchBar()
        checkDataExistForChangeOrientation()
//        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?;
//        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)


    }


    fun hideActionBar(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    fun displayFragmentWeatherCurrentPrincipalDetails(args : Bundle){
        var fragmentWeatherCurrentPrincipalDetails : WeatherCurrentPrincipalDetailsFragment = WeatherCurrentPrincipalDetailsFragment.newInstance()
        fragmentWeatherCurrentPrincipalDetails.arguments = args
        supportFragmentManager.beginTransaction().replace(R.id.weather_current_principal_details_fragment, fragmentWeatherCurrentPrincipalDetails, WeatherCurrentPrincipalDetailsFragment.javaClass.name).commit()
    }
    fun displayFragmentWeatherCurrentPrincipalDetailsWeatherForecastListFragment(args : Bundle){
        var fragmentWeatherForecastListFragment : WeatherForecastListFragment = WeatherForecastListFragment.newInstance()
        fragmentWeatherForecastListFragment.arguments = args
        supportFragmentManager.beginTransaction().replace(R.id.weather_forecast_list_fragment_recyclerView, fragmentWeatherForecastListFragment, WeatherForecastListFragment.javaClass.name).commit();

    }
    fun displayFragmentWeatherCurrentAdditionalDetailsFragment(args : Bundle){
        var fragmentWeatherCurrentAdditionalDetailsFragment : WeatherCurrentAdditionalDetailsFragment = WeatherCurrentAdditionalDetailsFragment.newInstance()
        fragmentWeatherCurrentAdditionalDetailsFragment.arguments = args
        supportFragmentManager.beginTransaction().replace(R.id.weather_current_additional_details_fragment, fragmentWeatherCurrentAdditionalDetailsFragment, fragmentWeatherCurrentAdditionalDetailsFragment.javaClass.name).commit()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                val refresh = Intent(this, WeatherActivity::class.java)
                refresh.putExtra(WEATHER_ACTIVITY_ARGUMENTS_ORIENTATION,cityCurrent)
                startActivity(refresh)
                this.finish()

            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val refresh = Intent(this, WeatherActivity::class.java)
                refresh.putExtra(WEATHER_ACTIVITY_ARGUMENTS_ORIENTATION,cityCurrent)
                startActivity(refresh)
                this.finish()
            }
        }
    }

    fun displaySearchBar(){
        val autocompleteFragment = PlaceAutocompleteFragment()
        val fragmentManager = getFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.place_autocomplete_fragment, autocompleteFragment)
        fragmentTransaction.commit()

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                cityCurrent = place.name.toString()
                val args = Bundle()
                args.putString(WEATHER_ACTIVITY_ARGUMENTS,place.name.toString())
                displayFragmentWeatherCurrentPrincipalDetails(args)
                displayFragmentWeatherCurrentPrincipalDetailsWeatherForecastListFragment(args)
                displayFragmentWeatherCurrentAdditionalDetailsFragment(args)
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                println(status)
            }
        })
    }

    fun checkDataExistForChangeOrientation(){
        var bundle : Bundle? = intent.extras
        cityCurrent = bundle?.getString(WEATHER_ACTIVITY_ARGUMENTS_ORIENTATION)
        if(bundle!=null){
            val args = Bundle()
            args.putString(WEATHER_ACTIVITY_ARGUMENTS,cityCurrent)
            displayFragmentWeatherCurrentPrincipalDetails(args)
            displayFragmentWeatherCurrentPrincipalDetailsWeatherForecastListFragment(args)
            displayFragmentWeatherCurrentAdditionalDetailsFragment(args)
        }
    }
}
