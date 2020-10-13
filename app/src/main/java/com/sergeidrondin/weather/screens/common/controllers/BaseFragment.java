package com.sergeidrondin.weather.screens.common.controllers;

import androidx.fragment.app.Fragment;

import com.sergeidrondin.weather.common.WeatherApplication;
import com.sergeidrondin.weather.common.dependencyinjection.ActivityCompositionRoot;
import com.sergeidrondin.weather.common.dependencyinjection.ControllerCompositionRoot;

public class BaseFragment extends Fragment {

    private ActivityCompositionRoot mActivityCompositionRoot;
    private ControllerCompositionRoot mControllerCompositionRoot;

    public ActivityCompositionRoot getActivityCompositionRoot() {
        if (mActivityCompositionRoot == null) {
            mActivityCompositionRoot = new ActivityCompositionRoot(
                    ((WeatherApplication) requireActivity().getApplication()).getCompositionRoot(),
                    requireActivity()
            );
        }
        return mActivityCompositionRoot;
    }


    protected ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(
                getActivityCompositionRoot()
            );
        }
        return mControllerCompositionRoot;
    }

}
