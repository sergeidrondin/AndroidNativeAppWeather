package com.sergeidrondin.weather.screens.forecastdetails;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.views.ViewMvc;

public interface ForecastDetailsViewMvc extends ViewMvc {
    void renderForecastDetails(DailyForecast dailyForecast);
}
