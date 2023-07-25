package com.example.wm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Home extends AppCompatActivity {
    private boolean isSearchActive = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnList=findViewById(R.id.btnList);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager= getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,ListFragment.class,null).setReorderingAllowed(true).addToBackStack("name").commit();
            }
        });

        Button btnPreference=findViewById(R.id.btnPreference);
        btnPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager= getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, PreferenceFragment.class,null).setReorderingAllowed(true).addToBackStack("name").commit();
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
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Handle search icon click here

                // Set the search active flag to true
                isSearchActive = true;

                // Hide the other layouts (btnList, btnPreference, btnNotification, fragmentContainerView)
                findViewById(R.id.btnList).setVisibility(View.GONE);
                findViewById(R.id.btnPreference).setVisibility(View.GONE);
                findViewById(R.id.btnNotification).setVisibility(View.GONE);
                findViewById(R.id.fragmentContainerView).setVisibility(View.GONE);

                // Show the main layout and the Toolbar layout
                findViewById(R.id.toolbar).setVisibility(View.VISIBLE);

                return true;
            // Add other menu item handling if needed
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Check if the search is currently active
        if (isSearchActive) {
            // Show the other layouts (btnList, btnPreference, btnNotification, fragmentContainerView)
            findViewById(R.id.btnList).setVisibility(View.VISIBLE);
            findViewById(R.id.btnPreference).setVisibility(View.VISIBLE);
            findViewById(R.id.btnNotification).setVisibility(View.VISIBLE);
            findViewById(R.id.fragmentContainerView).setVisibility(View.VISIBLE);

            // Hide the Toolbar layout
            findViewById(R.id.toolbar).setVisibility(View.GONE);

            // Set the search active flag to false
            isSearchActive = false;
        } else {
            // Perform the default back button action
            super.onBackPressed();
        }
    }

}
