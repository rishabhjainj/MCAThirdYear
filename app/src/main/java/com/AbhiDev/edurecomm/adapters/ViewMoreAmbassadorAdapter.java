package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.models.Mentor;

import java.util.List;

/**
 * Created by rahul on 6/3/18.
 */

public class ViewMoreAmbassadorAdapter extends RecyclerView.Adapter<ViewMoreAmbassadorAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Mentor> ambassdorsDatas;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerBlogAdapter.OnLoadMoreListener loadMoreListener;

    public ViewMoreAmbassadorAdapter(Context context, List<Mentor> ambassdorsDatas) {
        this.context = context;
        this.ambassdorsDatas = ambassdorsDatas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){}

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.mentor_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Mentor ambassdorsData = ambassdorsDatas.get(position);

        holder.name.setText(ambassdorsData.getFirst_name()+" "+ambassdorsData.getLast_name());
//        Log.d("ambass",ambassdorsData.getFirstName());
        //holder.baca.setText(ambassdorsData.getCampaignerType());
        holder.location.setText(ambassdorsData.getSchools().get(0).getName());
        holder.university.setText(ambassdorsData.getInstitution());
        holder.linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ambassdorsData.getLinkedInProfile()));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
            }
        });
        try {
            holder.email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String[] addresses = new String[1];
                    addresses[0] = ambassdorsData.getEmail();
                    Intent intent = new Intent(Intent.ACTION_SEND, Uri.fromParts(
                            "mailto", ambassdorsData.getEmail(), null));
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                    Intent mailer = Intent.createChooser(intent, "Send email:");
                    mailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (mailer.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(mailer);
                    } else {
                        Toast.makeText(context, "No app found on your phone which can perform this action", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        catch (Exception e){

        }
        if(ambassdorsData.getUserLiked()){
            holder.favorite.setFavorite(true);
            holder.favorite.setColorFilter(ContextCompat.getColor(context, R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        else{
            holder.favorite.setFavorite(false);
            holder.favorite.setColorFilter(ContextCompat.getColor(context, R.color.mettalic_grey), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ambassdorsData.getContact()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
//        if(position>=6){
//            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.square_button_pink_back));
//        }
//        else{
//            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.square_button_background));
//        }

        try {
            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Checkout This Career Counsellor");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, ambassdorsData.getLinkedInProfile());
                    sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (sharingIntent.resolveActivity(context.getPackageManager()) != null) {
                        Intent chooserIntent = Intent.createChooser(sharingIntent, "Share Using");
                        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(chooserIntent);

                    } else {
                        Toast.makeText(context, "No app found on your phone which can perform this action", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        catch (Exception e){

        }
        Picasso.with(context).load(ambassdorsData.getImage()).error(R.drawable.ic_man).into(holder.mentorImage);
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
                        holder.favorite.setColorFilter(ContextCompat.getColor(context, R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }

                    OnLiked(ambassdorsData.getId());
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
        return ambassdorsDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        View parent;
        TextView baca;
        ImageView mentorImage;
        TextView name;
        TextView location;
        TextView university;
        TextView ambassdorName;
        MaterialFavoriteButton favorite;
        ImageView linkedIn;
        ImageView email;
        ImageView share;
        ImageView call;
        LinearLayout linearLayout;


        public MyViewHolder(View view) {
            super(view);

            linearLayout = view.findViewById(R.id.linearLayout);
            linkedIn = view.findViewById(R.id.linkedin);
            mentorImage = view.findViewById(R.id.mentorImage);
            location = view.findViewById(R.id.txt3);
            university = view.findViewById(R.id.txt2);
            favorite = view.findViewById(R.id.likeButton);
            name = view.findViewById(R.id.ambassdorName);
           // baca = view.findViewById(R.id.baca);
            email = view.findViewById(R.id.email);
            share = view.findViewById(R.id.share);
            call = view.findViewById(R.id.cal_btn);
            this.parent = view;

        }
    }
    public interface OnLoadMoreListener{
        void onLoadMore();
    }
    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }
    public void setLoadMoreListener(CareerBlogAdapter.OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }

}
