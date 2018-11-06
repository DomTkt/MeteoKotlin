package com.example.a727222.weatherapp.module

import android.content.Context
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.network.MockClient
import com.example.a727222.weatherapp.network.RestClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Module(context: Context) {
    private var context: Context
    private lateinit var networking: Networking

    init{
        this.context = context
    }

    @Provides
    @Singleton
    fun provideNetworker(): Networking {

        when(context.packageName)
        {
            "com.example.a727222.weatherapp.mock" -> networking = MockClient(context)
            "com.example.a727222.weatherapp.prod" -> networking = RestClient()
        }
        return networking
    }
}