package com.sergeidrondin.weather.screens.common.navdrawer;

import android.widget.FrameLayout;

import com.sergeidrondin.weather.screens.common.views.ObservableViewMvc;

public interface NavDrawerViewMvc extends ObservableViewMvc<NavDrawerViewMvc.Listener> {

    interface Listener {
        void onForecastListClicked();
    }

    FrameLayout getFragmentFrame();
    boolean isDrawerOpen();
    void openDrawer();
    void closeDrawer();
}
