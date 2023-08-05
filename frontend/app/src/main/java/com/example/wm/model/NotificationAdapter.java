package com.example.wm.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wm.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Notification> notifications;
    private OnItemClickListener clickListener;

    public NotificationAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);

        holder.textViewMessage.setText(notification.getMessage());
        holder.textViewDate.setText(notification.getDateNotif());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMessage;
        TextView textViewDate;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewDate = itemView.findViewById(R.id.textViewDate);

            // Set click listener for the text view
            textViewMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Notification clickedNotification = notifications.get(position);
                            clickListener.onItemClick(clickedNotification);
                        }
                    }
                }
            });
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    // ViewHolder and other methods as before

    public interface OnItemClickListener {
        void onItemClick(Notification notification);
    }
}
