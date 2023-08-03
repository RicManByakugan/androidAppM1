package com.example.wm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.wm.R;
import com.example.wm.controller.post.ControllerPost;

import org.json.JSONObject;

public class DetailVideo extends AppCompatActivity {

    private ControllerPost controllerPost = new ControllerPost();
    private JSONObject obj;
    private VideoView videoViewPost;
    private TextView textView;
    private TextView textViewDesc;
    private TextView textViewDateL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);

        String postId = getIntent().getStringExtra("postID");
        getInitPost(postId);
    }

    private void initWidget(){
        videoViewPost = (VideoView) findViewById(R.id.videoView);
        textView = (TextView) findViewById(R.id.titlePost);
        textViewDesc = (TextView) findViewById(R.id.textDescription);
        textViewDateL = (TextView) findViewById(R.id.textDate);

        try {
            textView.setText(obj.getString("title"));
            textViewDesc.setText(obj.getString("datePost") + " | " + obj.getString("Lieu"));

            try {

                // Set the video URL and start playing
                Uri videoUri = Uri.parse(obj.getString("video_url"));
                videoViewPost.setVideoURI(videoUri);
                videoViewPost.start();

                // Create a MediaController to enable video controls (play, pause, seek)
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoViewPost);
                videoViewPost.setMediaController(mediaController);
            }catch (Exception e){
                Log.d("ERROR", "VIDEO ERROR" + e.toString());
            }
        }catch (Exception e){
            Log.d("ERROR","ERROR ");
        }

    }

    private void getInitPost(String id){
        controllerPost.GetPostVideo(id, new ControllerPost.GetPostVideoCallBack() {
            @Override
            public void onGetPostVideoResult(String data) {
                try {
                    obj = new JSONObject(data);
                    Log.d("My App", obj.toString());
                    initWidget();
                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + data + "\"");
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("My App", "The error is:"+errorMessage);
            }
        });
    }

}