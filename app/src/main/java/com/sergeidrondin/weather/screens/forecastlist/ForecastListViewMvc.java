package com.sergeidrondin.weather.screens.forecastlist;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.views.ObservableViewMvc;

import java.util.List;

public interface ForecastListViewMvc extends ObservableViewMvc<ForecastListViewMvc.Listener> {

    interface Listener {
        void onForecastClicked(DailyForecast forecast);
    }

    void bindForecasts(List<DailyForecast> forecasts);

    void showLoading();

    void showForecasts();
}
