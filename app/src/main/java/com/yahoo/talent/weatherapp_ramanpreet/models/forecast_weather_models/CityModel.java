package com.yahoo.talent.weatherapp_ramanpreet.models.forecast_weather_models;

import com.google.gson.annotations.SerializedName;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.AtmosphericConditionsModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.CityGeoCoordinatesModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.CloudsModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.SysModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.WeatherInfoModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.WindConditionsModel;

import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Parcel
public class CityModel {
    // City ID
    @SerializedName("id")
    private String cityID;

    // City name
    @SerializedName("name")
    public String cityName;

    // City geo location
    @SerializedName("coord")
    public CityGeoCoordinatesModel cityGeoCoordinatesModel;

    // Country code (GB, JP etc.)
    @SerializedName("country")
    public String countryCode;
}
