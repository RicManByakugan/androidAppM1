package com.example.wm.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.wm.R;
import com.example.wm.controller.post.ControllerPost;
import com.example.wm.model.Post;
import com.example.wm.model.PostAdapter;
import com.example.wm.model.PostAdapterVideo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListVideoFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListVideoFragement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ControllerPost controllerPost = new ControllerPost();
    private List<Post> postList;
    private PostAdapterVideo postAdapter;
    private ListView listViewPostVideo;
    public ListVideoFragement() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListVideoFragement.
     */
    // TODO: Rename and change types and number of parameters
    public static ListVideoFragement newInstance(String param1, String param2) {
        ListVideoFragement fragment = new ListVideoFragement();
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

    private void showPostVideo(){
        controllerPost.GetAllPostVideo(new ControllerPost.GetAllPostVideoCallback() {
            @Override
            public void onPostsReceived(List<Post> postList) {
                //ArrayAdapter<Post> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.list_item_postVideo, postList);
                postAdapter = new PostAdapterVideo(getActivity(), postList);
                listViewPostVideo.setAdapter(postAdapter);
                ProgressBar progressBar = getView().findViewById(R.id.progressBarVideo);
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String errorMessage) {
                // Handle the error case
                Log.d("ALL POST", errorMessage);

                // Pass an empty list to the adapter to trigger the error view
                postAdapter = new PostAdapterVideo(getActivity(), new ArrayList<>());
                listViewPostVideo.setAdapter(postAdapter);
                ProgressBar progressBar = getView().findViewById(R.id.progressBarVideo);
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_video_fragement, container, false);

        listViewPostVideo = view.findViewById(R.id.listViewPostVideo);
        ProgressBar progressBar = view.findViewById(R.id.progressBarVideo);
        progressBar.setVisibility(View.VISIBLE);

        postAdapter = new PostAdapterVideo(getActivity(), new ArrayList<>());
        listViewPostVideo.setAdapter(postAdapter);

        showPostVideo();

        return view;
    }
}