package com.sergeidrondin.weather.screens.common.controllers;

import androidx.appcompat.app.AppCompatActivity;

import com.sergeidrondin.weather.common.WeatherApplication;
import com.sergeidrondin.weather.common.dependencyinjection.ActivityCompositionRoot;
import com.sergeidrondin.weather.common.dependencyinjection.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private ActivityCompositionRoot mActivityCompositionRoot;
    private ControllerCompositionRoot mControllerCompositionRoot;

    public ActivityCompositionRoot getActivityCompositionRoot() {
        if (mActivityCompositionRoot == null) {
            mActivityCompositionRoot = new ActivityCompositionRoot(
                    ((WeatherApplication) getApplication()).getCompositionRoot(),
                    this
            );
        }
        return mActivityCompositionRoot;
    }

    protected ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(getActivityCompositionRoot());
        }
        return mControllerCompositionRoot;
    }
}
