package com.example.wm.view;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wm.R;
import com.example.wm.controller.post.ControllerPost;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Detail extends AppCompatActivity {

    private ControllerPost controllerPost = new ControllerPost();
    private JSONObject obj;
    private ImageView imageViewPost;
    private TextView textView;
    private TextView textViewDesc;
    private TextView textViewDateL;
    private Button downloadButton;

    private Button favoriteButton;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        String postTitle = getIntent().getStringExtra("postID");
        getInitPost(postTitle);
    }


    private void initWidget(){
        imageViewPost = (ImageView) findViewById(R.id.imageUrl);
        textView = (TextView) findViewById(R.id.titlePost);
        textViewDesc = (TextView) findViewById(R.id.textDescription);
        textViewDateL = (TextView) findViewById(R.id.textDate);

        try {
            textView.setText(obj.getString("title"));
            textViewDesc.setText(obj.getString("Lieu") + " | " + obj.getString("datePost"));
            //textViewDateL.setText(obj.getString("datePost"));
            Glide.with(this)
                    .load(obj.getString("image_url"))
                    .placeholder(R.drawable.red_placeholder_image) // Placeholder image while loading (if needed)
                    .error(R.drawable.error) // Image to display in case of error (if needed)
                    .into(imageViewPost);
            progressBar = findViewById(R.id.progressBarDetailImage);
            progressBar.setVisibility(View.GONE);
        }catch (Exception e){
            Log.d("ERROR","ERROR ");
        }

        downloadButton = findViewById(R.id.btnDownload);
        downloadButton.setOnClickListener(view -> {
            try {
                downloadImage(obj.getString("image_url"));
            }catch (Exception e){
                Log.d("Erreur" , "" + e.toString());
            }
        });

        /*favoriteButton = findViewById(R.id.btnFavorite);
        favoriteButton.setOnClickListener(view -> {
           Log.d("USER FAVORITE", "I LOVE THISSSSSSSSSSSSSS PICCCCCCCCCCCCCCCCCCC");
        });*/
    }

    private void downloadImage(String imageUrl) {
        // Créez une demande de téléchargement à l'aide de DownloadManager
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imageUrl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Image uploading");
        request.setDescription("Downloading ...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "WM.jpg");

        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        if (downloadManager != null) {
            downloadManager.enqueue(request);
            Toast.makeText(this, "Download image ...", Toast.LENGTH_SHORT).show();
        }
    }
    private void getInitPost(String id){
        controllerPost.GetPost(id, new ControllerPost.GetPostCallBack() {
            @Override
            public void onGetPostResult(String data) {
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
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }


}
