package com.sergeidrondin.weather.networking.onecall;

import com.google.gson.annotations.SerializedName;

public class DailyTemperatureSchema {

    @SerializedName("day")
    private final Float mDay;

    @SerializedName("min")
    private final Float mMin;

    @SerializedName("max")
    private final Float mMax;

    @SerializedName("night")
    private final Float mNight;

    @SerializedName("eve")
    private final Float mEve;

    @SerializedName("morn")
    private final Float mMorn;

    public DailyTemperatureSchema(
            Float day,
            Float min,
            Float max,
            Float night,
            Float eve,
            Float morn
    ) {
        this.mDay = day;
        this.mMin = min;
        this.mMax = max;
        this.mNight = night;
        this.mEve = eve;
        this.mMorn = morn;
    }

    public Float getDay() {
        return mDay;
    }

    public Float getMin() {
        return mMin;
    }

    public Float getMax() {
        return mMax;
    }

    public Float getNight() {
        return mNight;
    }

    public Float getEve() {
        return mEve;
    }

    public Float getMorn() {
        return mMorn;
    }
}
