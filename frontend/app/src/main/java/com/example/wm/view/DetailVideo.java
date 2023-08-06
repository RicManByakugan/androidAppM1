package com.example.wm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
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
    private TextView textViewV;

    private Button downloadBtn;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);

        String postId = getIntent().getStringExtra("postID");
        getInitPost(postId);
        //getInitPost("64cb6e7913c4bbb743894835");
    }
    private void initWidget(){
        videoViewPost = (VideoView) findViewById(R.id.videoView);
        textView = (TextView) findViewById(R.id.titlePost);
        textViewDesc = (TextView) findViewById(R.id.textDescription);
        textViewDateL = (TextView) findViewById(R.id.textDate);
        textViewV = (TextView) findViewById(R.id.vuVideo);

        try {
            textView.setText(obj.getString("title"));
            textViewDesc.setText(obj.getString("Lieu"));
            textViewDateL.setText(obj.getString("datePost"));
            if (obj.getString("visite") == null){
                textViewV.setText("0 VIEW");
            }else{
                textViewV.setText(obj.getString("visite") + " VIEW");
            }
            try {
                // Create a MediaController to enable video controls (play, pause, seek)
                MediaController mediaController = new MediaController(this);
                mediaController.show();
                mediaController.setAnchorView(videoViewPost);
                videoViewPost.setMediaController(mediaController);

                // Set the video URL and start playing
                Uri videoUri = Uri.parse(obj.getString("video_url"));
                videoViewPost.setVideoURI(videoUri);
                videoViewPost.start();

                progressBar = findViewById(R.id.progressBarDetailVideo);
                progressBar.setVisibility(View.GONE);

            }catch (Exception e){
                Log.d("ERROR", "VIDEO ERROR" + e.toString());
            }
        }catch (Exception e){
            Log.d("ERROR","ERROR ");
        }

        downloadBtn = findViewById(R.id.btnDownload);
        downloadBtn.setOnClickListener(view -> {
            try {
                downloadVideo(obj.getString("video_url"));
            }catch (Exception e){
                Log.d("Erreur" , "" + e.toString());
            }
        });
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

    private void downloadVideo(String videoUrl) {
        // Créez une demande de téléchargement à l'aide de DownloadManager
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(videoUrl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Video uploading");
        request.setDescription("Downloading ...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "WM.mp4");

        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        if (downloadManager != null) {
            downloadManager.enqueue(request);
            Toast.makeText(this, "Download video ...", Toast.LENGTH_SHORT).show();
        }
    }
}