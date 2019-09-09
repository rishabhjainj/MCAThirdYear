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
import android.widget.Toast;

import com.wireout.Activities.CareerLandingPage;
import com.wireout.R;
import com.wireout.models.CareerOption;

import java.util.List;
import java.util.Random;

public class CareerOptionsAdapter extends  RecyclerView.Adapter<CareerOptionsAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater inflater;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerOptionsAdapter.OnLoadMoreListener loadMoreListener;
    List<CareerOption> careerOptions;

    public CareerOptionsAdapter(Context context, List<CareerOption> careerOptions, DisplayMetrics displayMetrics) {
        this.context = context;
        this.careerOptions = careerOptions;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.sub_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final CareerOption  careerTag = careerOptions.get(position);
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(225);// Generates a random number between 0x10 and 0x20
        int mysec = rand.nextInt(225);
        int mythird = rand.nextInt(225);

        holder.linearLayout.setBackgroundColor(Color.rgb(myRandomNumber, mysec, mythird));
        holder.subject.setText(careerTag.getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CareerLandingPage.class);
                Toast.makeText(context,"id"+careerTag.getId(),Toast.LENGTH_SHORT);
                i.putExtra("id",careerTag.getId()+"");
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
    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }

}
