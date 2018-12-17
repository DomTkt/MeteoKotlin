package com.example.a727222.weatherapp.module

import android.content.Context
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.interfaces.NetworkingRx
import com.example.a727222.weatherapp.network.ApiServiceRx
import com.example.a727222.weatherapp.network.ApiServiceRxMock
import com.example.a727222.weatherapp.network.MockClient
import com.example.a727222.weatherapp.network.RestClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This class does injection processing by creating unique instances of objects call
 *
 */

@Module
open class ModuleBase(context: Context) {
    private var context: Context

    init{
        this.context = context
    }

    @Provides
    @Singleton
    fun provideNetworker(): Networking {
        when(context.packageName)
        {
            "com.example.a727222.weatherapp.mock" -> return MockClient(context)
            "com.example.a727222.weatherapp.prod" -> return RestClient()
            else -> return MockClient(context)
        }
    }

    /**
     * Create instance of ApiServiceRxMock if the build variant is in mock
     * Create instance of ApiServiceRx if the build variant is in prod
     * Else return an instance of ApiServiceMock
     */
    @Provides
    @Singleton
    fun provideNetworkerRx(): NetworkingRx {
        when(context.packageName)
        {
            "com.example.a727222.weatherapp.mock" -> return ApiServiceRxMock(context)
            "com.example.a727222.weatherapp.prod" -> return ApiServiceRx(context)
            else -> return ApiServiceRxMock(context)
        }
    }
}