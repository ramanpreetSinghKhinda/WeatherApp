package com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class CloudsModel {
    // Cloudiness, %
    @SerializedName("all")
    public int cloudinessPercent;
}