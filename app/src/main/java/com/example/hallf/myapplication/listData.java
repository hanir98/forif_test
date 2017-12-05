package com.example.hallf.myapplication;

import android.graphics.drawable.Drawable;

/**
 * Created by hallf on 2017-11-18.
 */

public class listData {
    private String city;
    private String status;
    private Drawable set;

    public void setCity(String city) {
        this.city = city;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSet(Drawable set) {
        this.set = set;
    }

    public String getCity() {
        return city;
    }

    public String getStatus() {
        return status;
    }

    public Drawable getSet() {
        return set;
    }
}
