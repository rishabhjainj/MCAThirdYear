package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.Activities.AllCoursesActivity;
import com.wireout.Activities.CareerLandingPage;
import com.wireout.Activities.CategoryBasedFilters;
import com.wireout.models.Career;
import com.wireout.models.Category;
import com.wireout.models.CategoryList;
import com.wireout.models.SchoolList;
import com.wireout.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFiltersAdapter extends  RecyclerView.Adapter<CategoryFiltersAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater inflater;
    List<CategoryList> categories;
    ArrayList<String> checkedItems;

    public CategoryFiltersAdapter(Context context, List<CategoryList> categories) {
        this.context = context;
        this.categories = categories;
        checkedItems = new ArrayList<String>();
        checkedItems.addAll(((CategoryBasedFilters)context).categoryIds);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public CategoryFiltersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.filters_category_item,parent,false);
        return new CategoryFiltersAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final CategoryFiltersAdapter.MyViewHolder holder, final int position) {
        final CategoryList category = categories.get(position);
        holder.textView.setText(category.getName());
        Picasso.with(context).load(category.getImage()).into(holder.categoryImage);
        for(String item : checkedItems){
            if(item.equals(holder.textView.getText()+"")){
                holder.checkBox.setChecked(true);
                Log.d("filtersactivity","comparemach:"+item+","+holder.textView.getText().toString());
            }


        }
        //holder.checkBox.setChecked(ageGroup.isChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked())
                {
                    ( (CategoryBasedFilters)context).categoryIds.add(category.getName());
                    //holder.checkBox.setChecked(false);
                }
                else
                {
                    //  holder.checkBox.setChecked(true);
                   int index = ((CategoryBasedFilters) context).categoryIds.indexOf(category.getName());
                   ((CategoryBasedFilters) context).categoryIds.remove(index);
                }
            }
        });

        holder.controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked())
                {
                       holder.checkBox.setChecked(false);
                    int index = ((CategoryBasedFilters) context).categoryIds.indexOf(category.getName());
                    ((CategoryBasedFilters) context).categoryIds.remove(index);
                }

                else
                {
                        holder.checkBox.setChecked(true);
                    ((CategoryBasedFilters) context).categoryIds.add(category.getName());
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public CheckBox checkBox;
        public ImageView categoryImage;
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout controller;

        public MyViewHolder(View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.category_image);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.label);
            controller = (RelativeLayout) itemView.findViewById(R.id.controller);
        }
    }

}
