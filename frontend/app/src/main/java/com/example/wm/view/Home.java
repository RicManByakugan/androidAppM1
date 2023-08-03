package com.example.wm.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.wm.R;

import com.example.wm.controller.post.ControllerPost;

import org.json.JSONObject;

public class Home extends AppCompatActivity {
    private boolean isSearchActive = false;
    private boolean isSettingsActive = false;
    private Class<? extends Fragment> lastFragmentClass = ListFragment.class;
    private ControllerPost controllerPost = new ControllerPost();

    private JSONObject userJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        Intent intent = getIntent();
        if (intent != null) {
            String userData = intent.getStringExtra("user");
            Log.d("User *****************************************************************", "" + userData);
            try {
                userJson = new JSONObject(userData);
            } catch (Throwable t) {
                Log.e("USER DATA ERROR", "Could not parse malformed JSON: \"" + json + "\"");
            }
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

                // Set the search active flag to true
                isSearchActive = true;

                // Hide the other layouts (btnList, btnPreference, btnNotification, fragmentContainerView)
                findViewById(R.id.btnImage).setVisibility(View.GONE);
                findViewById(R.id.btnVideo).setVisibility(View.GONE);
                findViewById(R.id.btnNotification).setVisibility(View.GONE);
                findViewById(R.id.fragmentContainerView).setVisibility(View.GONE);
                findViewById(R.id.action_settings).setVisibility(View.GONE);
                invalidateOptionsMenu();
                FragmentManager fragmentManager = getSupportFragmentManager();
                lastFragmentClass = fragmentManager.getFragments().size() > 0
                        ? fragmentManager.getFragments().get(0).getClass()
                        : null;
                return true;

                // Show the main layout and the Toolbar layout
            case R.id.action_settings:
                // Handle the settings button click here

                // Replace the fragmentContainerView with the SettingsFragment
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                lastFragmentClass = fragmentManager1.getFragments().size() > 0
                        ? fragmentManager1.getFragments().get(0).getClass()
                        : null;
                fragmentManager1.beginTransaction().replace(R.id.fragmentContainerView, new SettingsFragment()).addToBackStack(null).commit();
                findViewById(R.id.btnImage).setVisibility(View.GONE);
                findViewById(R.id.btnVideo).setVisibility(View.GONE);
                findViewById(R.id.btnNotification).setVisibility(View.GONE);
                findViewById(R.id.action_search).setVisibility(View.GONE);
                isSettingsActive=true;
                invalidateOptionsMenu();
                // Set the search active flag to false

                return true;

            // Add other menu item handling if needed
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Check if the search is currently active

        if (isSearchActive) {
            // Show the other layouts (btnList, btnPreference, btnNotification, fragmentContainerView)
            findViewById(R.id.btnImage).setVisibility(View.VISIBLE);
            findViewById(R.id.btnVideo).setVisibility(View.VISIBLE);
            findViewById(R.id.btnNotification).setVisibility(View.VISIBLE);
            findViewById(R.id.fragmentContainerView).setVisibility(View.VISIBLE);

            // Show the search icon
            // Trigger onCreateOptionsMenu to update the toolbar menu
            invalidateOptionsMenu();

            // Set the search active flag to false
            isSearchActive = false;
        } else {
            // Check if the SettingsFragment is currently displayed
            FragmentManager fragmentManager = getSupportFragmentManager();
            findViewById(R.id.btnImage).setVisibility(View.VISIBLE);
            findViewById(R.id.btnVideo).setVisibility(View.VISIBLE);
            findViewById(R.id.btnNotification).setVisibility(View.VISIBLE);
            findViewById(R.id.fragmentContainerView).setVisibility(View.VISIBLE);
            isSettingsActive=false;
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
