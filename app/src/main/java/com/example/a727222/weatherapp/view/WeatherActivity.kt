package com.example.a727222.weatherapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.utils.Utils
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener




class WeatherActivity : AppCompatActivity(){

    private lateinit var imageView: ImageView

    companion object {
        var WEATHER_ACTIVITY_ARGUMENTS : String = "WEATHER_ACTIVITY_ARGUMENTS"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        imageView = findViewById(R.id.weather_current_image_background_imageView)
        hideActionBar();
        val autocompleteFragment = fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment


        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
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


        loadBackground();
    }

    fun loadBackground(){
        Glide.with(applicationContext).load(Utils.getURLForResource(R.drawable.image_background_weather))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(imageView)
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



}
