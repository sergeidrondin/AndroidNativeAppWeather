package com.sergeidrondin.weather.screens.forecastlist.forecastlistitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastListItemViewMvc {

    private View mRootView;

    private DailyForecastSchema mDailyForecastSchema;

    private TextView mTxtTitle;

    public void setRootView(View rootView) {
        this.mRootView = rootView;
    }

    public View getRootView() {
        return mRootView;
    }

    protected <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    public ForecastListItemViewMvc(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_forecast_list_item, parent, false));

        mTxtTitle = findViewById(R.id.txt_title);
//        getRootView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (Listener listener : getListeners()) {
//                    listener.onQuestionClicked(mQuestion);
//                }
//            }
//        });
    }

    public void bindDailyForecast(DailyForecastSchema dailyForecastSchema) {
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
