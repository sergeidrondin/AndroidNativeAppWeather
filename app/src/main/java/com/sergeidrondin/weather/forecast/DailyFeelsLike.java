package com.sergeidrondin.weather.forecast;

import java.io.Serializable;

public class DailyFeelsLike implements Serializable {

    private final Float mDay;

    private final Float mNight;

    private final Float mEve;

    private final Float mMorn;

    public DailyFeelsLike(
            Float day,
            Float night,
            Float eve,
            Float morn
    ) {
        this.mDay = day;
        this.mNight = night;
        this.mEve = eve;
        this.mMorn = morn;
    }

    public Float getDay() {
        return mDay;
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
