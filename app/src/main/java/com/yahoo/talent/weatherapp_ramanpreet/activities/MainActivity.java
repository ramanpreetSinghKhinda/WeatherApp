package com.yahoo.talent.weatherapp_ramanpreet.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yahoo.talent.weatherapp_ramanpreet.R;
import com.yahoo.talent.weatherapp_ramanpreet.adapters.WeatherAdapter;
import com.yahoo.talent.weatherapp_ramanpreet.network.WeatherService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener, AdapterView.OnItemClickListener {

    @Bind(R.id.search_atv)
    AutoCompleteTextView mSearchView;

    @Bind(R.id.txt_city_name)
    TextView tvCityName;

    @Bind(R.id.txt_date)
    TextView tvDate;

    @Bind(R.id.txt_last_update_time)
    TextView tvLastUpdateTime;

    @Bind(R.id.ic_weather)
    ImageView imgWeatherIcon;

    @Bind(R.id.txt_temp_range)
    TextView tvTemp;

    @Bind(R.id.txt_desc)
    TextView tvDesc;

    @Bind(R.id.txt_wind_speed)
    TextView tvWindSpeed;

    @Bind(R.id.txt_pressure)
    TextView tvPressure;

    @Bind(R.id.txt_humidity)
    TextView tvHumidity;

    @Bind(R.id.txt_sunrise_time)
    TextView tvSunriseTime;

    @Bind(R.id.txt_sunset_time)
    TextView tvSunsetTime;

    @Bind(R.id.weather_cards_recycler_view)
    RecyclerView mRecyclerView;

    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSearchView.setOnEditorActionListener(this);
        mSearchView.setOnItemClickListener(this);

        List<JSONObject> forecastWeatherInfoJsonList = new ArrayList<>(0);
        weatherAdapter = new WeatherAdapter(forecastWeatherInfoJsonList, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(weatherAdapter);

        LocalBroadcastManager.getInstance(this).registerReceiver(mCurrentWeatherReceiver, new IntentFilter(WeatherService.CURRENT_WEATHER_LISTENER));
        LocalBroadcastManager.getInstance(this).registerReceiver(mForecastWeatherReceiver, new IntentFilter(WeatherService.FORECAST_WEATHER_LISTENER));
    }


    @Override
    public void onResume() {
        super.onResume();
        WeatherService.INSTANCE.startRetrofitService();
        WeatherService.INSTANCE.getCurrentWeatherDetails(WeatherService.HOME_LOCATION);
        WeatherService.INSTANCE.getForecastWeatherDetails(WeatherService.HOME_LOCATION);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEARCH
                || (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            search(mSearchView.getText().toString());
            handled = true;
        }

        return handled;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        search((String) parent.getAdapter().getItem(position));
    }

    private void search(String searchLocation) {
        WeatherService.INSTANCE.getCurrentWeatherDetails(searchLocation);
        WeatherService.INSTANCE.getForecastWeatherDetails(searchLocation);
    }

    private BroadcastReceiver mCurrentWeatherReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String currentWeatherJsonString = intent.getStringExtra(WeatherService.CURRENT_WEATHER_JSON);

            try {
                JSONObject currentWeatherJson = new JSONObject(currentWeatherJsonString);


                tvCityName.setText(currentWeatherJson.getString("cityName"));
                tvDate.setText(currentWeatherJson.getString("currentDate"));
                tvLastUpdateTime.setText(getString(R.string.txt_last_update, currentWeatherJson.getString("currentTime")));

                tvTemp.setText(getString(R.string.txt_temp_f_degree, currentWeatherJson.getString("temperature")));
                tvDesc.setText(currentWeatherJson.getString("weatherDescription"));

                Glide.with(getApplicationContext())
                        .load("http://openweathermap.org/img/w/" + currentWeatherJson.getString("weatherIconID") + ".png")
                        .error(R.drawable.ic_cloud)
                        .crossFade()
                        .into(imgWeatherIcon);

                tvWindSpeed.setText(getString(R.string.txt_wind_speed, currentWeatherJson.getString("windSpeed") + ""));
                tvPressure.setText(getString(R.string.txt_pressure, currentWeatherJson.getString("pressure")));
                tvHumidity.setText(getString(R.string.txt_humidity, currentWeatherJson.getString("humidity") + ""));

                tvSunriseTime.setText(getString(R.string.txt_sunrise_time, currentWeatherJson.getString("sunriseTime")));
                tvSunsetTime.setText(getString(R.string.txt_sunset_time, currentWeatherJson.getString("sunsetTime")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private BroadcastReceiver mForecastWeatherReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String forecastWeatherJsonString = intent.getStringExtra(WeatherService.FORECAST_WEATHER_JSON);
            List<JSONObject> forecastWeatherInfoJsonList = new ArrayList<>();

            try {
                JSONObject forecastWeatherJson = new JSONObject(forecastWeatherJsonString);
                JSONArray weatherInfoJsonArray = forecastWeatherJson.getJSONArray("weatherInfoJsonArrayString");


                for (int i = 0; i < weatherInfoJsonArray.length(); i++) {
                    forecastWeatherInfoJsonList.add(weatherInfoJsonArray.getJSONObject(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            weatherAdapter.updateForecastWeatherList(forecastWeatherInfoJsonList);
        }
    };
}
