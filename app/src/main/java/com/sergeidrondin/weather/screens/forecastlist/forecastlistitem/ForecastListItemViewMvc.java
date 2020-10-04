package com.sergeidrondin.weather.screens.forecastlist.forecastlistitem;

import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;
import com.sergeidrondin.weather.screens.common.ObservableViewMvc;

public interface ForecastListItemViewMvc extends ObservableViewMvc<ForecastListItemViewMvc.Listener> {

    public interface Listener {
        void onForecastClicked(DailyForecastSchema forecast);
    }

    void bindForecast(DailyForecastSchema dailyForecastSchema);
}
