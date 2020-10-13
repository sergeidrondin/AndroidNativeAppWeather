package com.sergeidrondin.weather.screens.common.main;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.screens.common.controllers.BackPressDispatcher;
import com.sergeidrondin.weather.screens.common.controllers.BackPressedListener;
import com.sergeidrondin.weather.screens.common.controllers.BaseActivity;
import com.sergeidrondin.weather.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.sergeidrondin.weather.screens.common.screensnavigator.ScreensNavigator;
import com.sergeidrondin.weather.screens.forecastlist.ForecastListFragment;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements BackPressDispatcher, FragmentFrameWrapper {

    private final Set<BackPressedListener> mBackPressedListeners = new HashSet<>();
    private ScreensNavigator mScreensNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();

        if (savedInstanceState == null) {
            mScreensNavigator.toForecastList();
        }
    }

    @Override
    public void onBackPressed() {

        boolean isBackPressConsumedByAnyListener = false;
        for (BackPressedListener listener: mBackPressedListeners) {
            if (listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true;
            }
        }
        if (!isBackPressConsumedByAnyListener) {
            super.onBackPressed();
        }
    }

    @Override
    public void registerListener(BackPressedListener listener) {
        mBackPressedListeners.add(listener);
    }

    @Override
    public void unregisterListener(BackPressedListener listener) {
        mBackPressedListeners.remove(listener);
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return findViewById(R.id.frame_content);
    }
}