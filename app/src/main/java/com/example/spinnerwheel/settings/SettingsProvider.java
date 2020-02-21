package com.example.spinnerwheel.settings;

import java.util.ArrayList;
import java.util.List;

public class SettingsProvider {

    private static final int SETTINGS_COUNT = 6;
    private static final String DEFAULT_OPTION = "Option #%s";

    public static List<Settings> getSettings() {
        List<Settings> list = new ArrayList<>();
        for (int i = 0; i < SETTINGS_COUNT; i++) {
            Settings settings = new Settings(i, String.format(DEFAULT_OPTION, i + 1));
            list.add(settings);
        }
        return list;
    }
}
