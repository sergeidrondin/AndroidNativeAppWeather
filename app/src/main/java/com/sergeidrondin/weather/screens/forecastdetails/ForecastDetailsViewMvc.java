package com.sergeidrondin.weather.screens.forecastdetails;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.views.ObservableViewMvc;

public interface ForecastDetailsViewMvc extends ObservableViewMvc<ForecastDetailsViewMvc.Listener> {
    interface Listener {
        void onNavigateUpClicked();
    }

    void renderForecastDetails(DailyForecast dailyForecast);
}
