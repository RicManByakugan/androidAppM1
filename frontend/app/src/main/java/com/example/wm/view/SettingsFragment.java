package com.example.wm.view;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;


import com.example.wm.R;
import com.example.wm.controller.user.ControllerUser;

public class SettingsFragment extends PreferenceFragmentCompat {

    private ControllerUser controllerUser = new ControllerUser();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey);

        // Add any preference change listeners if needed
        ListPreference themePreference = findPreference("theme");
        if (themePreference != null) {
            themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    // Handle theme preference changes here
                    String selectedTheme = newValue.toString();
                    setAppTheme(selectedTheme); // Call the method to set the app theme
                    return true;
                }
            });
        }

        // Other preference setup



        Preference disconnectButton = findPreference("disconnect_button");
        if (disconnectButton != null) {
            disconnectButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    showDisconnectConfirmationDialog();
                    return true;
                }
            });
        }
    }
    private void showDisconnectConfirmationDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Disconnect")
                .setMessage("Are you sure you want to disconnect?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    // Handle disconnect action here
                    // For example, call your logout method
                    controllerUser.userLogout(new ControllerUser.UserLogoutCallback() {
                        @Override
                        public void onUserLogoutResult(boolean isLoggedout) {
                            if (isLoggedout) {
                                Intent intent = new Intent(getActivity(), Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), "Logout failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
    private void setAppTheme(String theme) {
        int newTheme;
        switch (theme) {
            case "light":
                newTheme = R.style.AppTheme_Light;
                break;
            case "dark":
                newTheme = R.style.AppTheme_Dark;
                break;
            default:
                newTheme = R.style.AppTheme; // Default theme
                break;
        }

        getActivity().setTheme(newTheme);
    }
}