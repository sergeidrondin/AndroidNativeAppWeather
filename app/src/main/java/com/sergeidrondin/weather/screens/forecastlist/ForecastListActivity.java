package com.sergeidrondin.weather.screens.forecastlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sergeidrondin.weather.R;
import com.sergeidrondin.weather.common.WeatherApplication;
import com.sergeidrondin.weather.networking.WeatherApi;
import com.sergeidrondin.weather.networking.onecall.CurrentWeatherSchema;
import com.sergeidrondin.weather.networking.onecall.DailyForecastSchema;
import com.sergeidrondin.weather.networking.onecall.OneCallResponseSchema;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastListActivity extends AppCompatActivity {
    private ForecastListViewMvc mViewMvc;

    private final Double DEFAULT_LAT = 49.282729;
    private final Double DEFAULT_LON = -123.120737;

    private WeatherApi mWeatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(this);
        mViewMvc = new ForecastListViewMvc(inflater, null);
        setContentView(mViewMvc.getRootView());

        mWeatherApi = ((WeatherApplication) getApplication()).getCompositionRoot().getWeatherApi();

        fetchOneCallForecast();
    }

    public void fetchOneCallForecast() {
        mViewMvc.showLoading();
        String requestUrl = mWeatherApi.fetchOneCallForecast(DEFAULT_LAT, DEFAULT_LON).request().url().toString();
        Log.i("requestUrl", requestUrl);
        mWeatherApi.fetchOneCallForecast(DEFAULT_LAT, DEFAULT_LON)
                .enqueue(new Callback<OneCallResponseSchema>() {
                    @Override
                    public void onResponse(Call<OneCallResponseSchema> call, Response<OneCallResponseSchema> response) {
                        if (response.isSuccessful()) {
                            notifyOneCallSuccess(response.body());
                        } else {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<OneCallResponseSchema> call, Throwable t) {
                        String message = t.getMessage();
                        Log.d("onFailure", message);
                        notifyFailure();
                    }
                } );
    }

    private void notifyOneCallSuccess(OneCallResponseSchema body) {
        mViewMvc.showForecasts();
        List<DailyForecastSchema> forecasts = body.getDaily();
        mViewMvc.bindForecasts(forecasts);
    }

    private void notifyFailure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Cannot fetch forecast!");
        AlertDialog alert = builder.create();
        alert.show();
    }
}