package com.sergeidrondin.weather.screens.forecastlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sergeidrondin.weather.screens.common.controllers.BackPressedListener;
import com.sergeidrondin.weather.screens.common.controllers.BaseFragment;

public class ForecastListFragment extends BaseFragment implements BackPressedListener {

    private ForecastListController mForecastListController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ForecastListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getForecastListViewMvc(null);
        mForecastListController = getCompositionRoot().getForecastListController();

        mForecastListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mForecastListController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mForecastListController.onStop();
    }

    @Override
    public boolean onBackPressed() {
        return mForecastListController.onBackPressed();
    }
}
