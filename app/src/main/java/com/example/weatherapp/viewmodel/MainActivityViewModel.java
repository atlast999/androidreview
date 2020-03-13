package com.example.weatherapp.viewmodel;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.RepositoryWeather;
import com.example.weatherapp.utility.Util;


public class MainActivityViewModel extends ViewModel {
    private static final String TAG = "MainActivityViewModel";
    private Weather weather = new Weather();
    private MutableLiveData<String > cityId = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getCityId() {
        return cityId;
    }

    public void setCityId(String cityId){
        isLoading.postValue(true);
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                weather = RepositoryWeather.getInstance().getWeather(strings[0]);
                weather = Util.formatWeather(weather);
                return strings[0];
            }
            @Override
            protected void onPostExecute(String string) {
                MainActivityViewModel.this.cityId.postValue(string);
                isLoading.postValue(false);
            }
        }.execute(cityId);
    }

    public Weather getWeather(){
        return weather;
    }

}
