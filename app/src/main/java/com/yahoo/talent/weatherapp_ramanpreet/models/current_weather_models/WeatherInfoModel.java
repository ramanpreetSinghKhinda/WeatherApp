package com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class WeatherInfoModel {
    // Weather condition id
    @SerializedName("id")
    public long weatherConditionID;

    // Weather icon id
    @SerializedName("icon")
    public String weatherIconID;

    // Group of weather parameters (Rain, Snow, Extreme etc.)
    @SerializedName("main")
    public String weatherGroup;

    // Weather condition within the group
    @SerializedName("description")
    public String weatherDescription;
}

