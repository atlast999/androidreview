package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Weather {
    @SerializedName("name")
    private String cityName;
    @SerializedName("main")
    private MMain mMain;
    @SerializedName("weather")
    private ArrayList<MWeather> mWeather;
    @SerializedName("dt")
    private String updateTime;
    @SerializedName("visibility")
    private String visibility;



    public String getCityName(){
        return cityName;
    }
    public String getTemp(){
        return mMain.temp;
    }

    class mSys{
        @SerializedName("country")
        public String countryCode;
        @SerializedName("sunrise")
        public String sunrise;
        @SerializedName("sunset")
        public String sunset;
    }

    class mWind{
        @SerializedName("speed")
        public String speed;
    }

    class MWeather{
        @SerializedName("main")
        public String state;
        @SerializedName("icon")
        public String iconCode;
    }
    class MMain{
        @SerializedName("temp")
        public String temp;
        @SerializedName("feels_like")
        public String feelLike;
        @SerializedName("humidity")
        public String humidity;
    }
}
