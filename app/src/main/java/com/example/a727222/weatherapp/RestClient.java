package com.example.a727222.weatherapp;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public final class RestClient {

    private static RestService mRestService = null;


    public static RestService getClient(Context context) {
        if (mRestService == null) {
            final OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new FakeInterceptor(context))
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://mock")
                    .client(client)
                    .build();

            mRestService = retrofit.create(RestService.class);
        }
        return mRestService;
    }
}