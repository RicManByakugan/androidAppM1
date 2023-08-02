package com.example.wm.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wm.R;
import com.example.wm.view.Detail;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {
    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        super(context, 0, postList);
        this.context = context;
        this.postList = postList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_post, parent, false);
        }

        Post currentPost = postList.get(position);

        ImageView imageViewPost = itemView.findViewById(R.id.imageViewPost);
        TextView textViewTitle = itemView.findViewById(R.id.textViewTitle);
        TextView textViewDate = itemView.findViewById(R.id.textViewDate);
        TextView textViewLieu = itemView.findViewById(R.id.textViewLieu);

        textViewTitle.setText(currentPost.getTitle());
        textViewDate.setText(currentPost.getDatePost());
        textViewLieu.setText(currentPost.getLieu());

        // Load the image using Picasso or other image-loading libraries
        Glide.with(this.getContext())
                .load(currentPost.getimage_url())
                .placeholder(R.drawable.red_placeholder_image) // Placeholder image while loading (if needed)
                .error(R.drawable.error) // Image to display in case of error (if needed)
                .into(imageViewPost);

        itemView.setOnClickListener(view -> {
            // Create an intent to launch the Detail activity
            Intent intent = new Intent(context, Detail.class);

            // Pass data to the intent as extras
            intent.putExtra("postID", currentPost.get_id());


            // Start the Detail activity
            context.startActivity(intent);
        });

        return itemView;
    }
}
