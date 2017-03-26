package com.yahoo.talent.weatherapp_ramanpreet.models.forecast_weather_models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TemperatureModel {
    // Day temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("day")
    public double dayTemp;

    // Min daily temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("min")
    public double minTemp;

    // Max daily temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("max")
    public double maxTemp;

    // Morning temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("morn")
    public double morningTemp;

    // Evening temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("eve")
    public double eveningTemp;

    // Night temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("night")
    public double nightTemp;
}