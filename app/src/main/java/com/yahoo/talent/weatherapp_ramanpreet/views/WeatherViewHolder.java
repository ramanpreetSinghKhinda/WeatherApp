package com.yahoo.talent.weatherapp_ramanpreet.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yahoo.talent.weatherapp_ramanpreet.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvFutureDate;
    public TextView tvDescription;
    public ImageView imgWeatherIcon;
    public TextView tvTemperatureRange;
    public TextView tvWindSpeed;
    public TextView tvPressure;
    public TextView tvHumidity;


    public WeatherViewHolder(View itemView) {
        super(itemView);

        tvFutureDate = (TextView) itemView.findViewById(R.id.txt_future_date);
        tvDescription = (TextView) itemView.findViewById(R.id.txt_desc);
        imgWeatherIcon = (ImageView) itemView.findViewById(R.id.ic_weather);
        tvTemperatureRange = (TextView) itemView.findViewById(R.id.txt_temp_range);
        tvWindSpeed = (TextView) itemView.findViewById(R.id.txt_wind_speed);
        tvPressure = (TextView) itemView.findViewById(R.id.txt_pressure);
        tvHumidity = (TextView) itemView.findViewById(R.id.txt_humidity);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Calling Activity using interface

    }


}
