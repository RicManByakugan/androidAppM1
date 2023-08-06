package com.example.wm.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wm.R;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {


    private List<String> messagesList = new ArrayList<>();
    private MessageAdapter adapter;
    private VideoDetailFragment currentDetailFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add sample messages to the list (replace with your actual message data)
        messagesList.add("Pressable Message 1");
        messagesList.add("Pressable Message 2");
        messagesList.add("Pressable Message 3");

        // Initialize the adapter with the messages list
        adapter = new MessageAdapter(messagesList);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        // Find the RecyclerView
        RecyclerView recyclerViewMessages = rootView.findViewById(R.id.recyclerViewMessages);

        // Set up the RecyclerView with a LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewMessages.setLayoutManager(layoutManager);

        // Set the adapter to the RecyclerView
        recyclerViewMessages.setAdapter(adapter);

        return rootView;
    }

    // ...

    private class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

        private List<String> messages;

        public MessageAdapter(List<String> messages) {
            this.messages = messages;
        }

        @NonNull
        @Override
        public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
            String message = messages.get(position);
            holder.bind(message);
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView textViewMessage;

            public MessageViewHolder(View itemView) {
                super(itemView);
                textViewMessage = itemView.findViewById(R.id.textViewMessage);
                itemView.setOnClickListener(this);
            }

            public void bind(String message) {
                textViewMessage.setText(message);
            }

            @Override
            public void onClick(View v) {
                // Handle click event for the message item here
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    String selectedMessage = messages.get(position);
                    // Check if the MessageDetailFragment already exists in the back stack
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    VideoDetailFragment videoDetailFragment = (VideoDetailFragment) fragmentManager.findFragmentByTag("MessageDetailFragment");

                    if (videoDetailFragment == null) {
                        // If it doesn't exist, add the MessageDetailFragment to the back stack
                        currentDetailFragment = VideoDetailFragment.newInstance(selectedMessage);
                        replaceFragmentWithBackStack(currentDetailFragment, "MessageDetailFragment");
                    } else {
                        // If it exists, show the existing fragment (remove the updateMessage() call)
                        currentDetailFragment = videoDetailFragment;
                        showFragmentWithoutAddingToBackStack(currentDetailFragment);
                    }
                }
            }

            // ...
        }

        // Helper method to handle fragment transactions with back stack
        private void replaceFragmentWithBackStack(Fragment fragment, String tag) {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        }

        // Helper method to show a fragment without adding it to the back stack
        private void showFragmentWithoutAddingToBackStack(Fragment fragment) {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
            fragmentTransaction.commit();
        }
    }
}

