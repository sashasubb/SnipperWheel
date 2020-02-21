package com.example.spinnerwheel.settings;

import android.graphics.Color;

import androidx.annotation.ColorRes;

import com.example.spinnerwheel.R;

public class Settings {
    private int id;
    @ColorRes private int color;
    private String option;

    public Settings(int id, String option) {
        this.id = id;
        this.color = getColorById();
        this.option = option;
    }

    private int getColorById() {
        switch (id) {
            case 0: return R.color.option_1;
            case 1: return R.color.option_2;
            case 2: return R.color.option_3;
            case 3: return R.color.option_4;
            case 4: return R.color.option_5;
            default: return R.color.option_6;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
