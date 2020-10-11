package com.sergeidrondin.weather.screens.forecastlist;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.navdrawer.NavDrawerViewMvc;
import com.sergeidrondin.weather.screens.common.views.ObservableViewMvc;

import java.util.List;

public interface ForecastListViewMvc extends ObservableViewMvc<ForecastListViewMvc.Listener>, NavDrawerViewMvc {

    public interface Listener {
        void onForecastClicked(DailyForecast forecast);

        void onForecastListClicked();
    }

    void bindForecasts(List<DailyForecast> forecasts);

    void showLoading();

    void showForecasts();
}
