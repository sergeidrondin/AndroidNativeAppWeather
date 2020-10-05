package com.sergeidrondin.weather.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.sergeidrondin.weather.screens.forecastlist.ForecastListViewMvc;
import com.sergeidrondin.weather.screens.forecastlist.ForecastListViewMvcImpl;
import com.sergeidrondin.weather.screens.forecastlist.forecastlistitem.ForecastListItemViewMvc;
import com.sergeidrondin.weather.screens.forecastlist.forecastlistitem.ForecastListItemViewMvcImpl;

public class ViewMvcFactory {
    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public ForecastListViewMvc getForecastListViewMvc(@Nullable ViewGroup parent) {
        return new ForecastListViewMvcImpl(mLayoutInflater, parent, this);
    }

    public ForecastListItemViewMvc getForecastListItemViewMvc(@Nullable ViewGroup parent) {
        return new ForecastListItemViewMvcImpl(mLayoutInflater, parent);
    }
}
