package com.sergeidrondin.weather.forecast;

import java.io.Serializable;

public class WeatherInfo implements Serializable {

    private final Integer mId;

    private final String mMain;

    private final String mDescription;

    private final String mIcon;

    public WeatherInfo(Integer id, String main, String description, String icon) {
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
