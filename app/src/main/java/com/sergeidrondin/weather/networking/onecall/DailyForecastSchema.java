package com.sergeidrondin.weather.networking.onecall;

import com.google.gson.annotations.SerializedName;
import com.sergeidrondin.weather.networking.common.WeatherSchema;

import java.util.List;

public class DailyForecastSchema {
    @SerializedName("dt")
    private final Long mDt;

    @SerializedName("sunrise")
    private final Long mSunrise;

    @SerializedName("sunset")
    private final Long mSunset;

    @SerializedName("temp")
    private final DailyTemperatureSchema mTemp;

    @SerializedName("feels_like")
    private final DailyFeelsLikeSchema mFeelsLike;

    @SerializedName("pressure")
    private final Integer mPressure;

    @SerializedName("humidity")
    private final Integer mHumidity;

    @SerializedName("wind_speed")
    private final Float mWindSpeed;

    @SerializedName("uvi")
    private final Float mUVI;

    @SerializedName("weather")
    private final List<WeatherSchema> mWeather;

    public DailyForecastSchema(
            Long dt,
            Long sunrise,
            Long sunset,
            DailyTemperatureSchema temp,
            DailyFeelsLikeSchema feelsLike,
            Integer pressure,
            Integer humidity,
            Float windSpeed,
            Float uvi,
            List<WeatherSchema> weather
    ) {
        this.mDt = dt;
        this.mSunrise = sunrise;
        this.mSunset = sunset;
        this.mTemp = temp;
        this.mFeelsLike = feelsLike;
        this.mPressure = pressure;
        this.mHumidity = humidity;
        this.mWindSpeed = windSpeed;
        this.mUVI = uvi;
        this.mWeather = weather;
    }

    public Long getDt() {
        return mDt;
    }

    public Long getSunrise() {
        return mSunrise;
    }

    public Long getSunset() {
        return mSunset;
    }

    public DailyTemperatureSchema getTemperature() {
        return mTemp;
    }

    public DailyFeelsLikeSchema getFeelsLike() {
        return mFeelsLike;
    }

    public Integer getPressure() {
        return mPressure;
    }

    public Integer getHumidity() {
        return mHumidity;
    }

    public Float getWindSpeed() {
        return mWindSpeed;
    }

    public Float getUVI() {
        return mUVI;
    }

    public List<WeatherSchema> getWeatherList() {
        return mWeather;
    }
}
