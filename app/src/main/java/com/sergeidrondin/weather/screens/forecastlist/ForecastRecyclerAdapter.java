package com.sergeidrondin.weather.screens.forecastlist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.ViewMvcFactory;
import com.sergeidrondin.weather.screens.forecastlist.forecastlistitem.ForecastListItemViewMvc;
import com.sergeidrondin.weather.screens.forecastlist.forecastlistitem.ForecastListItemViewMvcImpl;

import java.util.ArrayList;
import java.util.List;

public class ForecastRecyclerAdapter
        extends RecyclerView.Adapter<ForecastRecyclerAdapter.MyViewHolder>
        implements ForecastListItemViewMvcImpl.Listener
{

    public interface OnForecastClickListener {
        void onForecastClicked(DailyForecast forecast);
    }

    @Override
    public void onForecastClicked(DailyForecast forecast) {
        mListener.onForecastClicked(forecast);
    }

    private final ViewMvcFactory mViewMvcFactory;
    private List<DailyForecast> mForecasts;
    private final OnForecastClickListener mListener;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final ForecastListItemViewMvc mViewMvc;

        public MyViewHolder(ForecastListItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }

    }

    public ForecastRecyclerAdapter(OnForecastClickListener listener, ViewMvcFactory viewMvcFactory) {
        mListener = listener;
        mViewMvcFactory = viewMvcFactory;
    }

    public void bindForecasts(List<DailyForecast> forecasts) {
        mForecasts = new ArrayList<>(forecasts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ForecastListItemViewMvc viewMvc = mViewMvcFactory.getForecastListItemViewMvc(parent);
        viewMvc.registerListener(this);
        return new MyViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mViewMvc.bindForecast(mForecasts.get(position));
    }

    @Override
    public int getItemCount() {
        if (mForecasts == null) return 0;
        return mForecasts.size();
    }


}
