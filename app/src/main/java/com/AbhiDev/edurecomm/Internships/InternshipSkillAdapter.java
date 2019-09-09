package com.AbhiDev.edurecomm.Internships;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wireout.R;
import com.wireout.adapters.CareerOptionsAdapter;
import com.wireout.models.exams.ExamSubCategory;

import java.util.List;

public class InternshipSkillAdapter extends  RecyclerView.Adapter<InternshipSkillAdapter.MyViewHolder>{
    Context context;
    private LayoutInflater inflater;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerOptionsAdapter.OnLoadMoreListener loadMoreListener;
    List<String> skills;
    public InternshipSkillAdapter(Context context, List<String> skills) {
        this.context = context;
        this.skills = skills;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public InternshipSkillAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.internship_skill_item_layout,parent,false);
        return new InternshipSkillAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(InternshipSkillAdapter.MyViewHolder holder, final int position) {
        final String  skill = skills.get(position);
        holder.skillText.setText(skill);



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
