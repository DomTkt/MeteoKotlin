package com.example.a727222.weatherapp.daggerTestDeleteAfter

import android.app.Application
import com.example.a727222.weatherapp.view.WeatherCurrentAdditionalDetailsFragment

class MyApplication : Application() {
    var myComponent: MyComponent? = null

    override fun onCreate() {
        super.onCreate()
        myComponent = createMyComponent()
        createMyComponentTest()

    }

    //TODO SURCHARGER ... pour le lancement de l'activité principale

    private fun createMyComponent(): MyComponent {
        return DaggerMyComponent
                .builder()
                .myModule(MyModule(context = applicationContext))
                .build()
    }

     fun createMyComponentTest() {
         DaggerMyComponent
                 .builder()
                 .myModule(MyModule(context = applicationContext))
                 .build().plus(WeatherCurrentAdditionalDetailsFragment.newInstance())
    }
}