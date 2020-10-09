package com.sergeidrondin.weather.forecast;

import android.util.Log;

import com.sergeidrondin.weather.common.BaseObservable;
import com.sergeidrondin.weather.networking.WeatherApi;
import com.sergeidrondin.weather.networking.common.WeatherSchema;
import com.sergeidrondin.weather.networking.onecall.DailyFeelsLikeSchema;
import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;
import com.sergeidrondin.weather.networking.onecall.DailyTemperatureSchema;
import com.sergeidrondin.weather.networking.onecall.OneCallResponseSchema;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchOneCallForecastUseCase extends BaseObservable<FetchOneCallForecastUseCase.Listener> {

    public interface Listener {

        void onOneCallForecastFetched(List<DailyForecast> forecasts);

        void onOneCallForecastFetchFailed();
    }

    // TODO get device coords
    private final Double DEFAULT_LAT = 49.282729;
    private final Double DEFAULT_LON = -123.120737;
    private final WeatherApi mWeatherApi;

    public FetchOneCallForecastUseCase(WeatherApi weatherApi) {
        this.mWeatherApi = weatherApi;
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

    private void notifySuccess(OneCallResponseSchema body) {
        List<DailyForecast> forecasts = new ArrayList<>(1);

        for (DailyForecastSchema schema: body.getDaily()) {
            DailyForecast forecast = getDailyForecastFromSchema(schema);
            forecasts.add(forecast);
        }

        for (Listener listener : getListeners()) {
            listener.onOneCallForecastFetched(forecasts);
        }
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onOneCallForecastFetchFailed();
        }
    }

    public void fetchOneCallForecastAndNotify() {
        String requestUrl = mWeatherApi.fetchOneCallForecast(DEFAULT_LAT, DEFAULT_LON).request().url().toString();
        Log.i("requestUrl", requestUrl);
        mWeatherApi.fetchOneCallForecast(DEFAULT_LAT, DEFAULT_LON)
                .enqueue(new Callback<OneCallResponseSchema>() {
                    @Override
                    public void onResponse(Call<OneCallResponseSchema> call, Response<OneCallResponseSchema> response) {
                        if (response.isSuccessful()) {
                            notifySuccess(response.body());
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
}
