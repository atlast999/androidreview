package com.example.weatherapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.RepositoryWeather;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Weather> mWeather = new MutableLiveData<>();
    private MutableLiveData<String > cityId = new MutableLiveData<>();

    public MutableLiveData<String> getCityId() {
        return cityId;
    }

    public void setCityId(String cityId){
        this.cityId.postValue(cityId);
    }

    public MutableLiveData<Weather> getWeather(String cityId){
        return RepositoryWeather.getInstance().getWeather(cityId);
//        mWeather.postValue(RepositoryWeather.getInstance().getWeatherm(cityId));
//        return mWeather;
    }

}
