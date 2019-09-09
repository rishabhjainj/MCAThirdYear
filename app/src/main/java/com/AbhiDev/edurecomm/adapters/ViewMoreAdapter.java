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
import com.wireout.models.University;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rahul on 6/3/18.
 */

public class ViewMoreAdapter extends RecyclerView.Adapter<ViewMoreAdapter.MyViewHolder> {

    private PrefManager prefManager;
    private Context context;
    Random r;
    private LayoutInflater inflater;
    List<University> universityDatas = new ArrayList<>();

    public ViewMoreAdapter(PrefManager prefManager, Context context, List<University> universityDatas) {
        this.prefManager = prefManager;
        this.context = context;
        this.universityDatas = universityDatas;

        r = new Random();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id) {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.institution_item_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final University universityData = universityDatas.get(position);

//        Picasso.with(context)
//                .load(universityData.getFeatureImage())
//                .fit()
//                .into(holder.collegeImage);
//        Picasso.with(context)
//                .load(universityData.getLogo())
//                .fit()
//                .into(holder.icon);
//
//        holder.collegeName.setText(universityData.getName());
//        Log.d("imageurl",universityData.getFeatureImage()+"");
//        holder.location.setText((universityData.getLocation().getCity()+","+universityData.getLocation().getState().substring(0)));
//        holder.call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:"+universityData.getContact()));
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            }
//        });
//        holder.enroll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(universityData.getWebsiteUrl()));
//                context.startActivity(browserIntent);
//            }
//        });
//            holder.shareImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                sharingIntent.setType("text/plain");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Checkout This University");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, universityData.getWebsiteUrl());
//                sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//
//
//                    if (sharingIntent.resolveActivity(context.getPackageManager()) != null) {
//                        Intent chooserIntent = Intent.createChooser(sharingIntent, "Share Using");
//                        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(chooserIntent);
//
//                    } else {
//                        Toast.makeText(context, "No app found on your phone which can perform this action", Toast.LENGTH_SHORT).show();
//                    }
//
//
//
//            }
//        });
//        holder.favorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (MyApplication.getInstance().prefManager.isLoggedIn()) {
//                    if (holder.favorite.isFavorite())
//                        holder.favorite.setFavorite(false);
//                    else
//                        holder.favorite.setFavorite(true);
//
//                    OnLiked(universityData.getId());
//                }
//                else
//                    Toast.makeText(context,"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();
//
//            }
//        });
        University institution = universityDatas.get(position);
        if(institution.getImages()!=null)
            Picasso.with(context)
                    .load(institution.getFeatureImage())
                    .into(holder.imageView);
        //Log.d("categoryImage",instituteImages.get(position)+"");
        holder.instituteNameTextView.setText(institution.getName());

    }

    @Override
    public int getItemCount() {
        return universityDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        View parent;
//        TextView collegeName;
//        TextView location;
//        ImageView icon;
//        ImageView collegeImage;
//        MaterialFavoriteButton favorite;
//        ImageView call;
//        ImageView shareImage;
//        LinearLayout enroll, linearLayout;
ImageView imageView;
        TextView instituteNameTextView;

        public MyViewHolder(View view) {
            super(view);

            imageView=itemView.findViewById(R.id.institution_image);
            instituteNameTextView=itemView.findViewById(R.id.institution_name);
            parent=this.itemView;
            this.parent = view;

        }
    }

}
