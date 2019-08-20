package com.sampxl.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OptionsFragment extends Fragment {
    private SQLiteDatabase mDatabase;
    private Button saveButton;
    private Switch switch1;
    private String mValue;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "switch1";

    private boolean switchOnOff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_options, container, false);
        saveButton = view.findViewById(R.id.save_button);
        switch1 = view.findViewById(R.id.switch1);

        PreferenceDBHelper dbHelper = new PreferenceDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        loadData();
        updateViews();

        return view;
    }

    public void saveData(){

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();

        Toast.makeText(getActivity(), "Changes Applied", Toast.LENGTH_SHORT).show();

        ContentValues cv = new ContentValues();
        cv.put(PreferenceContract.PreferenceEntry.COLUMN_NAME, "value");
        cv.put(PreferenceContract.PreferenceEntry.COLUMN_VALUE, String.valueOf(switch1.isChecked()));

        mDatabase.insert(PreferenceContract.PreferenceEntry.TABLE_NAME, null, cv);


    }

    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateViews(){
        switch1.setChecked(switchOnOff);
    }
}
