package com.sergeidrondin.weather.screens.common.toastshelper;

import android.content.Context;
import android.widget.Toast;

import com.sergeidrondin.weather.R;

public class ToastsHelper {
    private final Context mContext;

    public ToastsHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void showUseCaseError() {
        Toast.makeText(mContext, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }
}
