package com.sergeidrondin.weather.screens.forecastdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.controllers.BaseFragment;
import com.sergeidrondin.weather.screens.common.screensnavigator.ScreensNavigator;
import com.sergeidrondin.weather.screens.common.toastshelper.ToastsHelper;

public class ForecastDetailsFragment extends BaseFragment implements ForecastDetailsViewMvc.Listener {

    public static ForecastDetailsFragment newInstance(DailyForecast dailyForecast) {
        Bundle bundle = new Bundle();
        String dailyForecastExtraTag = String.valueOf(R.string.daily_forecast_extra_tag);
        bundle.putSerializable(dailyForecastExtraTag, dailyForecast);
        ForecastDetailsFragment fragment = new ForecastDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private ForecastDetailsViewMvc mForecastDetailsViewMvc;
    private ToastsHelper mToastsHelper;
    private ScreensNavigator mScreensNavigator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mForecastDetailsViewMvc = getCompositionRoot().getViewMvcFactory().getForecastDetailsViewMvc(container);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        mToastsHelper = getCompositionRoot().getToastsHelper();

        Bundle bundle = getArguments();
        if (bundle != null) {
            String dailyForecastExtraTag = String.valueOf(R.string.daily_forecast_extra_tag);
            DailyForecast dailyForecast = (DailyForecast) bundle.getSerializable(dailyForecastExtraTag);
            mForecastDetailsViewMvc.renderForecastDetails(dailyForecast);
        } else {
            mToastsHelper.showUseCaseError();
            mScreensNavigator.toForecastList();
        }

        return mForecastDetailsViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mForecastDetailsViewMvc.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mForecastDetailsViewMvc.unregisterListener(this);
    }

    @Override
    public void onNavigateUpClicked() {
        mScreensNavigator.navigateUp();
    }
}
