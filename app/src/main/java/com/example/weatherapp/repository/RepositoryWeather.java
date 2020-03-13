package com.example.weatherapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryWeather {
    private static final String TAG = "Repository";
    private static RepositoryWeather instance;
    private WeatherService service;
    public static RepositoryWeather getInstance() {
        if(instance == null){
            instance = new RepositoryWeather();
        }
        return instance;
    }

    public RepositoryWeather() {
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
                Log.d(TAG, "onResponse: "+ response.body().toString());
                data.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return data;
    }
}
