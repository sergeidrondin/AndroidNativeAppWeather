package com.sergeidrondin.weather.networking.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastResponseSchema {

    @SerializedName("weather")
    private final List<WeatherSchema> mWeatherList;

    @SerializedName("main")
    private final MainForecastInfoSchema mMain;

    public ForecastResponseSchema(List<WeatherSchema> weatherList, MainForecastInfoSchema main) {
        this.mWeatherList = weatherList;
        this.mMain = main;
    }
}
