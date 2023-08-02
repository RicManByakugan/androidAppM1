package com.example.wm.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.wm.R;
import com.example.wm.controller.post.ControllerPost;
import com.example.wm.model.Post;
import com.example.wm.model.PostAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    Button button;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ControllerPost controllerPost = new ControllerPost();
    private List<Post> postList;
    private PostAdapter postAdapter;
    private ListView listViewPosts;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private void showPosts() {
        controllerPost.GetAllPost(new ControllerPost.GetAllPostCallback() {
            @Override
            public void onPostsReceived(List<Post> postList) {
                // Handle the received list of posts
                postAdapter = new PostAdapter(getActivity(), postList);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Find the ListView inside the inflated view
        listViewPosts = view.findViewById(R.id.listViewPosts);

        // Set an empty adapter initially to avoid a NullPointerException
        postAdapter = new PostAdapter(getActivity(), new ArrayList<>());
        listViewPosts.setAdapter(postAdapter);

        // Load and display the posts
        showPosts();

        return view;
    }
}