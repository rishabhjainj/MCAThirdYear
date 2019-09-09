package com.AbhiDev.edurecomm.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.wireout.Activities.CourseLandingPage;
import com.wireout.common.MyApplication;
import com.wireout.R;
import com.wireout.models.RelatedCourse;

import java.util.List;
import java.util.Random;

/**
 * Created by rahul on 20/5/18.
 */

public class RelatedCoursesAdapter extends RecyclerView.Adapter<RelatedCoursesAdapter.MyViewHolder> {

    Context context;
    List<RelatedCourse> courses;

    public RelatedCoursesAdapter(Context context, List<RelatedCourse> courses) {
        this.context = context;
        this.courses = courses;
        Log.d("coursessize",courses.size()+"");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.buy_course_layout,parent,false);
        return new MyViewHolder(view);
    }
    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){}

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Double actualprice;
        Double discountPercent;
        Double discountedPrice;
        holder.offPercent.setVisibility(View.VISIBLE);
        holder.cutRate.setVisibility(View.VISIBLE);
        holder.actualPrice.setVisibility(View.VISIBLE);
        holder.rupee .setVisibility(View.VISIBLE);
        holder.rupee2.setVisibility(View.VISIBLE);

        final RelatedCourse course = courses.get(position);
        if(course.getUserLiked()){
            holder.favorite.setFavorite(true);
        }
        else{
            holder.favorite.setFavorite(false);
        }
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn()) {
                    if (holder.favorite.isFavorite())
                        holder.favorite.setFavorite(false);
                    else
                        holder.favorite.setFavorite(true);

                    OnLiked(course.getId());
                }
                else{
                    Toast.makeText(context,"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();

                }
            }
        });
        if(course.getTagline()!=null)
            holder.courseName.setText(course.getTagline());
        if(course.getNumLikes()!=null){
            holder.numLikes.setText(course.getNumLikes()+"");
        }
        if(course.getIsFree()!=null)
            if(!course.getIsFree()) {
                if (course.getDiscount() != null) {
                    discountPercent = course.getDiscount();
                    discountedPrice = course.getFee();
                    actualprice = (100*discountedPrice)/(100-discountPercent);
                    holder.actualPrice.setText((int)Math.round(course.getFee())+"");
                    holder.offPercent.setText(course.getDiscount() + "% off");
                    holder.cutRate.setText((int)Math.round(actualprice) + "");
                } else {
                    if (course.getFee() != null) {
                        holder.offPercent.setVisibility(View.GONE);
                        holder.cutRate.setVisibility(View.GONE);
                        holder.actualPrice.setText(course.getFee() + "");
                    }
                    else{
                        holder.actualPrice.setText("PAID");
                        holder.offPercent.setVisibility(View.GONE);
                        holder.cutRate.setVisibility(View.GONE);
                        holder.rupee .setVisibility(View.GONE);
                        holder.rupee2.setVisibility(View.GONE);
                    }
                }
            }
            else{
                holder.offPercent.setVisibility(View.GONE);
                holder.cutRate.setVisibility(View.GONE);
                holder.actualPrice.setText("FREE");
                holder.rupee .setVisibility(View.GONE);
                holder.rupee2.setVisibility(View.GONE);
            }
        if(course.getRating()!=null){
            double d = course.getRating();
            int rating = (int)d;
            holder.ratingText.setText(course.getRating()+"");
            // holder.ratingBar.setRating(rating);
        }
        if(course.getDuration()!=null){
            holder.courseDuration.setText(course.getDuration());
        }
        if(course.getCourseType()!=null){
            Log.d("coursetype",course.getCourseType());
            if(course.getCourseType().equals("Online")){
                holder.modeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_online));
            }
            else
            {
                holder.modeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_offline));
            }
            holder.mode.setText(course.getCourseType());
        }
        if(course.getUniversity()!=null){
            holder.university.setText(course.getUniversity().getName());
        }

        if(course.getName()!=null){
            holder.courseShortname.setText(course.getSchool().get(0).getName());
        }
        holder.overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
// Add the buttons
                builder.setTitle(course.getName());
                builder.setMessage(course.getDescription());
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(225);// Generates a random number between 0x10 and 0x20
        int mysec = rand.nextInt(225);
        int mythird = rand.nextInt(225);

        holder.registerButton.setText(course.getAction());
        holder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(course.getWikipediaUrl());
            }
        });
        holder.cardView.setBackgroundColor(Color.rgb(myRandomNumber, mysec, mythird));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CourseLandingPage.class);
                intent.putExtra("id",course.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        //ImageView courseImage;
        TextView courseName;
        View parent;
        TextView cutRate;
        TextView numLikes;
        TextView mode;
        TextView courseDuration;
        TextView ratingText;
        TextView university;
        TextView actualPrice;
        TextView offPercent;
        TextView rupee,rupee2;
        LinearLayout cardView;
        ImageView modeImage;
        TextView courseShortname;
        ImageView overview;
        MaterialFavoriteButton favorite;
        TextView registerButton;
        public MyViewHolder(View itemView) {
            super(itemView);
            //courseImage=itemView.findViewById(R.id.imageSuccess);
            ratingText = itemView.findViewById(R.id.ratingtext);
            courseName=itemView.findViewById(R.id.course_name);
            actualPrice = itemView.findViewById(R.id.actualprice);
            cutRate = itemView.findViewById(R.id.cutrate);
            modeImage= itemView.findViewById(R.id.modeImage);
            offPercent =itemView.findViewById(R.id.offpercentage);
            mode= itemView.findViewById(R.id.mode);
            rupee = itemView.findViewById(R.id.rupee);
            rupee2 = itemView.findViewById(R.id.rupee2);
            overview = itemView.findViewById(R.id.overview);
            numLikes = itemView.findViewById(R.id.num_likes);
            favorite = itemView.findViewById(R.id.likeButton);
            registerButton = itemView.findViewById(R.id.registerButton);
            courseDuration = itemView.findViewById(R.id.duration);
            university = itemView.findViewById(R.id.university);
            courseShortname= itemView.findViewById(R.id.courseShortName);
            cardView = (LinearLayout) itemView.findViewById(R.id.linearLayout);
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
