package com.yahoo.talent.weatherapp_ramanpreet.models.forecast_weather_models;

import com.google.gson.annotations.SerializedName;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.WeatherInfoModel;

import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Parcel
public class ForecastWeatherInfoModel {
    // Time of data forecasted, unix, UTC
    @SerializedName("dt")
    public long unixTimeStamp;

    // Temperature Info
    @SerializedName("temp")
    public TemperatureModel temperatureModel;

    //  Atmospheric pressure on the sea level, hPa
    @SerializedName("pressure")
    public double pressure;

    // Humidity, %
    @SerializedName("humidity")
    public int humidity;

    // Weather Info
    @SerializedName("weather")
    public WeatherInfoModel[] weatherInfoModelArray;

    // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
    @SerializedName("speed")
    public double windSpeed;

    // Wind direction, degrees (meteorological)
    @SerializedName("deg")
    public int windDirection;

    // Cloudiness, %
    @SerializedName("clouds")
    public int cloudinessPercent;

    public String getForecastedDate() {
        Date date = new Date(unixTimeStamp * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        return sdf.format(date);
    }

    public String getForecastedTime() {
        Date date = new Date(unixTimeStamp * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        return sdf.format(date) + " EST";
    }
}