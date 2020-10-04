package com.sergeidrondin.weather.screens.forecastlist.forecastlistitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;
import com.sergeidrondin.weather.screens.common.BaseViewMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastListItemViewMvcImpl extends BaseViewMvc implements ForecastListItemViewMvc {

    private final List<Listener> mListeners = new ArrayList<>(1);
    private DailyForecastSchema mDailyForecastSchema;
    private TextView mTxtTitle;

    public ForecastListItemViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_forecast_list_item, parent, false));

        mTxtTitle = findViewById(R.id.txt_title);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : mListeners) {
                    listener.onForecastClicked(mDailyForecastSchema);
                }
            }
        });
    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    @Override
    public void bindForecast(DailyForecastSchema dailyForecastSchema) {
        mDailyForecastSchema = dailyForecastSchema;

        Long unixDateTime = dailyForecastSchema.getDt();

        Date date = new java.util.Date(unixDateTime*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm z");
        // TODO set timezone according to the city
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-7"));
        String formattedDate = sdf.format(date);

        mTxtTitle.setText(formattedDate + " " + dailyForecastSchema.getTemperature().getDay());
    }
}
