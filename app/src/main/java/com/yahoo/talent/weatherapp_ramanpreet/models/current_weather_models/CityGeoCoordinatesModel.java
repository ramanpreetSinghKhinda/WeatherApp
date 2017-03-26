package com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class CityGeoCoordinatesModel {
    // City geo location, longitude
    @SerializedName("lon")
    public double cityGeoLongitude;

    // City geo location, latitude
    @SerializedName("lat")
    public double cityGeoLatitude;
}
