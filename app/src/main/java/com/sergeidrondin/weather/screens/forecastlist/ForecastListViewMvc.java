package com.sergeidrondin.weather.screens.forecastlist;

import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;
import com.sergeidrondin.weather.screens.common.ObservableViewMvc;

import java.util.List;

public interface ForecastListViewMvc extends ObservableViewMvc<ForecastListViewMvc.Listener> {

    public interface Listener {
        void onForecastClicked(DailyForecastSchema forecast);
    }

    void bindForecasts(List<DailyForecastSchema> forecasts);

    void showLoading();

    void showForecasts();
}
