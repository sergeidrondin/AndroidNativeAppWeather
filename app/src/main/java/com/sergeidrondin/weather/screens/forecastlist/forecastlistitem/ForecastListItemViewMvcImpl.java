package com.sergeidrondin.weather.screens.forecastlist.forecastlistitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.BaseObservableViewMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastListItemViewMvcImpl extends BaseObservableViewMvc<ForecastListItemViewMvc.Listener> implements ForecastListItemViewMvc {

    private DailyForecast mDailyForecast;
    private TextView mTxtTitle;

    public ForecastListItemViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_forecast_list_item, parent, false));

        mTxtTitle = findViewById(R.id.txt_title);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : getListeners()) {
                    listener.onForecastClicked(mDailyForecast);
                }
            }
        });
    }

    @Override
    public void bindForecast(DailyForecast dailyForecast) {
        mDailyForecast = dailyForecast;

        Long unixDateTime = dailyForecast.getDt();

        Date date = new java.util.Date(unixDateTime*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm z");
        // TODO set timezone according to the city
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-7"));
        String formattedDate = sdf.format(date);

        mTxtTitle.setText(formattedDate + " " + dailyForecast.getTemperature().getDay());
    }
}
