package com.sampxl.recipeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sampxl.recipeapp.PreferenceContract.*;

public class PreferenceDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "preference.db";
    public static final int DATABASE_VERSION = 1;

    public PreferenceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PREFERENCE_TABLE = "CREATE TABLE " +
                PreferenceEntry.TABLE_NAME + " (" +
                PreferenceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PreferenceEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                PreferenceEntry.COLUMN_VALUE + " TEXT NOT NULL, " +
                PreferenceEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_PREFERENCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + PreferenceEntry.TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PreferenceEntry.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
