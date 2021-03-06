package com.sergeidrondin.weather.screens.forecastlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.ViewMvcFactory;
import com.sergeidrondin.weather.screens.common.navdrawer.NavDrawerHelper;
import com.sergeidrondin.weather.screens.common.toolbar.ToolbarViewMvc;
import com.sergeidrondin.weather.screens.common.views.BaseObservableViewMvc;

import java.util.List;

public class ForecastListViewMvcImpl extends BaseObservableViewMvc<ForecastListViewMvc.Listener>
        implements ForecastListViewMvc, ForecastRecyclerAdapter.OnForecastClickListener {

    private final RecyclerView mForecastRecyclerView;
    private final ProgressBar mLoadingIndicator;
    private final ForecastRecyclerAdapter mForecastRecyclerAdapter;
    private final Toolbar mToolbar;
    private final ToolbarViewMvc mToolbarViewMvc;
    private final NavDrawerHelper mNavDrawerHelper;

    public ForecastListViewMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory, NavDrawerHelper navDrawerHelper) {
        this.mNavDrawerHelper = navDrawerHelper;

        View rootView = inflater.inflate(R.layout.layout_forecast_list, parent, false);
        setRootView(rootView);

        Context context = getContext();

        mForecastRecyclerAdapter = new ForecastRecyclerAdapter(this, viewMvcFactory);

        mForecastRecyclerView = findViewById(R.id.recycler_forecasts);
        mLoadingIndicator = findViewById(R.id.forecast_list_progress);
        mToolbar = findViewById(R.id.toolbar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        mForecastRecyclerView.setLayoutManager(layoutManager);
        mForecastRecyclerView.setHasFixedSize(true);
        mForecastRecyclerView.setAdapter(mForecastRecyclerAdapter);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarViewMvc.getRootView());
        mToolbarViewMvc.setTitle(getString(R.string.forecast_list_title));
        mToolbarViewMvc.enableHamburgerButtonAndListen(new ToolbarViewMvc.HamburgerClickListener() {
            @Override
            public void onHamburgerClicked() {
                mNavDrawerHelper.openDrawer();
            }
        });
    }

    @Override
    public void bindForecasts(List<DailyForecast> forecasts) {
        mForecastRecyclerAdapter.bindForecasts(forecasts);
    }

    @Override
    public void showLoading() {
        mForecastRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void showForecasts() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mForecastRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onForecastClicked(DailyForecast forecast) {
        for (Listener listener: getListeners()) {
            listener.onForecastClicked(forecast);
        }
    }
}
