package com.sergeidrondin.weather.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.screens.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {
    private final TextView mTxtTitle;
    private final ImageButton mBtnBack;
    private NavigateUpClickListener mNavigateUpClickListener;

    public interface NavigateUpClickListener {
        void onNavigateUpClicked();
    }

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTxtTitle = findViewById(R.id.toolbar_title_tv);
        mBtnBack = findViewById(R.id.btn_back);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigateUpClickListener.onNavigateUpClicked();
            }
        });
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    public void enableUpButtonAndListen(NavigateUpClickListener navigateUpClickListener) {
        mNavigateUpClickListener = navigateUpClickListener;
        mBtnBack.setVisibility(View.VISIBLE);
    }
}
