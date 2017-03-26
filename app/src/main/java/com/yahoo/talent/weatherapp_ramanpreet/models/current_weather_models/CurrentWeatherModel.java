package com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import com.google.gson.annotations.SerializedName;

@Parcel
public class CurrentWeatherModel {
    // Time of data calculation, unix, UTC
    @SerializedName("dt")
    public long unixTimeStamp;

    // City ID
    @SerializedName("id")
    public long cityID;

    // City name
    @SerializedName("name")
    public String cityName;

    // City geo location
    @SerializedName("coord")
    public CityGeoCoordinatesModel cityGeoCoordinatesModel;

    // Weather Info
    @SerializedName("weather")
    public WeatherInfoModel[] weatherInfoModelArray;

    // Info about atmospheric conditions {temperature, pressure and humidity|
    @SerializedName("main")
    public AtmosphericConditionsModel atmosphericConditionsModel;

    // Info about wind conditions {speed and degree}
    @SerializedName("wind")
    public WindConditionsModel windConditionsModel;

    // Cloudiness, %
    @SerializedName("clouds")
    public CloudsModel cloudsModel;

    // Additional Information {sunrise, sunset, etc.}
    @SerializedName("sys")
    public SysModel sysModel;

    public String getCurrentDate() {
        Date date = new Date(unixTimeStamp * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        return sdf.format(date);
    }

    public String getCurrentTime() {
        Date date = new Date(unixTimeStamp * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));

        return sdf.format(date) + " EST";
    }

    // Internal Parameters
    public String base;
    public String cod;

    public String getCurrentWeatherJsonString() {
        JSONObject currentWeatherJson = new JSONObject();
        try {
            currentWeatherJson.put("cityName",cityName);

            String weatherDesc = "not available";
            String weatherIconID = null;
            if (weatherInfoModelArray.length > 0) {
                weatherDesc = weatherInfoModelArray[0].weatherDescription;
                weatherIconID = weatherInfoModelArray[0].weatherIconID;
            }

            currentWeatherJson.put("weatherDescription", weatherDesc);
            currentWeatherJson.put("weatherIconID", weatherIconID);

            currentWeatherJson.put("temperature", atmosphericConditionsModel.temperature);
            currentWeatherJson.put("pressure", atmosphericConditionsModel.pressure);
            currentWeatherJson.put("humidity", atmosphericConditionsModel.humidity);

            currentWeatherJson.put("windSpeed", windConditionsModel.windSpeed);

            currentWeatherJson.put("sunriseTime", sysModel.getSunriseTime());
            currentWeatherJson.put("sunsetTime", sysModel.getSunsetTime());

            currentWeatherJson.put("currentDate", getCurrentDate());
            currentWeatherJson.put("currentTime", getCurrentTime());

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return currentWeatherJson.toString();
        }
    }
}
