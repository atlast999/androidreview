package com.example.weatherapp.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherapp.R;
import com.example.weatherapp.adapter.CityListViewAdapter;
import com.example.weatherapp.callback.ICitySelectedListener;
import com.example.weatherapp.callback.IQueryChangedListener;
import com.example.weatherapp.model.City;
import com.example.weatherapp.repository.RepositoryCity;

import java.util.ArrayList;

public class SearchCityFragment extends Fragment implements AdapterView.OnItemClickListener, IQueryChangedListener {
    private static final String TAG = "SearchCityFragment";
    private ICitySelectedListener listener;
    private ListView listView;
    private ArrayList<City> listCities = new ArrayList<>();
    private Context context;
    private CityListViewAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        listener = (ICitySelectedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_city, container, false);
        listView = view.findViewById(R.id.listViewCity);

        adapter = new CityListViewAdapter(this.getContext(), R.layout.item_city_layout, listCities);
        updateListInBackground();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    private void updateListInBackground() {
        new AsyncTask<Void, Void, ArrayList<City>>() {
            @Override
            protected ArrayList<City> doInBackground(Void... voids) {
                return RepositoryCity.getInstance(context).getListCities();
            }
            @Override
            protected void onPostExecute(ArrayList<City> list) {
                listCities.addAll(list);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: id = " + id);
        listener.onCitySelectedListener(String.valueOf(id));
    }

    @Override
    public void onQueryChangedListener(String newText) {
        adapter.getFilter().filter(newText);
    }
}
