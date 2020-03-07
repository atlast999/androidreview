package com.example.weatherapp.repository;

import com.example.weatherapp.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    //id=1581130&appid=10ce7a8b71d274aae09250389c394f2d&units=metric
    @GET("data/2.5/weather/")
    Call<Weather> getWeather(@Query("id") String cityId, @Query("appid") String appid, @Query("units") String units);
}
