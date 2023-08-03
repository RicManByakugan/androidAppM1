package com.example.wm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wm.R;

public class VideoDetailFragment extends Fragment {
    private TextView textViewDetailMessage;

    public VideoDetailFragment() {
        // Required empty public constructor
    }

    public static VideoDetailFragment newInstance(String selectedMessage) {
        VideoDetailFragment fragment = new VideoDetailFragment();
        Bundle args = new Bundle();
        args.putString("selected_message", selectedMessage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message_detail, container, false);
        textViewDetailMessage = rootView.findViewById(R.id.textViewMessageDetail);

        // Retrieve the selected message from the fragment arguments
        Bundle args = getArguments();
        if (args != null) {
            String selectedMessage = args.getString("selected_message", "");
            textViewDetailMessage.setText(selectedMessage);
        }

        // Find the FrameLayout to host the ComposeMessageFragment
        FrameLayout frameLayoutCompose = rootView.findViewById(R.id.frameLayoutCompose);

        // Check if the ComposeMessageFragment already exists in the back stack
        FragmentManager fragmentManager = getChildFragmentManager();
        ComposeMessageFragment composeMessageFragment = (ComposeMessageFragment) fragmentManager.findFragmentByTag("ComposeMessageFragment");

        if (composeMessageFragment == null) {
            // If it doesn't exist, add the ComposeMessageFragment to the FrameLayout
            composeMessageFragment = new ComposeMessageFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutCompose, composeMessageFragment, "ComposeMessageFragment");
            fragmentTransaction.commit();
        }

        return rootView;
    }
}
