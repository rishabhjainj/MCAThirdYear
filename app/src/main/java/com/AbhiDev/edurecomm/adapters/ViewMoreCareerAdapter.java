package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wireout.Activities.CareerLandingPage;
import com.wireout.models.CareerList;
import com.wireout.R;
import com.wireout.models.Career;

import java.util.List;
import java.util.Random;

public class ViewMoreCareerAdapter extends  RecyclerView.Adapter<ViewMoreCareerAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater inflater;
    List<CareerList> career;
    boolean isLoading = false, isMoreDataAvailable = true;
    private ViewMoreCareerAdapter.OnLoadMoreListener loadMoreListener;

    public ViewMoreCareerAdapter(Context context, List<CareerList> career, DisplayMetrics displayMetrics) {
        this.context = context;
        this.career = career;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public void onBindViewHolder(ViewMoreCareerAdapter.MyViewHolder holder, int position) {
        final CareerList  careerTag = career.get(position);
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(225);// Generates a random number between 0x10 and 0x20
        int mysec = rand.nextInt(225);
        int mythird = rand.nextInt(225);

        String hex = String.format("#%02x%02x%02x", myRandomNumber, mysec, mythird);

        hex = hex.substring(0, 1) + "8C" + hex.substring(1, hex.length());
        holder.linearLayout.setBackgroundColor(Color.parseColor(hex));
        holder.subject.setText(careerTag.getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CareerLandingPage.class);
                i.putExtra("id",careerTag.getId()+"");
                Log.d("careerid",careerTag.getId()+"");
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
    public ViewMoreCareerAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.career_options_item_layout,viewGroup,false);
        return new ViewMoreCareerAdapter.MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return career.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView subject;
        LinearLayout linearLayout;
        View parent;

        public MyViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            subject=itemView.findViewById(R.id.subject_text);
            parent=this.itemView;
        }
    }
    public interface OnLoadMoreListener{
        void onLoadMore();
    }
    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }
    public void setLoadMoreListener(ViewMoreCareerAdapter.OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }
}
