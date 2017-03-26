package com.yahoo.talent.weatherapp_ramanpreet.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yahoo.talent.weatherapp_ramanpreet.R;
import com.yahoo.talent.weatherapp_ramanpreet.views.WeatherViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private List<JSONObject> forecastWeatherInfoJsonList;
    Context mContext;

    public WeatherAdapter(@Nullable List<JSONObject> forecastWeatherInfoJsonList, Context mContext) {
        if (forecastWeatherInfoJsonList == null) {
            this.forecastWeatherInfoJsonList = new ArrayList<>();
        } else {
            this.forecastWeatherInfoJsonList = forecastWeatherInfoJsonList;
        }

        this.mContext = mContext;
    }

    public void updateForecastWeatherList(List<JSONObject> forecastWeatherInfoJsonList) {
        this.forecastWeatherInfoJsonList.clear();
        this.forecastWeatherInfoJsonList.addAll(forecastWeatherInfoJsonList);

        notifyDataSetChanged();
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_forecast_weather, parent, false);

        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        JSONObject forecastWeatherInfoJson = forecastWeatherInfoJsonList.get(position);

        try {
            holder.tvFutureDate.setText(forecastWeatherInfoJson.getString("forecastedDate"));

            holder.tvDescription.setText(forecastWeatherInfoJson.getString("weatherDescription"));
            Glide.with(holder.itemView.getContext())
                    .load("http://openweathermap.org/img/w/" + forecastWeatherInfoJson.getString("weatherIconID") + ".png")
                    .error(R.drawable.ic_cloud)
                    .crossFade()
                    .into(holder.imgWeatherIcon);

            holder.tvTemperatureRange.setText(mContext.getString(R.string.txt_temp_range_f_degree, forecastWeatherInfoJson.getString("minTemp"), forecastWeatherInfoJson.getString("maxTemp")));

            holder.tvWindSpeed.setText(mContext.getString(R.string.txt_wind_speed, forecastWeatherInfoJson.getString("windSpeed") + ""));
            holder.tvPressure.setText(mContext.getString(R.string.txt_pressure, forecastWeatherInfoJson.getString("pressure")));
            holder.tvHumidity.setText(mContext.getString(R.string.txt_humidity, forecastWeatherInfoJson.getString("humidity") + ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return forecastWeatherInfoJsonList.size();
    }

}

