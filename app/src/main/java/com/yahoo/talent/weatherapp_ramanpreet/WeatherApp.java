package com.yahoo.talent.weatherapp_ramanpreet;

import android.app.Application;

import com.yahoo.talent.weatherapp_ramanpreet.network.WeatherService;

public class WeatherApp extends Application {
    private static WeatherApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        WeatherService.INSTANCE.startRetrofitService();
        WeatherService.INSTANCE.setContext(getApplicationContext());

    }

    public static WeatherApp getInstance() {
        return sInstance;
    }
}