package com.sergeidrondin.weather.screens.forecastlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sergeidrondin.weather.screens.common.controllers.BaseActivity;

public class ForecastListActivity extends BaseActivity {

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, ForecastListActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

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

    @Override
    public void onBackPressed() {
        if (!mForecastListController.onBackPressed()) {
            super.onBackPressed();
        }
    }
}