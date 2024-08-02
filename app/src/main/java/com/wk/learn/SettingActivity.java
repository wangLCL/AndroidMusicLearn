package com.wk.learn;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import com.wk.ateutils.ATE;
import com.wk.ateutils.ATEActivity;
import com.wk.ateutils.Config;
import com.wk.dialogutils.color.ColorChooserDialog;
import com.wk.learn.fragment.SettingsFragment;
import com.wk.learn.preference.KwColorPreference;

public class SettingActivity extends ATEActivity implements ColorChooserDialog.ColorCallback {

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        final Config config = ATE.config(this);
        switch (dialog.getTitle()) {
            case R.string.primary_color:
                config.primaryColor(selectedColor);
                break;
            case R.string.accent_color:
                config.accentColor(selectedColor);
                break;
            case R.string.primary_text_color:
                config.textColorPrimary(selectedColor);
                break;
            case R.string.secondary_text_color:
                config.textColorSecondary(selectedColor);
                break;
        }
        config.commit();
        recreate(); // recreation needed to reach the checkboxes in the preferences layout
    }

    public static class SettingsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            KwColorPreference primaryColorPref = (KwColorPreference) findPreference("primary_color");
            primaryColorPref.setColor(Config.primaryColor(getActivity()), Color.BLACK);
            primaryColorPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    new ColorChooserDialog.Builder((SettingActivity) getActivity(), R.string.primary_color)
                            .preselect(Config.primaryColor(getActivity()))
                            .show();
                    return true;
                }
            });

            KwColorPreference accentColorPref = (KwColorPreference) findPreference("accent_color");
            accentColorPref.setColor(Config.accentColor(getActivity()), Color.BLACK);
            accentColorPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    new ColorChooserDialog.Builder((SettingActivity) getActivity(), R.string.accent_color)
                            .preselect(Config.accentColor(getActivity()))
                            .show();
                    return true;
                }
            });

            KwColorPreference textColorPrimaryPref = (KwColorPreference) findPreference("text_primary");
            textColorPrimaryPref.setColor(Config.textColorPrimary(getActivity()), Color.BLACK);
            textColorPrimaryPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    new ColorChooserDialog.Builder((SettingActivity) getActivity(), R.string.primary_text_color)
                            .preselect(Config.textColorPrimary(getActivity()))
                            .show();
                    return true;
                }
            });

            KwColorPreference textColorSecondaryPref = (KwColorPreference) findPreference("text_secondary");
            textColorSecondaryPref.setColor(Config.textColorSecondary(getActivity()), Color.BLACK);
            textColorSecondaryPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    new ColorChooserDialog.Builder((SettingActivity) getActivity(), R.string.secondary_text_color)
                            .preselect(Config.textColorSecondary(getActivity()))
                            .show();
                    return true;
                }
            });

            findPreference("colored_status_bar").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    ATE.config(getActivity())
                            .coloredStatusBar((Boolean) newValue)
                            .apply(getActivity());
                    return true;
                }
            });

            findPreference("colored_nav_bar").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    ATE.config(getActivity())
                            .coloredNavigationBar((Boolean) newValue)
                            .apply(getActivity());
                    return true;
                }
            });
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setSupportActionBar((Toolbar) findViewById(R.id.app_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null)
            getFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new SettingsFragment()).commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}