package com.example.weatherapp.repository;

import android.content.Context;

import com.example.weatherapp.model.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RepositoryCity {
    private ArrayList<City> listCities;
    private static RepositoryCity instance;

    public static RepositoryCity getInstance(Context context){
        if(instance == null){
            instance = new RepositoryCity(context);
        }
        return instance;
    }

    public RepositoryCity(Context context){
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("listcities.json"), "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            listCities = convertJson(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public ArrayList<City> getListCities() {
        return listCities;
    }

    private ArrayList<City> convertJson(String data) {
        ArrayList<City> mList = new ArrayList<>();
        try {
            JSONArray root = new JSONArray(data);
            for(int i = 0; i<root.length(); i++){
                JSONObject object = root.getJSONObject(i);
                City city = new City();
                city.setId(object.getLong("id"));
                city.setName(object.getString("name"));
                mList.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mList;
    }
}
