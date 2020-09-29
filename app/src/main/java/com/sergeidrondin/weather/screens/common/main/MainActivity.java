package com.sergeidrondin.weather.screens.common.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.common.WeatherApplication;
import com.sergeidrondin.weather.networking.WeatherApi;
import com.sergeidrondin.weather.networking.forecast.ForecastResponseSchema;
import com.sergeidrondin.weather.networking.forecast.MainForecastInfoSchema;
import com.sergeidrondin.weather.networking.forecast.WeatherSchema;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String DEFAULT_CITY = "Vancouver";

    private WeatherApi mWeatherApi;

    private TextView m_city_tv;
    private TextView m_temperature_tv;
    private TextView m_description_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mWeatherApi = ((WeatherApplication) getApplication()).getCompositionRoot().getWeatherApi();

        m_city_tv = findViewById(R.id.main_city);
        m_temperature_tv = findViewById(R.id.main_temperature);
        m_description_tv = findViewById(R.id.main_description);

        m_city_tv.setText(DEFAULT_CITY);

//        fetchWeather();
    }

    public void onBtnClick(View v) {
        Log.d("onBtnClick", "onBtnClick");
        fetchWeather();
    }

    public void fetchWeather() {
        String requestUrl = mWeatherApi.fetchCityWeatherForecast(DEFAULT_CITY).request().url().toString();
        Log.i("URLLL: ", requestUrl);
        mWeatherApi.fetchCityWeatherForecast(DEFAULT_CITY)
            .enqueue(new Callback<ForecastResponseSchema>() {
                @Override
                public void onResponse(Call<ForecastResponseSchema> call, Response<ForecastResponseSchema> response) {
                    Log.i("RESPONSE", response.body().toString());
                    if (response.isSuccessful()) {
                        notifySuccess(response.body().getMainInfo(), response.body().getWeatherList());
                    } else {
                        notifyFailure();
                    }
                }

                @Override
                public void onFailure(Call<ForecastResponseSchema> call, Throwable t) {
                    String message = t.getMessage();
                    Log.d("FAILURERRRR", message);
                    notifyFailure();
                }
            } );
    }

    private void notifyFailure() {
        m_description_tv.setText("Some Network error");

    }

    private void notifySuccess(MainForecastInfoSchema mainInfo, List<WeatherSchema> weatherList) {
        String temperature = String.valueOf(mainInfo.getTemp());
        m_temperature_tv.setText(temperature);

        String description = weatherList.get(0).getDescription();
        m_description_tv.setText(description);
    }
}