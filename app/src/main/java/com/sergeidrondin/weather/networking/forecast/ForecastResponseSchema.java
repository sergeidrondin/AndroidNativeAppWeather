package com.sergeidrondin.weather.networking.forecast;

import com.google.gson.annotations.SerializedName;
import com.sergeidrondin.weather.networking.common.WeatherSchema;

import java.util.List;

public class ForecastResponseSchema {

    @SerializedName("weather")
    private final List<WeatherSchema> mWeatherList;

    @SerializedName("main")
    private final MainForecastInfoSchema mMainInfo;

    public ForecastResponseSchema(List<WeatherSchema> weatherList, MainForecastInfoSchema main) {
        this.mWeatherList = weatherList;
        this.mMainInfo = main;
    }

    public List<WeatherSchema> getWeatherList() {
        return mWeatherList;
    }

    public MainForecastInfoSchema getMainInfo() {
        return mMainInfo;
    }
}
