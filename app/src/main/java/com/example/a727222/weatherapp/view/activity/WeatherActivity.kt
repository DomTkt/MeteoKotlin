package com.example.a727222.weatherapp.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ScrollView
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.utils.Utils
import com.example.a727222.weatherapp.view.fragment.WeatherCurrentAdditionalDetailsFragment
import com.example.a727222.weatherapp.view.fragment.WeatherCurrentPrincipalDetailsFragment
import com.example.a727222.weatherapp.view.fragment.WeatherForecastListFragment
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import io.fabric.sdk.android.Fabric

/**
 * WeatherActivity is a launch screen, display component as fragment search bar...
 */
class WeatherActivity : AppCompatActivity(){

    /**
     * scrollView allows to scroll
     */
    private lateinit var scrollView : ScrollView
    /**
     * cityCurrent city current of the user, get by her position
     */
    private var cityCurrent : String? =  null

    /**
     * GPS
     */
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    /**
     * args in Bundle
     */
    lateinit var args : Bundle


    companion object {
        const val  WEATHER_ACTIVITY_ARGUMENTS : String = "WEATHER_ACTIVITY_ARGUMENTS"
        const val WEATHER_ACTIVITY_ARGUMENTS_ORIENTATION : String = "WEATHER_ACTIVITY_ARGUMENTS_ORIENTATION"
        const val WEATHER_ACTIVITY_CITY_CURRENT : String = "WEATHER_ACTIVITY_CITY_CURRENT"
        private const val REQUEST_FINE_LOCATION = 123
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        Fabric.with(this, Crashlytics())
        scrollView = findViewById(R.id.weather_activity_root_layout)
        hideActionBar()
        displaySearchBar()
        checkDataExistForChangeOrientation()
    }

    /**
     * Method to hide action bar
     */
    fun hideActionBar(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    /**
     * Display the fragment which contains the data weather current principal details
     */
    fun displayFragmentWeatherCurrentPrincipalDetails(args : Bundle){
        var fragmentWeatherCurrentPrincipalDetails : WeatherCurrentPrincipalDetailsFragment = WeatherCurrentPrincipalDetailsFragment.newInstance()
        fragmentWeatherCurrentPrincipalDetails.arguments = args
        supportFragmentManager.beginTransaction().replace(R.id.weather_current_principal_details_fragment, fragmentWeatherCurrentPrincipalDetails, WeatherCurrentPrincipalDetailsFragment.javaClass.name).commit()
    }
    /**
     * Display the fragment which contains the data forecast
     */
    fun displayFragmentWeatherForecastListFragment(args : Bundle){
        var fragmentWeatherForecastListFragment : WeatherForecastListFragment = WeatherForecastListFragment.newInstance()
        fragmentWeatherForecastListFragment.arguments = args
        supportFragmentManager.beginTransaction().replace(R.id.weather_forecast_list_fragment_recyclerView, fragmentWeatherForecastListFragment, WeatherForecastListFragment.javaClass.name).commit();

    }
    /**
     * Display the fragment which contains the data weather current additional details
     */
    fun displayFragmentWeatherCurrentAdditionalDetailsFragment(args : Bundle){
        var fragmentWeatherCurrentAdditionalDetailsFragment : WeatherCurrentAdditionalDetailsFragment = WeatherCurrentAdditionalDetailsFragment.newInstance()
        fragmentWeatherCurrentAdditionalDetailsFragment.arguments = args
        supportFragmentManager.beginTransaction().replace(R.id.weather_current_additional_details_fragment, fragmentWeatherCurrentAdditionalDetailsFragment, fragmentWeatherCurrentAdditionalDetailsFragment.javaClass.name).commit()
    }

    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
            requestPermissions()
    }

    /**
     * event change orientation
     */
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

    /**
     * Display the search bar with auto complete Google for the city/country
     */
    fun displaySearchBar(){
        val autocompleteFragment = PlaceAutocompleteFragment()
        val fragmentManager = getFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.place_autocomplete_fragment, autocompleteFragment)
        fragmentTransaction.commit()

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                    cityCurrent = place.name.toString()
                    showWeather(cityCurrent)
                Answers.getInstance().logContentView(ContentViewEvent()
                        .putCustomAttribute("User search city", cityCurrent))

            }

            override fun onError(status: Status) {
                println(status)
                throw RuntimeException("This is a crash when selected place : "+ status.statusMessage)
            }
        })
    }

    /**
     * check if data exist when we change orientation
     */
    fun checkDataExistForChangeOrientation(){
        var bundle : Bundle? = intent.extras
        cityCurrent = bundle?.getString(WEATHER_ACTIVITY_ARGUMENTS_ORIENTATION)
        if(bundle!=null){
            val args = Bundle()
            args.putString(WEATHER_ACTIVITY_ARGUMENTS,cityCurrent)
            displayFragmentWeatherCurrentPrincipalDetails(args)
            displayFragmentWeatherForecastListFragment(args)
            displayFragmentWeatherCurrentAdditionalDetailsFragment(args)
        }
    }

    /**
     * permission for the GPS
     */
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FINE_LOCATION)
    }

    /**
     * permission event result
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_FINE_LOCATION -> {
                if ((grantResults.size != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    args = Bundle()
                    if (args.isEmpty) {
                        getLocalisation()
                    } else{
                    var city = args.getString(WEATHER_ACTIVITY_CITY_CURRENT)
                    showWeather(city)
                }

                } else {
                    println("Denied")
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocalisation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if(location!=null) {
                        var cityName = Utils.getCityNameFromLocation(this, location.latitude, location.longitude)
                        var args = Bundle()
                        args.putString(WEATHER_ACTIVITY_CITY_CURRENT, cityName)
                        showWeather(cityName)

                    }
                }
    }

    /**
     * Display all fragment
     */
    private fun showWeather(cityName : String?){
        val args = Bundle()
        args.putString(WEATHER_ACTIVITY_ARGUMENTS,cityName)
        displayFragmentWeatherCurrentPrincipalDetails(args)
        displayFragmentWeatherForecastListFragment(args)
        displayFragmentWeatherCurrentAdditionalDetailsFragment(args)
    }


}
