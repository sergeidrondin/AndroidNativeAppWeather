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
import com.sergeidrondin.weather.networking.common.WeatherSchema;
import com.sergeidrondin.weather.networking.onecall.CurrentWeatherSchema;
import com.sergeidrondin.weather.networking.onecall.OneCallResponseSchema;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String DEFAULT_CITY = "Vancouver";
    private final Double DEFAULT_LAT = 49.282729;
    private final Double DEFAULT_LON = -123.120737;


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

        fetchOneCallForecast();
    }

    public void onBtnClick(View v) {
        fetchOneCallForecast();
    }

    public void fetchWeather() {
        String requestUrl = mWeatherApi.fetchCityWeatherForecast(DEFAULT_CITY).request().url().toString();
        mWeatherApi.fetchCityWeatherForecast(DEFAULT_CITY)
            .enqueue(new Callback<ForecastResponseSchema>() {
                @Override
                public void onResponse(Call<ForecastResponseSchema> call, Response<ForecastResponseSchema> response) {
                    if (response.isSuccessful()) {
                        notifySuccess(response.body().getMainInfo(), response.body().getWeatherList());
                    } else {
                        notifyFailure();
                    }
                }

                @Override
                public void onFailure(Call<ForecastResponseSchema> call, Throwable t) {
                    String message = t.getMessage();
                    notifyFailure();
                }
            } );
    }

    public void fetchOneCallForecast() {
        String requestUrl = mWeatherApi.fetchOneCallForecast(DEFAULT_LAT, DEFAULT_LON).request().url().toString();
        Log.i("requestUrl", requestUrl);
        mWeatherApi.fetchOneCallForecast(DEFAULT_LAT, DEFAULT_LON)
                .enqueue(new Callback<OneCallResponseSchema>() {
                    @Override
                    public void onResponse(Call<OneCallResponseSchema> call, Response<OneCallResponseSchema> response) {
                        if (response.isSuccessful()) {
                            notifyOneCallSuccess(response.body());
                        } else {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<OneCallResponseSchema> call, Throwable t) {
                        String message = t.getMessage();
                        Log.d("onFailure", message);
                        notifyFailure();
                    }
                } );
    }

    private void notifyOneCallSuccess(OneCallResponseSchema body) {
        CurrentWeatherSchema current = body.getCurrent();

        String temperature = String.valueOf(current.getTemperature());
        m_temperature_tv.setText(temperature);

        String description = current.getWeatherList().get(0).getDescription();
        m_description_tv.setText(description);
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