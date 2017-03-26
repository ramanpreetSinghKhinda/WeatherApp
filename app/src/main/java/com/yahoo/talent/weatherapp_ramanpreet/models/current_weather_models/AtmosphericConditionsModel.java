package com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class AtmosphericConditionsModel {
    // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("temp")
    public double temperature;

    // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    @SerializedName("pressure")
    public double pressure;

    // Humidity, %
    @SerializedName("humidity")
    public int humidity;

    // Minimum temperature at the moment.
    // This is deviation from current temp that is possible for large cities
    // and megalopolises geographically expanded (use these parameter optionally).
    // Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("temp_min")
    public double minTemp;

    // Maximum temperature at the moment.
    // This is deviation from current temp that is possible for large cities
    // and megalopolises geographically expanded (use these parameter optionally).
    // Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    @SerializedName("temp_max")
    public double maxTemp;

    // Atmospheric pressure on the sea level, hPa
    @SerializedName("sea_level")
    public double pressureAtSeaLevel;

    // Atmospheric pressure on the ground level, hPa
    @SerializedName("grnd_level")
    public double pressureAtGroundLevel;
}