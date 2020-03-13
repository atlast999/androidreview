package com.example.weatherapp.utility;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.model.Weather;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static final String TAG = "Util";
    public static Weather formatWeather(Weather weather){
        weather.setCityName(weather.getCityName() + ", " + weather.getCountryCode());
        weather.setUpdateTime(convertUnixToTime(weather.getUpdateTime()));
        weather.setTemp(weather.getTemp() + "°");
        //state is ok
        weather.setRealFeel(Double.parseDouble(weather.getFeelLike()));
        weather.setFeelLike("Feels like " + weather.getFeelLike() + "°");
        weather.setSpeed(convertTokmh(weather.getSpeed()));
        if(weather.getVisibility() != null){
            weather.setVisibility(convertTokm(weather.getVisibility()));
        }
        //humidity is ok
        weather.setIconCode(convertToIconUrl(weather.getIconCode()));
        weather.setSunrise(convertToTime(weather.getSunrise(), weather.getTimezone()));
        weather.setSunset(convertToTime(weather.getSunset(), weather.getTimezone()));
        return weather;
    }

    private static String convertToTime(String time, String timezone) {
        long second = Long.parseLong(time) - 7*60*60 + Long.parseLong(timezone);
        Date date = new Date(second*1000);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    private static String convertToIconUrl(String iconCode) {
        return "http://openweathermap.org/img/w/"+iconCode+".png";
    }

    private static String convertTokm(String visibility) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double vis = 0.001*Double.parseDouble(visibility);
        return decimalFormat.format(vis);
}

    private static String convertTokmh(String speed) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double realSpeed = 3.6 * Double.parseDouble(speed);
        return decimalFormat.format(realSpeed);
    }
    
    private static String convertUnixToTime(String updateTime) {
        long millisecond = Long.parseLong(updateTime)*1000;
        Date date = new Date(millisecond);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append("update ");
        builder.append(timeFormat.format(date));
        builder.append(" - ");
        builder.append(dateFormat.format(date));
        return builder.toString();
    }

    public static void changeBackGround(Context context, Weather weather, ImageView imgBackground) {
        double temperature = weather.getRealFeel();
        if(temperature < 12d){
            loadImage(context, R.drawable.cold, imgBackground);
        }else if (temperature < 29d){
            loadImage(context, R.drawable.feelgood, imgBackground);
        }else if (temperature < 36d){
            loadImage(context, R.drawable.hot, imgBackground);
        }else {
            loadImage(context, R.drawable.extremehot, imgBackground);
        }
        //TODO
    }
    private static void loadImage(Context context, int resource, ImageView imageView){
        Glide.with(context)
                .load(resource)
                .into(imageView);
    }
}
