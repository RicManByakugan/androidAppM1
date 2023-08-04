package com.example.wm.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.wm.R;

import com.example.wm.controller.post.ControllerPost;
import com.example.wm.controller.user.ControllerUser;
import com.example.wm.model.Post;
import com.example.wm.model.PostAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private boolean isSearchActive = false;
    private boolean isSettingsActive = false;
    private Class<? extends Fragment> lastFragmentClass = ListFragment.class;

    private JSONObject userJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

    }
    private void setAppTheme() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedTheme = sharedPreferences.getString("theme", "light");
        switch (selectedTheme) {
            case "light":
                setTheme(R.style.AppTheme_Light);
                break;
            case "dark":
                setTheme(R.style.AppTheme_Dark);
                break;
            default:
                setTheme(R.style.AppTheme); // Default theme
                break;
        }
    }
    public void init(){
        Button btnList=findViewById(R.id.btnImage);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager= getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,ListFragment.class,null).setReorderingAllowed(true).addToBackStack("name").commit();
            }
        });

        Button btnPreference=findViewById(R.id.btnVideo);
        btnPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager= getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ListVideoFragement.class,null).setReorderingAllowed(true).addToBackStack("name").commit();
            }
        });

        Button btnNotification=findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager= getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, NotificationFragment.class,null).setReorderingAllowed(true).addToBackStack("name").commit();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Check if the current fragment is the SettingsFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainerView);
        boolean isSettingsFragment = currentFragment instanceof SettingsFragment;

        // Hide the settings icon if the current fragment is the SettingsFragment or if the search is active
        MenuItem settingsItem = menu.findItem(R.id.action_settings);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        if(isSearchActive||isSettingsActive){
            settingsItem.setVisible(false);
            searchItem.setVisible(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Handle search icon click here

                // Show the SearchFragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, new SearchFragment())
                        .addToBackStack(null) // Optionally add to back stack
                        .commit();

                // Hide other layouts and UI elements
                hideUIElementsForSearch();

                return true;

            case R.id.action_settings:
                // Handle the settings button click here

                // Show the SettingsFragment
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(R.id.fragmentContainerView, new SettingsFragment())
                        .addToBackStack(null) // Optionally add to back stack
                        .commit();

                // Hide other layouts and UI elements
                hideUIElementsForSettings();

                return true;

            // Add other menu item handling if needed

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hideUIElementsForSearch() {
        findViewById(R.id.btnImage).setVisibility(View.GONE);
        findViewById(R.id.btnVideo).setVisibility(View.GONE);
        findViewById(R.id.btnNotification).setVisibility(View.GONE);
        findViewById(R.id.action_settings).setVisibility(View.GONE);

        // Update any other UI elements that need to be hidden
        // ...
    }

    private void hideUIElementsForSettings() {
        findViewById(R.id.btnImage).setVisibility(View.GONE);
        findViewById(R.id.btnVideo).setVisibility(View.GONE);
        findViewById(R.id.btnNotification).setVisibility(View.GONE);
        findViewById(R.id.action_search).setVisibility(View.GONE);

        // Update any other UI elements that need to be hidden
        // ...
    }
    private void showUIElementsForSearch() {
        findViewById(R.id.btnImage).setVisibility(View.VISIBLE);
        findViewById(R.id.btnVideo).setVisibility(View.VISIBLE);
        findViewById(R.id.btnNotification).setVisibility(View.VISIBLE);
        findViewById(R.id.fragmentContainerView).setVisibility(View.VISIBLE);
    }
    private void showUIElementsForSettings() {
        findViewById(R.id.btnImage).setVisibility(View.VISIBLE);
        findViewById(R.id.btnVideo).setVisibility(View.VISIBLE);
        findViewById(R.id.btnNotification).setVisibility(View.VISIBLE);
        findViewById(R.id.fragmentContainerView).setVisibility(View.VISIBLE);
    }

    public void onBackPressed() {
        // Check if the search is currently active

        if (isSearchActive) {
            // Show the other layouts (btnList, btnPreference, btnNotification, fragmentContainerView)

            showUIElementsForSearch();
            // Show the search icon
            // Trigger onCreateOptionsMenu to update the toolbar menu
            invalidateOptionsMenu();

            // Set the search active flag to false
            isSearchActive = false;
        } else {
            // Check if the SettingsFragment is currently displayed
            FragmentManager fragmentManager = getSupportFragmentManager();
            isSettingsActive=false;
            showUIElementsForSettings();
            invalidateOptionsMenu();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainerView);
            if (currentFragment instanceof SettingsFragment) {
                // If the current fragment is the SettingsFragment, pop it from the back stack
                fragmentManager.popBackStack();
                return;
            }

            // If there are fragments in the back stack, navigate back to the previous fragment
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            } else {
                // If there is no fragment in the back stack, show the last fragment that was visible before entering search mode
                if (lastFragmentClass != null) {
                    try {
                        isSettingsActive=false;
                        invalidateOptionsMenu();
                        Fragment fragmentToShow = lastFragmentClass.newInstance();
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragmentToShow).commit();
                    } catch (IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                } else {
                    // If there is no last fragment and search is not active, perform the default back button action
                    isSettingsActive=false;
                    isSearchActive = false;
                    invalidateOptionsMenu();
                    super.onBackPressed();
                }
            }
        }
    }

}
