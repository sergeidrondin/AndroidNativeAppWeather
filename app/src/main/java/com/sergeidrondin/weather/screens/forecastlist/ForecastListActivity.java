package com.sergeidrondin.weather.screens.forecastlist;

import android.os.Bundle;

import com.sergeidrondin.weather.screens.common.controllers.BaseActivity;

public class ForecastListActivity extends BaseActivity {

    private ForecastListController mForecastListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ForecastListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getForecastListViewMvc(null);
        mForecastListController = getCompositionRoot().getForecastListController();

        mForecastListController.bindView(viewMvc);

        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mForecastListController.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mForecastListController.onStop();
    }
}