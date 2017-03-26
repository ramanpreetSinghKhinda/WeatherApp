package com.yahoo.talent.weatherapp_ramanpreet.network;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yahoo.talent.weatherapp_ramanpreet.models.current_weather_models.CurrentWeatherModel;
import com.yahoo.talent.weatherapp_ramanpreet.models.forecast_weather_models.ForecastWeatherModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum WeatherService {
    INSTANCE;

    /*******************
     * Globals Start
     *******************/
    private static final String TAG = "Raman_Weather";

    private static final String API_KEY = "df24ac21725fa74231cbe77c73bdb156";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String UNIT = "imperial";

    public static final String HOME_LOCATION = "New York, US";

    public static final String CURRENT_WEATHER_LISTENER = "current_weather_listener";
    public static final String FORECAST_WEATHER_LISTENER = "forecast_weather_listener";

    public static final String CURRENT_WEATHER_JSON = "current_weather_json";
    public static final String FORECAST_WEATHER_JSON = "forecast_weather_json";

    /*******************
     * Globals End
     *******************/

    public static final int FORECAST_DAYS_COUNT = 7;

    private WeatherAPI weatherApi;
    private CurrentWeatherModel currentWeatherModel;
    private Context mContext;

    public void startRetrofitService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        weatherApi = retrofit.create(WeatherAPI.class);
    }

    public String getNewDateFormat(long unixSeconds) {
        Date date = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        return sdf.format(date);
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void getCurrentWeatherDetails(String location) {
        Call<CurrentWeatherModel> call = weatherApi.getCurrentWeather(API_KEY, location, UNIT);

        call.enqueue(new Callback<CurrentWeatherModel>() {
            @Override
            public void onResponse(Call<CurrentWeatherModel> call, Response<CurrentWeatherModel> response) {
                CurrentWeatherModel callResponse = response.body();

                if (null != callResponse) {
                    sendCurrentWeatherStatus(callResponse.getCurrentWeatherJsonString());
                }

                Log.v(TAG, "getCurrentWeatherDetails() : onResponse() : " + callResponse);
            }

            @Override
            public void onFailure(Call<CurrentWeatherModel> call, Throwable t) {
                Log.v(TAG, "getWeatherForecastDetails() : onFailure()");
                t.printStackTrace();
            }
        });
    }

    public void getForecastWeatherDetails(String location) {
        Call<ForecastWeatherModel> call = weatherApi.getForecastWeather(API_KEY, location, UNIT, FORECAST_DAYS_COUNT);

        call.enqueue(new Callback<ForecastWeatherModel>() {
            @Override
            public void onResponse(Call<ForecastWeatherModel> call, Response<ForecastWeatherModel> response) {
                ForecastWeatherModel callResponse = response.body();

                if (null != callResponse) {
                    sendForecastWeatherStatus(callResponse.getForecastWeatherJsonString());
                }

                Log.v(TAG, "getForecastWeatherDetails() : onResponse() : " + callResponse);
            }

            @Override
            public void onFailure(Call<ForecastWeatherModel> call, Throwable t) {
                Log.v(TAG, "getForecastWeatherDetails() : onFailure()");
                t.printStackTrace();
            }
        });
    }

    public void sendCurrentWeatherStatus(String currentWeatherJsonString) {
        Intent intent = new Intent(CURRENT_WEATHER_LISTENER);
        intent.putExtra(CURRENT_WEATHER_JSON, currentWeatherJsonString);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

    public void sendForecastWeatherStatus(String forecastWeatherJsonString) {
        Intent intent = new Intent(FORECAST_WEATHER_LISTENER);
        intent.putExtra(FORECAST_WEATHER_JSON, forecastWeatherJsonString);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }
}
