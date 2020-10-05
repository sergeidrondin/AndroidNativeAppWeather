package com.sergeidrondin.weather.forecast;

import com.sergeidrondin.weather.networking.common.WeatherSchema;
import com.sergeidrondin.weather.networking.onecall.DailyFeelsLikeSchema;
import com.sergeidrondin.weather.networking.onecall.DailyTemperatureSchema;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DailyForecast {
    private final Long mDt;

    private final Long mSunrise;

    private final Long mSunset;

    private final DailyTemperatureSchema mTemp;

    private final DailyFeelsLikeSchema mFeelsLike;

    private final Integer mPressure;

    private final Integer mHumidity;

    private final Float mWindSpeed;

    private final Float mUVI;

    private final List<WeatherSchema> mWeather;

    public DailyForecast(
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

    public String getWeatherSummary() {
        Long unixDateTime = getDt();

        Date date = new java.util.Date(unixDateTime*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm z");
        // TODO set timezone according to the city
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-7"));
        String formattedDate = sdf.format(date);

        return formattedDate + " " + getTemperature().getDay();
    }
}
