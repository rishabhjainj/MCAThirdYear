package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.Exams.Activities.ExamSubCategoryListActivity;
import com.wireout.Exams.CategoryModel;
import com.wireout.Exams.ExamOptionModel;
import com.wireout.R;

import java.util.List;
import java.util.Random;

public class ExamsAdapter extends  RecyclerView.Adapter<ExamsAdapter.MyViewHolder> {

    Context context;
    private LayoutInflater inflater;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerOptionsAdapter.OnLoadMoreListener loadMoreListener;
    List<CategoryModel> careerOptions;
    public ExamsAdapter(Context context, List<CategoryModel> careerOptions) {
        this.context = context;
        this.careerOptions = careerOptions;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ExamsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.exam_option_layout,parent,false);
        return new ExamsAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ExamsAdapter.MyViewHolder holder, final int position) {
        final CategoryModel  careerTag = careerOptions.get(position);
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(225);// Generates a random number between 0x10 and 0x20
        int mysec = rand.nextInt(225);
        int mythird = rand.nextInt(225);


        Picasso.with(context).load(careerTag.getImage()).into(holder.examImage);
        Picasso.with(context).load(careerTag.getIcon()).into(holder.examIcon);
        holder.subject.setText(careerTag.getTitle());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ExamSubCategoryListActivity.class);
                i.putExtra("category",careerTag.getTitle());
                context.startActivity(i);
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
        return careerOptions.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView subject;
        View parent;
        ImageView examIcon;
        ImageView examImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            examIcon = itemView.findViewById(R.id.exam_icon);
            subject=itemView.findViewById(R.id.subject_text);
            examImage = itemView.findViewById(R.id.exam_image);
            parent=this.itemView;
        }
    }
}
