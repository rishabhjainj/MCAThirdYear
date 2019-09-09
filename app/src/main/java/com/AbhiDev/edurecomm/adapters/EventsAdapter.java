package com.AbhiDev.edurecomm.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.Activities.Analysis.AnalysisStatusActivity;
import com.wireout.Activities.Recommendations;
import com.wireout.common.MyApplication;
import com.wireout.events.Tab4_Events_Adapter;
import com.wireout.models.Event;
import com.wireout.R;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {
    Context context;
    List<Event> eventList;
    String date,month,mon,endDate,endMonth,endMon;
    public EventsAdapter(Context context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public EventsAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.tab4_events_layout,viewGroup,false);
        return new EventsAdapter.MyViewHolder(view);
    }

    String getMonth(String month){
        String mon = "";
        switch (month){
            case "01" :
                mon = "Jan";
                break;
            case "02" :
                mon = "Feb";
                break;
            case "03" :
                mon = "Mar";
                break;
            case "04" :
                mon = "Apr";
                break;
            case "05" :
                mon = "May";
                break;
            case "06" :
                mon = "Jun";
                break;
            case "07" :
                mon = "Jul";
                break;
            case "08" :
                mon = "Aug";
                break;
            case "09" :
                mon = "Sep";
                break;
            case "10" :
                mon = "Oct";
                break;
            case "11" :
                mon = "Nov";
                break;
            case "12" :
                mon = "Dec";
                break;

        }
        return  mon;
    }
    @Override
    public void onBindViewHolder(EventsAdapter.MyViewHolder holder, int position) {
        final Event event = eventList.get(position);
        holder.endDate.setVisibility(View.VISIBLE);
        holder.textView_title.setText(event.getName());
        date = event.getStartTime().substring(8,10);
        month = event.getStartTime().substring(5,7);
        mon = getMonth(month);
        endDate = event.getEndTime().substring(8,10);
        endMonth = event.getEndTime().substring(5,7);
        endMon = getMonth(endMonth);
        if(date.equals(endDate)&&mon.equals(endMon)){
            holder.startDate.setText(mon+" "+date);
            holder.endDate.setVisibility(View.GONE);
        }
        else{
            holder.startDate.setText(mon+" "+date);
            holder.endDate.setText(" - "+ endMon+" "+endDate);
        }
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
// Add the buttons
                builder.setTitle(event.getName()+"");
                builder.setMessage(event.getDescription()+" ");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+event.getContact()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.textView_label.setText(event.getLabel());
        holder.textView_timeAM.setText(event.getStartTime().substring(11,13)+" Hrs");
        holder.textView_time_PM.setText(event.getEndTime().substring(11,13)+" Hrs");
        //holder.textView_orgName.setText(event.getDescription());
        holder.textView_addr.setText(event.getShortLocation()+"");

//        holder.relativeLayout_parent.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.sample_new));
        Picasso.with(context)
                .load(event.getImagePath())
                .fit()
                .into(holder.imageView);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        CardView cardView;
        TextView textView_title, textView_date, textView_timeAM, textView_time_PM, startDate,endDate;
        TextView textView_label, textView_orgName, textView_addr, textView_dist;
        RelativeLayout relativeLayout_parent;
        ImageView call;
        ImageView info;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.eventDesc);
            startDate=(TextView)itemView.findViewById(R.id.tv_months_tab4);
            call = itemView.findViewById(R.id.imgBtn_call_tab4);
            imageView=(ImageView)itemView.findViewById(R.id.img_events_tab4);
            cardView=(CardView)itemView.findViewById(R.id.card_tab4);
            textView_title=(TextView)itemView.findViewById(R.id.tv_eventName_tab4);
            endDate = itemView.findViewById(R.id.to_date);
            textView_timeAM=(TextView)itemView.findViewById(R.id.tv_timeAM_tab4);
            textView_time_PM=(TextView)itemView.findViewById(R.id.tv_timePM_tab4);
            textView_label=(TextView)itemView.findViewById(R.id.tv_label_tab4);
            textView_orgName=(TextView)itemView.findViewById(R.id.tv_orgName_tab4);
            textView_addr=(TextView)itemView.findViewById(R.id.tv_addr_tab4);
            textView_dist=(TextView)itemView.findViewById(R.id.tv_dist_tab4);
            relativeLayout_parent=(RelativeLayout)itemView.findViewById(R.id.rel3);


        }
    }
}
