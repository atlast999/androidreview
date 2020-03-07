package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.Repository;
import com.example.weatherapp.viewmodel.MainActivityViewModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvTemp, tvCityName;
    private Weather weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCityName = this.findViewById(R.id.tvCityName);
        tvTemp = this.findViewById(R.id.tvTemperature);
//        tvSpeed = this.findViewById(R.id.tvWindSpeed);


        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

//        weather = viewModel.getWeather("1835848").getValue();
        Repository.getInstance().getService().getWeather("1835848", "10ce7a8b71d274aae09250389c394f2d", "metric")
                .enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        Log.d(TAG, "onResponse: ");
                        weather = response.body();
                        updateUI();
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });

    }

    private void updateUI() {
        tvCityName.setText(weather.getCityName());
//        tvSpeed.setText(weather.getWindSpeed());
        tvTemp.setText(weather.getTemp());
    }
}
