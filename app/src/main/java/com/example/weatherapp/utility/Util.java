package com.example.weatherapp.utility;

import com.example.weatherapp.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static Weather formatWeather(Weather weather){
        weather.setCityName(weather.getCityName() + ", " + weather.getCountryCode());
        weather.setUpdateTime(convertUnixToTime(weather.getUpdateTime()));
        weather.setTemp(weather.getTemp() + "°");
        //state is ok
        weather.setFeelLike("Feels like " + weather.getFeelLike() + "°");
        weather.setSpeed(convertTokmh(weather.getSpeed()));
        weather.setVisibility(convertTokm(weather.getVisibility()));
        //humidity is ok
        //icon
        //sunrise, set
        return weather;
        //TODO
    }

    private static String convertTokm(String visibility) {
        double vis = 0.001*Double.parseDouble(visibility);
        return String.valueOf(vis);
    }

    private static String convertTokmh(String speed) {
        double realSpeed = 3.6 * Double.parseDouble(speed);
        return String.valueOf(realSpeed);
    }

    //    <!--                android:text="4:30 pm - Saturday, 7 Mar 2020"-->
    private static String convertUnixToTime(String updateTime) {
        Date date = new Date((long)1000*Long.parseLong(updateTime));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append("update ");
        builder.append(timeFormat.format(date));
        builder.append(" - ");
        builder.append(dateFormat.format(date));
        return builder.toString();
    }

    public static void changeBackGround(Weather weather) {
        //TODO
    }
}
