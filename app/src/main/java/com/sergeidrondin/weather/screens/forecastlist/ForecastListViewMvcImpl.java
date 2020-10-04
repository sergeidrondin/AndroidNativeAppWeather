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
import com.sergeidrondin.weather.screens.common.BaseObservableViewMvc;
import com.sergeidrondin.weather.screens.common.BaseViewMvc;
import com.sergeidrondin.weather.screens.common.ObservableViewMvc;

import java.util.ArrayList;
import java.util.List;

public class ForecastListViewMvcImpl extends BaseObservableViewMvc<ForecastListViewMvc.Listener> implements ForecastRecyclerAdapter.OnForecastClickListener, ForecastListViewMvc {

    private RecyclerView mForecastRecyclerView;
    private ProgressBar mLoadingIndicator;
    private ForecastRecyclerAdapter mForecastRecyclerAdapter;

    public ForecastListViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_forecast_list, parent, false));
        Context context = getContext();

        mForecastRecyclerAdapter = new ForecastRecyclerAdapter(this, context);

        mForecastRecyclerView = findViewById(R.id.recycler_forecasts);
        mLoadingIndicator = findViewById(R.id.forecast_list_progress);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        mForecastRecyclerView.setLayoutManager(layoutManager);
        mForecastRecyclerView.setHasFixedSize(true);
        mForecastRecyclerView.setAdapter(mForecastRecyclerAdapter);
    }

    @Override
    public void bindForecasts(List<DailyForecastSchema> forecasts) {
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
    public void onForecastClicked(DailyForecastSchema forecast) {
        for (Listener listener: getListeners()) {
            listener.onForecastClicked(forecast);
        }
    }
}
