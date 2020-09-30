package com.sergeidrondin.weather.networking.onecall;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OneCallResponseSchema {

    @SerializedName("current")
    private final CurrentWeatherSchema mCurrent;

    @SerializedName("daily")
    private final List<DailyForecastSchema> mDaily;

    public OneCallResponseSchema(CurrentWeatherSchema current, List<DailyForecastSchema> daily) {
        this.mCurrent = current;
        this.mDaily = daily;
    }

    public CurrentWeatherSchema getCurrent() {
        return mCurrent;
    }

    public List<DailyForecastSchema> getDaily() {
        return mDaily;
    }
}
