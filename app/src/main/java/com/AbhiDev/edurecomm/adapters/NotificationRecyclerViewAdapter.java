package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.R;
import com.wireout.common.PrefManager;
import com.wireout.models.Notification;

import java.util.List;

/**
 * Created by rahul on 4/3/18.
 */

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.MyViewHolder> {

    PrefManager prefManager;
    Context context;
    LayoutInflater inflater;
    List<Notification> notifications;

    public NotificationRecyclerViewAdapter(Context context,List<Notification> notifications) {
        this.prefManager = prefManager;
        this.context = context;
        this.notifications=notifications;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.notification_adapter_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Notification notification=notifications.get(position);

        Picasso.with(context)
                .load(notification.getImage())
                .fit()
                .into(holder.imageView);
        holder.title.setText(notification.getActionUri());
        holder.text.setText(notification.getText());
////        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.nav_notif));
//
//        holder.textView_college.setText(notificationData.getCollege());
//        holder.textView_message.setText(notificationData.getMessage());
//        holder.textView_timeNumeric.setText(notificationData.getTime_numeric());
//        holder.textView_timeText.setText(notificationData.getTime_text());
//        holder.textView_timeAgo.setText(notificationData.getTime_ago());
//        holder.textView_apply.setText(notificationData.getApply_button_text());
//        holder.cardView_apply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"Applied at "+holder.textView_college.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
//        TextView textView_college, textView_message, textView_timeNumeric, textView_timeText;
//        TextView textView_timeAgo, textView_apply;
//        CardView cardView_apply;
//        ImageView imageView;
        public  TextView title;
        public  TextView text;
        public ImageView imageView;
        public View parent;

        public MyViewHolder(View view) {
            super(view);

//            textView_college=(TextView)itemView.findViewById(R.id.tv_noti_adapter_college);
//            textView_message=(TextView)itemView.findViewById(R.id.tv_noti_adapter_message);
//            textView_timeNumeric=(TextView)itemView.findViewById(R.id.tv_noti_adapter_timeNumeric);
//            textView_timeText=(TextView)itemView.findViewById(R.id.tv_noti_adapter_timeText);
//            textView_timeAgo=(TextView)itemView.findViewById(R.id.tv_noti_adapter_timeAgo);
//            textView_apply=(TextView)itemView.findViewById(R.id.tv_noti_adapter_apply);
//            cardView_apply=(CardView)itemView.findViewById(R.id.card_notif_apply);
//            imageView=(ImageView)itemView.findViewById(R.id.img_view_noti_adapter);
            this.title = (TextView)view.findViewById(R.id.title);
            this.text = (TextView)view.findViewById(R.id.text);
            this.imageView = (ImageView) view
                    .findViewById(R.id.offer_img);
            this.parent=view;
        }
    }

}
