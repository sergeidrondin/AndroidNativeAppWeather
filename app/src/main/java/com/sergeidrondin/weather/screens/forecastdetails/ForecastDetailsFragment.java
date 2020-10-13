package com.sergeidrondin.weather.screens.forecastdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.controllers.BackPressDispatcher;
import com.sergeidrondin.weather.screens.common.controllers.BackPressedListener;
import com.sergeidrondin.weather.screens.common.controllers.BaseFragment;
import com.sergeidrondin.weather.screens.common.navdrawer.DrawerItems;
import com.sergeidrondin.weather.screens.common.screensnavigator.ScreensNavigator;
import com.sergeidrondin.weather.screens.common.toastshelper.ToastsHelper;

public class ForecastDetailsFragment extends BaseFragment implements BackPressedListener, ForecastDetailsViewMvc.Listener {

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
    private BackPressDispatcher mBackPressDispatcher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mForecastDetailsViewMvc = getCompositionRoot().getViewMvcFactory().getForecastDetailsViewMvc(container);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        mBackPressDispatcher = getCompositionRoot().getBackPressDispatcher();
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
        mBackPressDispatcher.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mForecastDetailsViewMvc.unregisterListener(this);
        mBackPressDispatcher.unregisterListener(this);
    }

    @Override
    public void onNavigateUpClicked() {
        mScreensNavigator.toForecastList();
    }

    @Override
    public boolean onBackPressed() {
        if (mForecastDetailsViewMvc.isDrawerOpen()) {
            mForecastDetailsViewMvc.closeDrawer();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDrawerItemClicked(DrawerItems item) {
        switch(item) {
            case FORECAST_LIST:
                mScreensNavigator.toForecastList();
        }
    }
}
