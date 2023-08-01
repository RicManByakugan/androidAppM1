package com.example.wm.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wm.R;
import com.example.wm.controller.post.ControllerPost;

import java.io.InputStream;
import java.net.URL;

public class Detail extends AppCompatActivity {

    private ControllerPost controllerPost = new ControllerPost();
    private String dataPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        /*controllerPost.GetPost("64c88bcb700c072f9bcc7327", new ControllerPost.GetPostCallBack() {
            @Override
            public void onGetPostResult(String data) {
                dataPost = data;
            }
        });
        Log.d("DATAPOST", dataPost);*/
    }

    /*public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }*/
}
