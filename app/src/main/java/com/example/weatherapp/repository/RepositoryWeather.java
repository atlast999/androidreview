package com.example.weatherapp.repository;

import com.example.weatherapp.model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryWeather {
    private boolean isGetting = false;
    private Weather curWeather;
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

    public Weather getWeather(String cityId){
        curWeather = new Weather();
        isGetting = true;
        service.getWeather(cityId, "10ce7a8b71d274aae09250389c394f2d", "metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                curWeather = response.body();
                isGetting = false;
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
        while (isGetting);
        return curWeather;
    }
}
