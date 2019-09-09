package com.AbhiDev.edurecomm.Internships;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wireout.R;
import com.wireout.adapters.CareerOptionsAdapter;

import java.util.List;

public class SimilarInternshipAdapter extends  RecyclerView.Adapter<SimilarInternshipAdapter.MyViewHolder>{
    Context context;
    private LayoutInflater inflater;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerOptionsAdapter.OnLoadMoreListener loadMoreListener;
    List<String> skills;
    public SimilarInternshipAdapter(Context context, List<String> skills) {
        this.context = context;
        this.skills = skills;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public SimilarInternshipAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.similar_internship_item_layout,parent,false);
        return new SimilarInternshipAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(SimilarInternshipAdapter.MyViewHolder holder, final int position) {
        final String  skill = skills.get(position);
        //holder.skillText.setText(skill);



    }
    @Override
    public int getItemCount() {
        return skills.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView skillText;
        View parent;


        public MyViewHolder(View itemView) {
            super(itemView);
            skillText=itemView.findViewById(R.id.skilltext);

            parent = this.itemView;


        }
    }

}