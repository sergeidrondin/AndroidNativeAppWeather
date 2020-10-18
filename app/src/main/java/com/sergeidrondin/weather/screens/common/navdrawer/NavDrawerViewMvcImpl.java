package com.sergeidrondin.weather.screens.common.navdrawer;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.screens.common.views.BaseObservableViewMvc;

public class NavDrawerViewMvcImpl extends BaseObservableViewMvc<NavDrawerViewMvc.Listener>
    implements NavDrawerViewMvc {

    private final DrawerLayout mDrawerLayout;
    private final FrameLayout mFrameLayout;
    private final NavigationView mNavigationView;

    public NavDrawerViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_drawer, parent, false));
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mFrameLayout = findViewById(R.id.frame_content);
        mNavigationView = findViewById(R.id.nav_view);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                if (item.getItemId() == R.id.drawer_menu_forecast_list) {
                    for (Listener listener: getListeners()) {
                        listener.onForecastListClicked();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return mFrameLayout;
    }

    @Override
    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }
}
