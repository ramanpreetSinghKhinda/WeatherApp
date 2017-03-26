package com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class WindConditionsModel {
    // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
    @SerializedName("speed")
    public double windSpeed;

    // Wind direction, degrees (meteorological)
    @SerializedName("deg")
    public int windDirection;
}

