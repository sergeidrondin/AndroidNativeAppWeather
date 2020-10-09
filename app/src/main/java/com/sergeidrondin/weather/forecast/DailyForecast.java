package com.sergeidrondin.weather.forecast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DailyForecast implements Serializable {
    private final Long mDt;

    private final Long mSunrise;

    private final Long mSunset;

    private final DailyTemperature mTemp;

    private final DailyFeelsLike mFeelsLike;

    private final Integer mPressure;

    private final Integer mHumidity;

    private final Float mWindSpeed;

    private final Float mUVI;

    private final List<WeatherInfo> mWeather;

    public DailyForecast(
            Long dt,
            Long sunrise,
            Long sunset,
            DailyTemperature temp,
            DailyFeelsLike feelsLike,
            Integer pressure,
            Integer humidity,
            Float windSpeed,
            Float uvi,
            List<WeatherInfo> weather
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

    public DailyTemperature getTemperature() {
        return mTemp;
    }

    public DailyFeelsLike getFeelsLike() {
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

    public List<WeatherInfo> getWeatherList() {
        return mWeather;
    }

    public String getFormattedDate() {
        Long unixDateTime = getDt();

        Date date = new java.util.Date(unixDateTime*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
        TimeZone timeZone = TimeZone.getDefault();
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    public String getWeatherSummary() {
        return getFormattedDate() + " " + getTemperature().getDay();
    }
}
