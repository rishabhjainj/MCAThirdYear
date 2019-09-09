package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wireout.Activities.CareerLandingPage;
import com.wireout.R;
import com.wireout.models.DailyGoals;

import java.util.List;
import java.util.Random;

public class SubjectsAdapter extends  RecyclerView.Adapter<SubjectsAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater inflater;
    List<DailyGoals> careerTags;

    public SubjectsAdapter(Context context, List<DailyGoals> careerTags, DisplayMetrics displayMetrics) {
        this.context = context;
        this.careerTags = careerTags;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.sub_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        DailyGoals  careerTag = careerTags.get(position);
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(225);// Generates a random number between 0x10 and 0x20
        int mysec = rand.nextInt(225);
        int mythird = rand.nextInt(225);

        holder.linearLayout.setBackgroundColor(Color.rgb(myRandomNumber, mysec, mythird));
        holder.subject.setText(careerTag.getTitle());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CareerLandingPage.class));
            }
        });


    }
    @Override
    public int getItemCount() {
        return careerTags.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView subject;
        LinearLayout linearLayout;
        View parent;

        public MyViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            subject=itemView.findViewById(R.id.subject_text);
            parent=this.itemView;
        }
    }
}
