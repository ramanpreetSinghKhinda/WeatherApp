package com.yahoo.talent.weatherapp_ramanpreet.network;

import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.CurrentWeatherModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.forecast_weather_models.ForecastWeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("weather")
    Call<CurrentWeatherModel> getCurrentWeather(
            @Query("apikey") String apiKey,
            @Query("q") String location,
            @Query("units") String metric
            );

    @GET("forecast/daily")
    Call<ForecastWeatherModel> getForecastWeather(
            @Query("apikey") String apiKey,
            @Query("q") String location,
            @Query("units") String metric,
            @Query("cnt") int count
    );

}
