package com.sergeidrondin.weather.screens.forecastlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sergeidrondin.weather.screens.common.controllers.BaseFragment;

public class ForecastListFragment extends BaseFragment {

    public static Fragment newInstance() {
        return new ForecastListFragment();
    }

    private ForecastListController mForecastListController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ForecastListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getForecastListViewMvc(container);
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
}
