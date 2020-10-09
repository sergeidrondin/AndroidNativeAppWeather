package com.sergeidrondin.weather.screens.forecastlist.forecastlistitem;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.views.ObservableViewMvc;

public interface ForecastListItemViewMvc extends ObservableViewMvc<ForecastListItemViewMvc.Listener> {

    public interface Listener {
        void onForecastClicked(DailyForecast forecast);
    }

    void bindForecast(DailyForecast dailyForecast);
}
