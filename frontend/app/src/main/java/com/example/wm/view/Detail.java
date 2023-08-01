package com.example.wm.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wm.R;
import com.example.wm.controller.post.ControllerPost;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class Detail extends AppCompatActivity {

    private ControllerPost controllerPost = new ControllerPost();
    private JSONObject obj;
    private ImageView image;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        getInitPost("64c88bcb700c072f9bcc7327");

    }

    private void initWidget(){
        image = (ImageView) findViewById(R.id.imageUrl);
        textView = (TextView) findViewById(R.id.titlePost);
        try {
            /*Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(obj.getString("image_url")).getContent());
            image.setImageBitmap(bitmap);*/
            textView.setText(obj.getString("title"));
            //Picasso.get().load(obj.getString("image_url")).into(image);
            //Picasso.with(this).load(obj.getString("image_url")).into(image);
        }catch (Exception e){
            Log.d("ERROR","ERROR ");
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
