package com.sergeidrondin.weather.screens.forecastdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.navdrawer.DrawerItems;
import com.sergeidrondin.weather.screens.common.screensnavigator.ScreensNavigator;
import com.sergeidrondin.weather.screens.common.toastshelper.ToastsHelper;
import com.sergeidrondin.weather.screens.common.controllers.BaseActivity;

public class ForecastDetailsActivity extends BaseActivity implements ForecastDetailsViewMvc.Listener {
    private ForecastDetailsViewMvc mForecastDetailsViewMvc;
    private ToastsHelper mToastsHelper;
    private ScreensNavigator mScreensNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mForecastDetailsViewMvc = getCompositionRoot().getViewMvcFactory().getForecastDetailsViewMvc(null);
        mToastsHelper = getCompositionRoot().getMessagesDisplayer();
        mScreensNavigator = getCompositionRoot().getScreensNavigator();

        Intent intent = getIntent();
        String dailyForecastExtraTag = String.valueOf(R.string.daily_forecast_extra_tag);
        if (intent.hasExtra(dailyForecastExtraTag)) {
            DailyForecast dailyForecast = (DailyForecast) intent.getSerializableExtra(dailyForecastExtraTag);
            mForecastDetailsViewMvc.renderForecastDetails(dailyForecast);
        } else {
            mToastsHelper.showUseCaseError();
            onBackPressed();
        }

        setContentView(mForecastDetailsViewMvc.getRootView());
    }

    public static void start(Context context, DailyForecast dailyForecast) {
        Intent intent = new Intent(context, ForecastDetailsActivity.class);
        String dailyForecastTag = String.valueOf(R.string.daily_forecast_extra_tag);
        intent.putExtra(dailyForecastTag, dailyForecast);
        context.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mForecastDetailsViewMvc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mForecastDetailsViewMvc.unregisterListener(this);
    }

    @Override
    public void onNavigateUpClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (mForecastDetailsViewMvc.isDrawerOpen()) {
            mForecastDetailsViewMvc.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDrawerItemClicked(DrawerItems item) {
        switch(item) {
            case FORECAST_LIST:
                mScreensNavigator.toForecastListClearTop();
        }
    }
}