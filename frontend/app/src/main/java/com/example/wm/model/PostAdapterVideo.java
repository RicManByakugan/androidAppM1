package com.example.wm.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.wm.R;
import com.example.wm.view.DetailVideo;

import org.w3c.dom.Text;

import java.util.List;

public class PostAdapterVideo extends ArrayAdapter<Post> {

    private Context context;
    private List<Post> postList;

    public PostAdapterVideo(Context context, List<Post> postList) {
        super(context, 0, postList);
        this.context = context;
        this.postList = postList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_post_video, parent, false);
        }

        Post currentPost = postList.get(position);

        TextView textViewTitle = itemView.findViewById(R.id.textViewTitle);
        TextView textViewDate = itemView.findViewById(R.id.textViewDate);
        TextView textViewLieu = itemView.findViewById(R.id.textViewLieu);
        TextView textViewV = itemView.findViewById(R.id.textViewVVide);

        textViewTitle.setText(currentPost.getTitle());
        textViewDate.setText(currentPost.getDatePost());
        if (currentPost.getVisite() == null){
            textViewV.setText("0 VIEW");
        }else{
            textViewV.setText(currentPost.getVisite() + " VIEW");
        }
        //textViewLieu.setText(currentPost.getLieu());

        itemView.setOnClickListener(view -> {
            // Create an intent to launch the Detail activity
            Intent intent = new Intent(context, DetailVideo.class);
            // Pass data to the intent as extras
            intent.putExtra("postID", currentPost.get_id());
            // Start the Detail activity
            context.startActivity(intent);
        });

        return itemView;
    }

}
