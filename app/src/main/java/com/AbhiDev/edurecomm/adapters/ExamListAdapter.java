package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wireout.Activities.CareerLandingPage;
import com.wireout.Exams.Activities.ExamInstructionActivity;
import com.wireout.R;
import com.wireout.models.CareerOption;
import com.wireout.models.exams.ExamSubCategory;

import java.util.List;

public class ExamListAdapter extends   RecyclerView.Adapter<ExamListAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater inflater;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerOptionsAdapter.OnLoadMoreListener loadMoreListener;
    List<ExamSubCategory> examSubCategoryList;

    public ExamListAdapter(Context context, List<ExamSubCategory> examSubCategoryList) {
        this.context = context;
        this.examSubCategoryList = examSubCategoryList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ExamListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.exam_item_layout,parent,false);
        return new ExamListAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ExamListAdapter.MyViewHolder holder, final int position) {
        final ExamSubCategory  exam = examSubCategoryList.get(position);
        holder.subject.setText(exam.getCategory().getTitle());
        if(exam.getIsAttempted()){
            holder.attempted.setBackgroundColor(context.getResources().getColor(R.color.green500));
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ExamInstructionActivity.class);
                i.putExtra("exam",exam);
                context.startActivity(i);
            }
        });
        holder.time.setText(exam.getTime()+" Minutes");
        holder.noOfQues.setText(exam.getQuestions().size()+" Questions");
        if (position >= getItemCount() - 15 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            isLoading = true;
            loadMoreListener.onLoadMore();
            Log.d("careeroptionadapter", "loadMore called");
        }


    }
    @Override
    public int getItemCount() {
        return examSubCategoryList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView subject;
        LinearLayout linearLayout;
        TextView time;
        TextView skills;
        TextView noOfQues;
        ImageView attempted;
        View parent;

        public MyViewHolder(View itemView) {
            super(itemView);

            attempted = itemView.findViewById(R.id.attempted_image);
            skills = itemView.findViewById(R.id.skills);
            time = itemView.findViewById(R.id.time);
            noOfQues = itemView.findViewById(R.id.questions);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            subject=itemView.findViewById(R.id.subject_text);
            parent=this.itemView;
        }
    }
}
