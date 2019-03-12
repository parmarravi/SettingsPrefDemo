package com.example.ravi.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.prefs.PreferenceChangeListener;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_screen, rootKey);

        EditTextPreference editTextPreference = this.findPreference("edit_text_key");
        editTextPreference.setOnBindEditTextListener(editText -> {
            //HIDING KEYBoard issue when canceled dialog
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setFilters(new InputFilter[]{new InputFilterMinMax("1", "3600")});

        });
    }

    @Override
    public void onResume() {
        super.onResume();

        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        Preference preference = findPreference("edit_text_key");
        EditTextPreference editTextPreference = (EditTextPreference) preference;
        editTextPreference.setSummary(editTextPreference.getText());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d("TAG", "onSharedPreferenceChanged: " + s);
        if (s.equals("edit_text_key")) {
            Preference preference = findPreference("edit_text_key");
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            Log.d("TAG", "onSharedPreferenceChanged: " +  editTextPreference.getText());
            editTextPreference.setSummary(editTextPreference.getText());
        }

    }
}
