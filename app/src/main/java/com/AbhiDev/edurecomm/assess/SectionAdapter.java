package com.AbhiDev.edurecomm.assess;

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

import com.squareup.picasso.Picasso;
import com.wireout.Exams.Activities.ExamInstructionActivity;
import com.wireout.Exams.Activities.ExamSubCategoryListActivity;
import com.wireout.Exams.CategoryModel;
import com.wireout.R;
import com.wireout.adapters.CareerOptionsAdapter;
import com.wireout.adapters.ExamsAdapter;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.models.exams.Section;

import java.util.List;

public class SectionAdapter extends  RecyclerView.Adapter<SectionAdapter.MyViewHolder> {
    Context context;
    private LayoutInflater inflater;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerOptionsAdapter.OnLoadMoreListener loadMoreListener;
    List<Section> careerOptions;
    public SectionAdapter(Context context, List<Section> careerOptions) {
        this.context = context;
        this.careerOptions = careerOptions;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public SectionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.exam_item_layout,parent,false);
        return new SectionAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(SectionAdapter.MyViewHolder holder, final int position) {
        final Section exam = careerOptions.get(position);
        final ExamSubCategory examSubCategory = new ExamSubCategory();
        examSubCategory.setQuestions(exam.getQuestions());
        holder.subject.setText(exam.getTitle());
        if(exam.getIsAttempted()){
            holder.attempted.setBackgroundColor(context.getResources().getColor(R.color.green500));
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ExamInstructionActivity.class);

                i.putExtra("exam",examSubCategory);
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
        return careerOptions.size();
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
