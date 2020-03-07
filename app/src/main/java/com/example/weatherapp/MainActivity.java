package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.Repository;
import com.example.weatherapp.viewmodel.MainActivityViewModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Observer<String>{
    private static final String TAG = "MainActivity";
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getCityId().observe(this, this);

        viewModel.setCityId("1835848");

    }

    @Override
    public void onChanged(String cityId) {
        viewModel.getWeather(cityId).observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                binding.setWeather(weather);
            }
        });
    }
}
