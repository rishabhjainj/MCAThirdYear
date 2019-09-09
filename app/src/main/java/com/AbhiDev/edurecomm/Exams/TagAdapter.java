package com.AbhiDev.edurecomm.Exams;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wireout.Activities.CareerLandingPage;
import com.wireout.R;
import com.wireout.adapters.CareerAdapter;
import com.wireout.models.CareerList;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.MyViewHolder> {

    List<String> tags;
    Context context;
    private LayoutInflater inflater;

    public TagAdapter(List<String> tags,Context context){
        this.tags= tags;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public TagAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.exam_tag_layout,parent,false);
        return new TagAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(TagAdapter.MyViewHolder holder, final int position) {
        String careerTag = tags.get(position);

        holder.tag.setText(careerTag);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
    @Override
    public int getItemCount() {
        return tags.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tag;
        View parent;

        public MyViewHolder(View itemView) {
            super(itemView);


            tag=itemView.findViewById(R.id.tag_text);
            parent=this.itemView;
        }
    }
}
