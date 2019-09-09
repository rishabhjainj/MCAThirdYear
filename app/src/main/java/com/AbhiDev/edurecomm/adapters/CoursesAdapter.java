package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.R;
import com.wireout.models.Course;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder>{

    Context context;
    List<Course> courses;

    public CoursesAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
        Log.d("coursessize",courses.size()+"");
    }
    @Override
    public CoursesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.courses_full_item_layout,parent,false);
        return new CoursesAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CoursesAdapter.MyViewHolder holder, final int position) {
        final Course course = courses.get(position);
        Picasso.with(context)
                .load(course.getImage())
                .fit()
                .into(holder.courseImage);
        holder.courseName.setText(course.getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(course.getWikipediaUrl());
            }
        });
    }
    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView courseImage;
        TextView courseName;
        View parent;
        public MyViewHolder(View itemView) {
            super(itemView);
            courseImage=itemView.findViewById(R.id.imageSuccess);
            courseName=itemView.findViewById(R.id.tvSuccess);
            parent=this.itemView;
        }
    }
    public void openBrowser(String url)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
