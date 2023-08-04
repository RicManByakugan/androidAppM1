package com.example.wm.view;

import android.util.Log;
import android.widget.EditText;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.wm.R;
import com.example.wm.controller.post.ControllerPost;
import com.example.wm.model.Post;
import com.example.wm.model.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText searchEditText;

    private ControllerPost controllerPost = new ControllerPost();

    private List<Post> postList;
    private PostAdapter postAdapter;
    private ListView listViewPosts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        listViewPosts= view.findViewById(R.id.listViewId);

        searchEditText = view.findViewById(R.id.editTextSearch);

        Button searchButton = view.findViewById(R.id.buttonSearch);
        searchButton.setOnClickListener(v -> {
            String searchText = searchEditText.getText().toString();
            // Now you can use 'searchText' for your search operation
            performSearch(searchText);
            listViewPosts.setVisibility(View.VISIBLE);
        });

        return view;
    }

    private void performSearch(String cleSearch) {
        controllerPost.searchPost(cleSearch,new ControllerPost.searchPostCallback() {
            @Override
            public void onsearchPostsReceived(List<Post> postList) {
                // Handle the received list of posts
                PostAdapter postAdapter = new PostAdapter(getActivity(), postList);

                // Set the custom adapter on the ListView associated with the ListFragment
                listViewPosts.setAdapter(postAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                // Handle the error case
                Log.d("ALL POST", errorMessage);

                // Pass an empty list to the adapter to trigger the error view
                postAdapter = new PostAdapter(getActivity(), new ArrayList<>());

                // Set the custom adapter on the ListView associated with the ListFragment
                listViewPosts.setAdapter(postAdapter);
            }
        });
    }
}
