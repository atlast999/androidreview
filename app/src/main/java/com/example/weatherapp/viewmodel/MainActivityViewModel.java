package com.example.weatherapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.Repository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Weather> weather;

    public MutableLiveData<Weather> getWeather(String cityId){
        return Repository.getInstance().getWeather(cityId);
    }
}
