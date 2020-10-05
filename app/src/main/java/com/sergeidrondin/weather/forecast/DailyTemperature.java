package com.sergeidrondin.weather.forecast;

import java.io.Serializable;

public class DailyTemperature implements Serializable {

    private final Float mDay;

    private final Float mMin;

    private final Float mMax;

    private final Float mNight;

    private final Float mEve;

    private final Float mMorn;

    public DailyTemperature(
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
