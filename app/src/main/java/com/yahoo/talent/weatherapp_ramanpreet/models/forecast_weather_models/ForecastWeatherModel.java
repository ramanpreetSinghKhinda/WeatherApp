package com.yahoo.talent.weatherapp_ramanpreet.models.forecast_weather_models;

import com.google.gson.annotations.SerializedName;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.AtmosphericConditionsModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.CityGeoCoordinatesModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.CloudsModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.SysModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.WeatherInfoModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.WindConditionsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Parcel
public class ForecastWeatherModel {
    // Info about city {name, geo-coordinates, country, etc.}
    @SerializedName("city")
    public CityModel cityModel;

    // Number of lines returned by this API call
    @SerializedName("cnt")
    public int forecastDaysCount;

    // Forecast Weather list
    @SerializedName("list")
    public ForecastWeatherInfoModel ForecastWeatherInfoModelArray[];


    public String getForecastWeatherJsonString() {
        JSONObject forecastWeatherJson = new JSONObject();
        try {
            JSONArray weatherInfoJsonArray = new JSONArray();

            for(ForecastWeatherInfoModel forecastWeatherInfo : ForecastWeatherInfoModelArray) {
                JSONObject weatherInfoJson = new JSONObject();

                weatherInfoJson.put("forecastedDate", forecastWeatherInfo.getForecastedDate());
                weatherInfoJson.put("forecastedTime", forecastWeatherInfo.getForecastedTime());

                weatherInfoJson.put("minTemp", forecastWeatherInfo.temperatureModel.minTemp);
                weatherInfoJson.put("maxTemp", forecastWeatherInfo.temperatureModel.maxTemp);

                weatherInfoJson.put("pressure", forecastWeatherInfo.pressure);
                weatherInfoJson.put("humidity", forecastWeatherInfo.humidity);
                weatherInfoJson.put("windSpeed", forecastWeatherInfo.windSpeed);

                String weatherDesc = "not available";
                String weatherIconID = null;
                if (forecastWeatherInfo.weatherInfoModelArray.length > 0) {
                    weatherDesc = forecastWeatherInfo.weatherInfoModelArray[0].weatherDescription;
                    weatherIconID = forecastWeatherInfo.weatherInfoModelArray[0].weatherIconID;
                }

                weatherInfoJson.put("weatherDescription", weatherDesc);
                weatherInfoJson.put("weatherIconID", weatherIconID);

                weatherInfoJsonArray.put(weatherInfoJson);
            }

            forecastWeatherJson.put("weatherInfoJsonArrayString",(Object)weatherInfoJsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return forecastWeatherJson.toString();
        }
    }
}
