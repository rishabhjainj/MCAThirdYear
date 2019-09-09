package com.AbhiDev.edurecomm.Internships;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.wireout.Exams.Activities.ExamSubCategoryListActivity;
import com.wireout.Exams.CategoryModel;
import com.wireout.R;
import com.wireout.adapters.CareerOptionsAdapter;
import com.wireout.common.MyApplication;
import com.wireout.models.Internships;
import com.wireout.models.exams.ExamSubCategory;


import java.util.List;

public class InternshipsAdapter extends  RecyclerView.Adapter<InternshipsAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater inflater;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerOptionsAdapter.OnLoadMoreListener loadMoreListener;
    List<Internships> internships;
    public InternshipsAdapter(Context context, List<Internships> careerOptions) {
        this.context = context;
        this.internships = careerOptions;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public InternshipsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.rewards_item_layout,parent,false);
        return new InternshipsAdapter.MyViewHolder(view);
    }
    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){}
    @Override
    public void onBindViewHolder(final InternshipsAdapter.MyViewHolder holder, final int position) {
        final Internships  internship = internships.get(position);
        Picasso.with(context).load(internship.getImage()).into(holder.image);
        holder.organization.setText(internship.getRole());
        Log.d("liked",internship.getUserLiked()+"");
        holder.company.setText(internship.getOrganization());
        holder.location.setText(internship.getLocation());
        holder.applyDate.setText("Apply by "+internship.getApplyBy());
        if(internship.getPaid())
        holder.details.setText(internship.getType()+" | "+"Paid | "+"Starts "+internship.getStartDate());
        else
            holder.details.setText(internship.getType()+" | "+"Unpaid | "+"Starts "+internship.getStartDate());
        if(!internship.getFeatured())
         holder.tag.setVisibility(View.GONE);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, RewardsNInternship.class);
                i.putExtra("id",internship.getId()+"");
                //Log.d("id",internship.getId()+"");
                context.startActivity(i);
            }
        });
        if(internship.getUserLiked()){
            holder.favorite.setFavorite(true);
            holder.favorite.setColorFilter(ContextCompat.getColor(context, R.color.hueblue), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        else{
            holder.favorite.setFavorite(false);
            holder.favorite.setColorFilter(ContextCompat.getColor(context, R.color.mettalic_grey), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn()) {
                    if (holder.favorite.isFavorite()) {
                        holder.favorite.setFavorite(false);
                        holder.favorite.setColorFilter(ContextCompat.getColor(context, R.color.mettalic_grey), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }
                    else {
                        holder.favorite.setFavorite(true);
                        holder.favorite.setColorFilter(ContextCompat.getColor(context, R.color.hueblue), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }

                    OnLiked(1);
                }
                else{
                    Toast.makeText(context,"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();

                }
            }
        });
        if (position >= getItemCount() - 15 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            isLoading = true;
            loadMoreListener.onLoadMore();
            Log.d("careeroptionadapter", "loadMore called");
        }


    }
    @Override
    public int getItemCount() {
        return internships.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        View parent;
        TextView organization,company,location,tag,details,applyDate;
        MaterialFavoriteButton favorite;

        public MyViewHolder(View itemView) {
            super(itemView);
            parent = this.itemView;
            image= itemView.findViewById(R.id.image);
            organization=itemView.findViewById(R.id.post);
            company = itemView.findViewById(R.id.company);
            location = itemView.findViewById(R.id.location);
            favorite = itemView.findViewById(R.id.likeButton);
            details = itemView.findViewById(R.id.details);
            applyDate =itemView.findViewById(R.id.apply_date);
            tag = itemView.findViewById(R.id.tag);

        }
    }

}
