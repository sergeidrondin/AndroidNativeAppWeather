package com.sergeidrondin.weather.networking.onecall;

import com.google.gson.annotations.SerializedName;

public class DailyFeelsLikeSchema {
    @SerializedName("day")
    private final Float mDay;

    @SerializedName("night")
    private final Float mNight;

    @SerializedName("eve")
    private final Float mEve;

    @SerializedName("morn")
    private final Float mMorn;

    public DailyFeelsLikeSchema(
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
