package com.sergeidrondin.weather.screens.forecastdetails;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.forecast.DailyTemperature;
import com.sergeidrondin.weather.screens.common.ViewMvcFactory;
import com.sergeidrondin.weather.screens.common.toolbar.ToolbarViewMvc;
import com.sergeidrondin.weather.screens.common.views.BaseObservableViewMvc;

public class ForecastDetailsViewMvcImpl extends BaseObservableViewMvc<ForecastDetailsViewMvc.Listener>
        implements ForecastDetailsViewMvc {

    private final TextView mMorningTextView;
    private final TextView mDayTextView;
    private final TextView mEveningTextView;
    private final TextView mNightTextView;

    private final Toolbar mToolbar;
    private final ToolbarViewMvc mToolbarViewMvc;


    public ForecastDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_forecast_details, parent, false));
        mMorningTextView = findViewById(R.id.forecast_detail_tv_morning);
        mDayTextView = findViewById(R.id.forecast_detail_tv_day);
        mEveningTextView = findViewById(R.id.forecast_detail_tv_evening);
        mNightTextView = findViewById(R.id.forecast_detail_tv_night);

        mToolbar = findViewById(R.id.toolbar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);
        mToolbar.addView(mToolbarViewMvc.getRootView());
        mToolbarViewMvc.enableUpButtonAndListen(new ToolbarViewMvc.NavigateUpClickListener() {
            @Override
            public void onNavigateUpClicked() {
                for (Listener listener: getListeners()) {
                    listener.onNavigateUpClicked();
                }
            }
        });
    }

    @Override
    public void renderForecastDetails(DailyForecast dailyForecast) {
        DailyTemperature dt = dailyForecast.getTemperature();

        String morningTemperature = String.valueOf(dt.getDay());
        mMorningTextView.setText(morningTemperature);

        String dayTemperature = String.valueOf(dt.getMorn());
        mDayTextView.setText(dayTemperature);

        String eveningTemperature = String.valueOf(dt.getEve());
        mEveningTextView.setText(eveningTemperature);

        String nightTemperature = String.valueOf(dt.getNight());
        mNightTextView.setText(nightTemperature);

        mToolbarViewMvc.setTitle(getString(R.string.forecast_details_title) + " " + dailyForecast.getFormattedDate());
    }
}
