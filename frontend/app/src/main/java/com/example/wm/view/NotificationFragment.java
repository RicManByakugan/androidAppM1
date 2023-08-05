package com.example.wm.view;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wm.R;
import com.example.wm.controller.user.ControllerUser;
import com.example.wm.model.Notification;
import com.example.wm.model.NotificationAdapter;
import com.example.wm.model.PostAdapter;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {

    private RecyclerView recyclerViewNotifications;
    private NotificationAdapter notificationAdapter;

    private ControllerUser userControl=new ControllerUser();

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerViewNotifications = view.findViewById(R.id.recyclerViewNotifications);

        // Set up the layout manager and adapter for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewNotifications.setLayoutManager(layoutManager);

        // Create and set the adapter
       showNotifications();

        return view;
    }

    private void showNotifications() {
        userControl.getUserNotif(new ControllerUser.UserNotifCallBack() {
            @Override
            public void onUserNotifResult(List<Notification> notification) {
                notificationAdapter = new NotificationAdapter( notification);

                // Set the custom adapter on the ListView associated with the ListFragment
                recyclerViewNotifications.setAdapter(notificationAdapter);
                notificationAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Notification notification) {
                        Log.d("wtf",notification.getTypePost());
                        if(notification.getTypePost().compareTo("IMAGE") == 0){
                        Intent intent = new Intent(requireContext(), Detail.class);

                        // Pass data to the intent as extras
                        intent.putExtra("postID", notification.getPostID());


                        // Start the Detail activity
                        requireContext().startActivity(intent);
                    }
                        if(notification.getTypePost().compareTo("VIDEO") == 0){
                            Intent intent = new Intent(requireContext(), DetailVideo.class);

                            // Pass data to the intent as extras
                            intent.putExtra("postID", notification.getPostID());


                            // Start the Detail activity
                            requireContext().startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onError(String dataEmpty) {

            }
        });


    }
}