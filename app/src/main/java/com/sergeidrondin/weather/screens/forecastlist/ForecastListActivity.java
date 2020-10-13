package com.sergeidrondin.weather.screens.forecastlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.screens.common.controllers.BackPressedListener;
import com.sergeidrondin.weather.screens.common.controllers.BaseActivity;

public class ForecastListActivity extends BaseActivity {

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, ForecastListActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private BackPressedListener mBackPressedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        ForecastListFragment fragment;
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragment = new ForecastListFragment();
            ft.add(R.id.frame_content, fragment).commit();
        } else {
            fragment = (ForecastListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content);
        }
        mBackPressedListener = fragment;
    }

    @Override
    public void onBackPressed() {
        if (!mBackPressedListener.onBackPressed()) {
            super.onBackPressed();
        }
    }
}