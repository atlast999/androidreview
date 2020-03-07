package com.example.weatherapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.utility.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String TAG = "Repository";
    private static Repository instance;
    private WeatherService service;
    public static Repository getInstance() {
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(WeatherService.class);
    }

    public MutableLiveData<Weather> getWeather(String cityId){
        final MutableLiveData<Weather> data = new MutableLiveData<>();
        service.getWeather(cityId, "10ce7a8b71d274aae09250389c394f2d", "metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Log.d(TAG, "onResponse: ");
                Weather weather = Util.formatWeather(response.body());
                data.setValue(weather);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return data;
    }
}
