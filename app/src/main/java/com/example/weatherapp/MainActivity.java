package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.weatherapp.callback.ICitySelectedListener;
import com.example.weatherapp.callback.IQueryChangedListener;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.fragment.SearchCityFragment;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.utility.Util;
import com.example.weatherapp.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements Observer<String>, ICitySelectedListener {
    private static final String TAG = "MainActivity";
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private SearchCityFragment searchCityFragment;
    private IQueryChangedListener queryChangedListener;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = this.getSupportFragmentManager();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getCityId().observe(this, this);

        viewModel.setCityId("1835848");

        binding.toolbar.setTitle("");
        this.setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.toolbar_main, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        handleSearchView(searchView);

        return super.onCreateOptionsMenu(menu);
    }

    private void handleSearchView(SearchView searchView) {
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableFragment();
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                disableFragment();
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryChangedListener.onQueryChangedListener(newText);
                return false;
            }
        });
    }

    @Override
    public void onChanged(String cityId) {
        viewModel.getWeather(cityId).observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                new AsyncTask<Weather, Weather, Weather>() {
                    @Override
                    protected Weather doInBackground(Weather... weathers) {
                        return Util.formatWeather(weathers[0]);
                    }
                    @Override
                    protected void onPostExecute(Weather weather) {
                        Util.changeBackGround(MainActivity.this, weather, binding.imgBackground);
                        binding.setWeather(weather);
                    }
                }.execute(weather);
            }
        });
    }

    @Override
    public void onCitySelectedListener(String cityId) {
        disableFragment();
        //close searchView when a new location is selected
        searchView.onActionViewCollapsed();
        viewModel.setCityId(cityId);
    }

    private void disableFragment(){
        fragmentManager.beginTransaction().remove(searchCityFragment).commit();
        binding.scrollView.setVisibility(View.VISIBLE);
    }
    private void enableFragment(){
        searchCityFragment = new SearchCityFragment();
        queryChangedListener = searchCityFragment;
        fragmentManager.beginTransaction().add(R.id.frameLayout, searchCityFragment).commit();
        binding.scrollView.setVisibility(View.GONE);
    }
}
