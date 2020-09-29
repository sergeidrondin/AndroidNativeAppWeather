package com.sergeidrondin.weather.networking.forecast;

import com.google.gson.annotations.SerializedName;

public class MainForecastInfoSchema {

    @SerializedName("temp")
    private final Float mTemp;

    @SerializedName("feels_like")
    private final Float mFeelsLike;

    @SerializedName("temp_min")
    private final Float mTempMin;

    @SerializedName("temp_max")
    private final Float mTempMax;

    @SerializedName("pressure")
    private final Integer mPressure;

    @SerializedName("humidity")
    private final Integer mHumidity;

    public MainForecastInfoSchema(Float temp, Float feels_like, Float temp_min, Float temp_max, Integer pressure, Integer humidity) {
        this.mTemp = temp;
        this.mFeelsLike = feels_like;
        this.mTempMin = temp_min;
        this.mTempMax = temp_max;
        this.mPressure = pressure;
        this.mHumidity = humidity;
    }

    public Float getTemp() {
        return mTemp;
    }

    public Float getFeelsLike() {
        return mFeelsLike;
    }

    public Float getTempMin() {
        return mTempMin;
    }

    public Float getTempMax() {
        return mTempMax;
    }

    public Integer getPressure() {
        return mPressure;
    }

    public Integer getHumidity() {
        return mHumidity;
    }
}
