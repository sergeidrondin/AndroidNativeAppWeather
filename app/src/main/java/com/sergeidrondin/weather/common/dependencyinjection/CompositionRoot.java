package com.sergeidrondin.weather.common.dependencyinjection;

import com.sergeidrondin.weather.common.Constants;
import com.sergeidrondin.weather.networking.WeatherApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {
    private Retrofit mRetrofit;

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public WeatherApi getWeatherApi() {
        return getRetrofit().create(WeatherApi.class);
    }

}
