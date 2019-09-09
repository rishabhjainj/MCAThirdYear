package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.common.PrefManager;
import com.wireout.models.ShortTermCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rahul on 6/3/18.
 */

public class ViewMoreCarrierAdapter extends RecyclerView.Adapter<ViewMoreCarrierAdapter.MyViewHolder> {

    private PrefManager prefManager;
    private Context context;
    private LayoutInflater inflater;
    List<ShortTermCourse> urls = new ArrayList<>();

    public ViewMoreCarrierAdapter(PrefManager prefManager, Context context, List<ShortTermCourse> urls) {
        this.prefManager = prefManager;
        this.context = context;
        this.urls = urls;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.viewmore_carrier_adapter_layout,parent,false);
        return new MyViewHolder(view);
    }
    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){}

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ShortTermCourse shortTermCoursesData = urls.get(position);

        Picasso.with(context)
                .load(shortTermCoursesData.getImage())
                .fit()
                .into(holder.courseImage);
        holder.favorite.setFavorite(shortTermCoursesData.getUserLiked());
        Random r = new Random();
        //holder.views.setText((r.nextInt(1000-400) + 400)+" views");
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn()) {
                    if (holder.favorite.isFavorite())
                        holder.favorite.setFavorite(false);
                    else
                        holder.favorite.setFavorite(true);

                    OnLiked(shortTermCoursesData.getId());
                }
                else{
                    Toast.makeText(context,"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.rating.setText(shortTermCoursesData.getRating()+"");
        holder.type.setText(shortTermCoursesData.getBoostPercent()+"% Boost");
        holder.duration.setText(shortTermCoursesData.getDuration()+" Months");
        holder.offer.setText(shortTermCoursesData.getOfferredBy());
        holder.courseName.setText(shortTermCoursesData.getName());

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        View parent;
//        TextView streaks;
        // ImageView soldout;

        TextView duration;
        ImageView courseImage;
        TextView type;
        TextView rating;
        TextView courseName;
        TextView views;
        MaterialFavoriteButton favorite;
        TextView offer;
        public MyViewHolder(View itemView) {
            super(itemView);

            views = itemView.findViewById(R.id.views);
            favorite = itemView.findViewById(R.id.likeButton);
            rating = itemView.findViewById(R.id.ratingtext);
            type = itemView.findViewById(R.id.type);
            duration = itemView.findViewById(R.id.tv_KM_tab4);
            courseName = itemView.findViewById(R.id.textView);
            courseImage = itemView.findViewById(R.id.imageView);
            offer = itemView.findViewById(R.id.vendor);
            this.parent = itemView;


        }
    }

}
