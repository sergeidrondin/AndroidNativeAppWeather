package com.sergeidrondin.weather.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.screens.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {
    private final TextView mTxtTitle;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        this.mTxtTitle = findViewById(R.id.toolbar_title_tv);
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }
}
