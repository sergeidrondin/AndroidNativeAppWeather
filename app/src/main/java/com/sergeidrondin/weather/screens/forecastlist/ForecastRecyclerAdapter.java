package com.sergeidrondin.weather.screens.forecastlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;
import com.sergeidrondin.weather.screens.forecastlist.forecastlistitem.ForecastListItemViewMvc;
import com.sergeidrondin.weather.screens.forecastlist.forecastlistitem.ForecastListItemViewMvcImpl;

import java.util.ArrayList;
import java.util.List;

public class ForecastRecyclerAdapter
        extends RecyclerView.Adapter<ForecastRecyclerAdapter.MyViewHolder>
        implements ForecastListItemViewMvcImpl.Listener
{

    public interface OnForecastClickListener {
        void onForecastClicked(DailyForecastSchema forecast);
    }

    @Override
    public void onForecastClicked(DailyForecastSchema forecast) {
        mListener.onForecastClicked(forecast);
    }

    private final Context mContext;
    private List<DailyForecastSchema> mForecasts;
    private final OnForecastClickListener mListener;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final ForecastListItemViewMvc mViewMvc;

        public MyViewHolder(ForecastListItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }

    }

    public ForecastRecyclerAdapter(OnForecastClickListener listener, Context context) {
        mListener = listener;
        mContext = context;
    }

    public void bindForecasts(List<DailyForecastSchema> forecasts) {
        mForecasts = new ArrayList<>(forecasts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ForecastListItemViewMvc viewMvc = new ForecastListItemViewMvcImpl(inflater, parent);
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
