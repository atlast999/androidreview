package com.example.weatherapp.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Weather {
    private double realFeel;
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
    @SerializedName("timezone")
    private String timezone;
    //getter
    public double getRealFeel() {
        return realFeel;
    }

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
    public String getTimezone() {
        return timezone;
    }

    //setter
    public void setRealFeel(double realFeel) {
        this.realFeel = realFeel;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    public void setTemp(String temp) {
        this.mMain.temp = temp;
    }
    public void setFeelLike(String feelLike) {
        this.mMain.feelLike = feelLike;
    }
    public void setHumidity(String humidity) {
        this.mMain.humidity = humidity;
    }
    public void setState(String state) {
        this.mWeather.get(0).state = state;
    }
    public void setIconCode(String iconCode) {
        this.mWeather.get(0).iconCode = iconCode;
    }
    public void setSpeed(String speed) {
        this.mWind.speed = speed;
    }
    public void setCountryCode(String countryCode) {
        this.mSys.countryCode = countryCode;
    }
    public void setSunrise(String sunrise) {
        this.mSys.sunrise = sunrise;
    }
    public void setSunset(String sunset) {
        this.mSys.sunset = sunset;
    }
    //set image
    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
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

    @NonNull
    @Override
    public String toString() {
        return this.cityName;
    }
}
