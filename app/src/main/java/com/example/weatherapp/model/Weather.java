package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Weather {
    @SerializedName("name")
    private String cityName;
    @SerializedName("dt")
    private String updateTime;
    @SerializedName("visibility")
    private String visibility;
    @SerializedName("main")
    private MMain mMain;
    @SerializedName("weather")
    private ArrayList<MWeather> mWeather;
    @SerializedName("wind")
    private MWind mWind;
    @SerializedName("sys")
    private MSys mSys;

    public String getCityName(){
        return cityName;
    }
    public String getUpdateTime(){
        return updateTime;
    }
    public String getVisibility() {
        return visibility;
    }
    public String getTemp(){
        return mMain.temp;
    }
    public String getFeelLike() {
        return mMain.feelLike;
    }
    public String getHumidity() {
        return mMain.humidity;
    }
    public String getState() {
        return mWeather.get(0).state;
    }
    public String getIconCode() {
        return mWeather.get(0).iconCode;
    }
    public String getSpeed(){
        return mWind.speed;
    }
    public String getCountryCode() {
        return mSys.countryCode;
    }

    public String getSunrise() {
        return mSys.sunrise;
    }

    public String getSunset() {
        return mSys.sunset;
    }


    class MSys{
        @SerializedName("country")
        public String countryCode;
        @SerializedName("sunrise")
        public String sunrise;
        @SerializedName("sunset")
        public String sunset;
    }

    class MWind{
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
