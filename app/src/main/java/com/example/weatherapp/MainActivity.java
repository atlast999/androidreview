package com.example.weatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.Repository;
import com.example.weatherapp.utility.Util;
import com.example.weatherapp.viewmodel.MainActivityViewModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Observer<String>{
    private static final String TAG = "MainActivity";
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private final int SEARCH_CITY_RQ = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getCityId().observe(this, this);

        viewModel.setCityId("1835848");
//        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, SearchCityActivity.class);
//                //TODO
//                startActivityForResult(intent, SEARCH_CITY_RQ);
//            }
//        });

    }

    @Override
    public void onChanged(String cityId) {
        viewModel.getWeather(cityId).observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                Util.changeBackGround(weather);
                binding.setWeather(weather);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //TODO
    }
}
