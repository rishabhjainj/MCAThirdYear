package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.Activities.AllCoursesActivity;
import com.wireout.Activities.ExploreTagSearchActivity;
import com.wireout.R;
import com.wireout.models.Tag;

import java.util.List;

/**
 * Created by Rishabh on 2/22/2018.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.RecyclerViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Tag> tags;
    DisplayMetrics displayMetrics;
    public ExploreAdapter(Context context,List<Tag> tags,DisplayMetrics displayMetrics){
        this.context = context;
        this.displayMetrics = displayMetrics;
        this.tags=tags;
        inflater = LayoutInflater.from(context);


    }


    @Override
    public ExploreAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.explore_card_layout,parent,false);
        ExploreAdapter.RecyclerViewHolder recyclerViewHolder=new ExploreAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(ExploreAdapter.RecyclerViewHolder holder, int position) {
        final Tag tag = tags.get(position);
        final String url = tag.getImage();


        Double height = displayMetrics.heightPixels/(3.0);
        holder.relativeLayout.getLayoutParams().height = height.intValue();
        holder.relativeLayout.getLayoutParams().width = displayMetrics.widthPixels;

        Picasso.with(context)
                .load(url)
                .fit()
                .into(holder.collegeImage);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(context, AllCoursesActivity.class);
                i.putExtra("landing","explore");
                i.putExtra("search",tag.getName());
                context.startActivity(i);
            }
        });
        holder.textView_tags.setText(tag.getName());
        holder.textView_views.setText(tag.getViews()+"");
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{


        //        TextView discount;
//        TextView discountText;
        View parent;
//        TextView streaks;
        // ImageView soldout;
        TextView textView_tags, textView_views;

        CardView cardView;
        ImageView collegeImage;
        RelativeLayout relativeLayout;

        public RecyclerViewHolder(View view){
            super(view);

            relativeLayout = (RelativeLayout)view.findViewById(R.id.relative_layout);
            textView_tags=(TextView)view.findViewById(R.id.tv_tags_explore);
            textView_views=(TextView)view.findViewById(R.id.tv_views_explore);
            cardView  =(CardView)view.findViewById(R.id.card_view);
            collegeImage = view.findViewById(R.id.imageView);
            this.parent = view;

        }
    }
}
