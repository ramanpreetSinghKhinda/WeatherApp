package com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Parcel
public class SysModel {
    // Country code (GB, JP etc.)
    @SerializedName("country")
    public String countryCode;

    // Sunrise time, unix, UTC
    @SerializedName("sunrise")
    public long sunriseUnixTime;

    // Sunrise time, unix, UTC
    @SerializedName("sunset")
    public long sunsetUnixTime;

    public String getSunriseTime() {
        Date date = new Date(sunriseUnixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        return sdf.format(date) + " EST";
    }

    public String getSunsetTime() {
        Date date = new Date(sunsetUnixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        return sdf.format(date) + " EST";
    }

    // Internal parameter
    public long id;
    public int type;
    public double message;
}