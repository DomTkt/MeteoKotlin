package com.example.a727222.weatherapp;

import com.example.a727222.weatherapp.models.ResponseData;
import com.example.a727222.weatherapp.models.WeatherCurrent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RestService {

    @GET("/login")
    Call<ResponseData> login(@Query("username") final String id,
                             @Query("pwd") final String pwd);

    @GET("/data/2.5/weather")
    Call<WeatherCurrent> getCurrentWeather(@Query("q") final String city,
                                           @Query("APPID") final String appId);

}
