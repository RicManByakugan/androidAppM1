package com.example.wm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Home extends AppCompatActivity {
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

}
