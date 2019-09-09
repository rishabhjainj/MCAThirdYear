package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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
import com.wireout.models.Mentor;

import java.util.List;

/**
 * Created by Rishabh on 2/21/2018.
 */

public class AmbassdorsRecyclerViewAdapter extends RecyclerView.Adapter<AmbassdorsRecyclerViewAdapter.RecyclerViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<Mentor> ambassdorsDatas;
    DisplayMetrics displayMetrics;
    public AmbassdorsRecyclerViewAdapter(Context context, List<Mentor> ambassdorsDatas, DisplayMetrics displayMetrics){

        this.context = context;
        this.ambassdorsDatas = ambassdorsDatas;
        this.displayMetrics=displayMetrics;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public AmbassdorsRecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mentor_homepage_layout,parent,false);
        AmbassdorsRecyclerViewAdapter.RecyclerViewHolder recyclerViewHolder=new AmbassdorsRecyclerViewAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){

    }
    @Override
    public void onBindViewHolder(final AmbassdorsRecyclerViewAdapter.RecyclerViewHolder holder, int position) {

//
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
    }

    @Override
    public int getItemCount() {
        return ambassdorsDatas.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        View parent;
        TextView name;
        TextView location;
        TextView university;
        TextView ambassdorName;
        MaterialFavoriteButton favorite;
        ImageView linkedIn;
        ImageView email;
        ImageView share;
        ImageView mentorImage;
        ImageView call;


        public RecyclerViewHolder(View view){
            super(view);
            linkedIn = view.findViewById(R.id.linkedin);
            mentorImage = view.findViewById(R.id.mentorImage);
            location = view.findViewById(R.id.txt3);
            university = view.findViewById(R.id.txt2);
            favorite = view.findViewById(R.id.likeButton);
            name = view.findViewById(R.id.ambassdorName);
            email = view.findViewById(R.id.email);
            share = view.findViewById(R.id.share);
            call = view.findViewById(R.id.cal_btn);
            this.parent = view;
        }
    }
}
