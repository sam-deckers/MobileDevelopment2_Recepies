package com.sampxl.recipeapp;

import android.provider.BaseColumns;

public class PreferenceContract {

    private PreferenceContract(){}

    public static final class PreferenceEntry implements BaseColumns{
        public static final String TABLE_NAME = "preference";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
