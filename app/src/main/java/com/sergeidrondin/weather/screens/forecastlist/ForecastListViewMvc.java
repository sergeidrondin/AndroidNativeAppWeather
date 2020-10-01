package com.sergeidrondin.weather.screens.forecastlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;

import java.util.List;

public class ForecastListViewMvc {
    private final View mRootView;

    private RecyclerView mForecastRecyclerView;
    private ProgressBar mLoadingIndicator;
    private ForecastRecyclerAdapter mForecastRecyclerAdapter;

    public ForecastListViewMvc(LayoutInflater inflater, ViewGroup parent) {
        mRootView = inflater.inflate(R.layout.layout_forecast_list, parent, false);

        Context context = mRootView.getContext();

        mForecastRecyclerAdapter = new ForecastRecyclerAdapter(context);

        mForecastRecyclerView = findViewById(R.id.recycler_forecasts);
        mLoadingIndicator = findViewById(R.id.forecast_list_progress);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        mForecastRecyclerView.setLayoutManager(layoutManager);
        mForecastRecyclerView.setHasFixedSize(true);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mForecastRecyclerView.setAdapter(mForecastRecyclerAdapter);
    }

    protected <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    public View getRootView() {
        return mRootView;
    }

    public void bindForecasts(List<DailyForecastSchema> forecasts) {
        mForecastRecyclerAdapter.bindForecasts(forecasts);
    }

    public void showLoading() {
        mForecastRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    public void showForecasts() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mForecastRecyclerView.setVisibility(View.VISIBLE);

    }
}
