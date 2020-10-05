package com.sergeidrondin.weather.screens.forecastdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.controllers.BaseActivity;

public class ForecastDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context, DailyForecast dailyForecast) {
        Intent intent = new Intent(context, ForecastDetailsActivity.class);
        String dailyForecastTag = String.valueOf(R.string.daily_forecast_extra_tag);
        intent.putExtra(dailyForecastTag, dailyForecast);
        context.startActivity(intent);
    }
}