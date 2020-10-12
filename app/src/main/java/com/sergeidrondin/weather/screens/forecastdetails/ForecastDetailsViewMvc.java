package com.sergeidrondin.weather.screens.forecastdetails;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.navdrawer.DrawerItems;
import com.sergeidrondin.weather.screens.common.navdrawer.NavDrawerViewMvc;
import com.sergeidrondin.weather.screens.common.views.ObservableViewMvc;

public interface ForecastDetailsViewMvc extends ObservableViewMvc<ForecastDetailsViewMvc.Listener>,
        NavDrawerViewMvc {
    public interface Listener {
        void onNavigateUpClicked();

        void onDrawerItemClicked(DrawerItems item);
    }

    void renderForecastDetails(DailyForecast dailyForecast);
}
