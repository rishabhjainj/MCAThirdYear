package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.wireout.Activities.CategoryLandingPage;
import com.wireout.R;
import com.wireout.models.CategoryList;

import java.util.List;


/**
 * Created by rahul on 27/5/18.
 */

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsAdapter.MyViewHolder> {

    Context context;
    List<CategoryList> categoryList;


    public SchoolsAdapter(Context context, List<CategoryList> categoryList) {
        this.context = context;
        this.categoryList = categoryList;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final CategoryList category = categoryList.get(position);
        Picasso.with(context)
                .load(category.getImage())
                .into(holder.schoolImage);
        Log.d("categoryImage",category.getId()+"");
        holder.schoolName.setText(category.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(context, CategoryLandingPage.class);
               i.putExtra("id",category.getId()+"");
               context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView schoolImage;
        TextView schoolName;
        CardView cardView;
        View parent;
        public MyViewHolder(View itemView) {
            super(itemView);
            schoolImage=itemView.findViewById(R.id.category_image);
            schoolName=itemView.findViewById(R.id.category_text);
            cardView = itemView.findViewById(R.id.cardView);
            parent=this.itemView;
        }
    }

}
