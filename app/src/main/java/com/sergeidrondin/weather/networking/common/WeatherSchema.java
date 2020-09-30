package com.sergeidrondin.weather.networking.common;

import com.google.gson.annotations.SerializedName;

public class WeatherSchema {

    @SerializedName("id")
    private final Integer mId;

    @SerializedName("main")
    private final String mMain;

    @SerializedName("description")
    private final String mDescription;

    @SerializedName("icon")
    private final String mIcon;

    public WeatherSchema(Integer id, String main, String description, String icon) {
        this.mId = id;
        this.mMain = main;
        this.mDescription = description;
        this.mIcon = icon;
    }

    public Integer getId() {
        return mId;
    }

    public String getMain() {
        return mMain;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getIcon() {
        return mIcon;
    }
}
