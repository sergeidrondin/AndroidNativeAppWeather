package com.sergeidrondin.weather.screens.forecastlist;

import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sergeidrondin.weather.forecast.DailyFeelsLike;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.forecast.DailyTemperature;
import com.sergeidrondin.weather.forecast.WeatherInfo;
import com.sergeidrondin.weather.networking.WeatherApi;
import com.sergeidrondin.weather.networking.common.WeatherSchema;
import com.sergeidrondin.weather.networking.onecall.DailyFeelsLikeSchema;
import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;
import com.sergeidrondin.weather.networking.onecall.DailyTemperatureSchema;
import com.sergeidrondin.weather.networking.onecall.OneCallResponseSchema;
import com.sergeidrondin.weather.screens.common.controllers.BaseActivity;
import com.sergeidrondin.weather.screens.forecastdetails.ForecastDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastListActivity extends BaseActivity implements ForecastListViewMvcImpl.Listener {

    private ForecastListViewMvc mViewMvc;

    private final Double DEFAULT_LAT = 49.282729;
    private final Double DEFAULT_LON = -123.120737;

    private WeatherApi mWeatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mViewMvc = getCompositionRoot().getViewMvcFactory().getForecastListViewMvc(null);
        mViewMvc.registerListener(this);

        mWeatherApi = getCompositionRoot().getWeatherApi();

        fetchOneCallForecast();

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
    }

    public void fetchOneCallForecast() {
        mViewMvc.showLoading();
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

    private DailyForecast getDailyForecastFromSchema(DailyForecastSchema schema) {
        DailyTemperatureSchema temperatureSchema = schema.getTemperature();
        DailyTemperature dailyTemperature = new DailyTemperature(
                temperatureSchema.getDay(),
                temperatureSchema.getMin(),
                temperatureSchema.getMax(),
                temperatureSchema.getNight(),
                temperatureSchema.getEve(),
                temperatureSchema.getMorn()
        );

        DailyFeelsLikeSchema feelsLikeSchema = schema.getFeelsLike();
        DailyFeelsLike feelsLike = new DailyFeelsLike(
                feelsLikeSchema.getDay(),
                feelsLikeSchema.getNight(),
                feelsLikeSchema.getEve(),
                feelsLikeSchema.getMorn()
        );

        List<WeatherInfo> weatherInfos = new ArrayList<>(1);
        for (WeatherSchema weatherSchema: schema.getWeatherList()) {
            weatherInfos.add(new WeatherInfo(
                    weatherSchema.getId(),
                    weatherSchema.getMain(),
                    weatherSchema.getDescription(),
                    weatherSchema.getIcon()
            ));
        }

        return new DailyForecast(
                schema.getDt(),
                schema.getSunrise(),
                schema.getSunset(),
                dailyTemperature,
                feelsLike,
                schema.getPressure(),
                schema.getHumidity(),
                schema.getWindSpeed(),
                schema.getUVI(),
                weatherInfos
        );
    }

    private void notifyOneCallSuccess(OneCallResponseSchema body) {
        List<DailyForecast> forecasts = new ArrayList<>(1);

        for (DailyForecastSchema schema: body.getDaily()) {
            DailyForecast forecast = getDailyForecastFromSchema(schema);
            forecasts.add(forecast);
        }
        mViewMvc.bindForecasts(forecasts);
        mViewMvc.showForecasts();
    }

    private void notifyFailure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Cannot fetch forecast!");
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onForecastClicked(DailyForecast forecast) {
        Toast.makeText(this, forecast.getWeatherSummary(), Toast.LENGTH_SHORT).show();
        ForecastDetailsActivity.start(this, forecast);
    }
}