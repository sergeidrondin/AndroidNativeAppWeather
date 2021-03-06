package com.sergeidrondin.weather.networking;

import com.sergeidrondin.weather.common.Constants;
import com.sergeidrondin.weather.networking.forecast.ForecastResponseSchema;
import com.sergeidrondin.weather.networking.onecall.OneCallResponseSchema;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?appid=" + Constants.OPEN_WEATHER_MAP_API_KEY + "&units=metric")
    Call<ForecastResponseSchema> fetchCityWeatherForecast(@Query("q") String city);

    @GET("onecall?appid=" + Constants.OPEN_WEATHER_MAP_API_KEY + "&units=metric" + "&exclude=minutely,hourly,alerts")
    Call<OneCallResponseSchema> fetchOneCallForecast(@Query("lat") Double lat, @Query("lon") Double lon);
}
