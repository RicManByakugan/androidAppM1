package com.example.wm.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wm.R;
import com.example.wm.controller.post.ControllerPost;

public class Detail extends AppCompatActivity {

    private ControllerPost controllerPost = new ControllerPost();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        controllerPost.GetPost("64c88bcb700c072f9bcc7327");
    }
}
