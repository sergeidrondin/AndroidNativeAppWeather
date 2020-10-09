package com.sergeidrondin.weather.screens.forecastdetails;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.forecast.DailyTemperature;
import com.sergeidrondin.weather.screens.common.views.BaseViewMvc;

public class ForecastDetailsViewMvcImpl extends BaseViewMvc implements ForecastDetailsViewMvc {
    private TextView mMorningTextView;
    private TextView mDayTextView;
    private TextView mEveningTextView;
    private TextView mNightTextView;


    public ForecastDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.activity_forecast_details, parent, false));
        mMorningTextView = findViewById(R.id.forecast_detail_tv_morning);
        mDayTextView = findViewById(R.id.forecast_detail_tv_day);
        mEveningTextView = findViewById(R.id.forecast_detail_tv_evening);
        mNightTextView = findViewById(R.id.forecast_detail_tv_night);
    }

    @Override
    public void renderForecastDetails(DailyForecast dailyForecast) {
        DailyTemperature dt = dailyForecast.getTemperature();

        String morningTemperature = String.valueOf(dt.getDay());
        mMorningTextView.setText(morningTemperature);

        String dayTemperature = String.valueOf(dt.getMorn());
        mDayTextView.setText(dayTemperature);

        String eveningTemperature = String.valueOf(dt.getEve());
        mEveningTextView.setText(eveningTemperature);

        String nightTemperature = String.valueOf(dt.getNight());
        mNightTextView.setText(nightTemperature);
    }
}
