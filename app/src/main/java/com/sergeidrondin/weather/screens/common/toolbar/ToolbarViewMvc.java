package com.sergeidrondin.weather.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.screens.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {
    public interface NavigateUpClickListener {
        void onNavigateUpClicked();
    }

    public interface HamburgerClickListener {
        void onHamburgerClicked();
    }

    private final TextView mTxtTitle;
    private final ImageButton mBtnBack;
    private final ImageButton mBtnHamburger;
    private NavigateUpClickListener mNavigateUpClickListener;
    private HamburgerClickListener mHamburgerClickListener;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTxtTitle = findViewById(R.id.toolbar_title_tv);
        mBtnBack = findViewById(R.id.btn_back);
        mBtnHamburger = findViewById(R.id.btn_hamburger);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigateUpClickListener.onNavigateUpClicked();
            }
        });

        mBtnHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHamburgerClickListener.onHamburgerClicked();
            }
        });
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    public void enableHamburgerButtonAndListen(HamburgerClickListener hamburgerClickListener) {
        if (mNavigateUpClickListener != null) {
            throw new RuntimeException("hamburger and up shouldn't be shown together");
        }
        mHamburgerClickListener = hamburgerClickListener;
        mBtnHamburger.setVisibility(View.VISIBLE);
    }

    public void enableUpButtonAndListen(NavigateUpClickListener navigateUpClickListener) {
        if (mHamburgerClickListener != null) {
            throw new RuntimeException("hamburger and up shouldn't be shown together");
        }
        mNavigateUpClickListener = navigateUpClickListener;
        mBtnBack.setVisibility(View.VISIBLE);
    }
}
