package com.example.a727222.weatherapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.utils.Utils

class WeatherActivity : AppCompatActivity(){

    private lateinit var imageView: ImageView
    private lateinit var frameLayout : FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        imageView = findViewById(R.id.weather_current_image_background_imageView)
        frameLayout = findViewById(R.id.weather_activity_root_layout)
//        Picasso.get().load(Utils.getURLForResource(R.drawable.image_background_weather))
//                .fit()
//                .centerCrop()
//                .onlyScaleDown()
//                .into(imageView)

        Glide.with(applicationContext).load(Utils.getURLForResource(R.drawable.image_background_weather))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(imageView)
    }
}
