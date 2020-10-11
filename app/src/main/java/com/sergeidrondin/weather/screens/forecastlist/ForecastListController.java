package com.sergeidrondin.weather.screens.forecastlist;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.forecast.FetchOneCallForecastUseCase;
import com.sergeidrondin.weather.screens.common.toastshelper.ToastsHelper;
import com.sergeidrondin.weather.screens.common.screensnavigator.ScreensNavigator;

import java.util.List;

public class ForecastListController implements ForecastListViewMvcImpl.Listener, FetchOneCallForecastUseCase.Listener {

    private final FetchOneCallForecastUseCase mFetchOneCallForecastUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final ToastsHelper mToastsHelper;

    private ForecastListViewMvc mViewMvc;

    public ForecastListController(
            FetchOneCallForecastUseCase fetchOneCallForecastUseCase,
            ScreensNavigator screensNavigator,
            ToastsHelper mToastsHelper
    ) {
        this.mFetchOneCallForecastUseCase = fetchOneCallForecastUseCase;
        this.mScreensNavigator = screensNavigator;
        this.mToastsHelper = mToastsHelper;
    }

    public void bindView(ForecastListViewMvc viewMvc) {
        mViewMvc = viewMvc;
    }

    public void onStart() {
        mFetchOneCallForecastUseCase.registerListener(this);
        mViewMvc.showLoading();
        mFetchOneCallForecastUseCase.fetchOneCallForecastAndNotify();
        mViewMvc.registerListener(this);
    }

    public void onStop() {
        mFetchOneCallForecastUseCase.unregisterListener(this);
        mViewMvc.unregisterListener(this);
    }

    @Override
    public void onForecastClicked(DailyForecast forecast) {
        mScreensNavigator.toForecastDetails(forecast);
    }

    @Override
    public void onForecastListClicked() {
        // this is already ForecastList - so no operation required
    }

    @Override
    public void onOneCallForecastFetched(List<DailyForecast> forecasts) {
        mViewMvc.bindForecasts(forecasts);
        mViewMvc.showForecasts();
    }

    @Override
    public void onOneCallForecastFetchFailed() {
        mViewMvc.showForecasts();
        mToastsHelper.showUseCaseError();
    }
}
