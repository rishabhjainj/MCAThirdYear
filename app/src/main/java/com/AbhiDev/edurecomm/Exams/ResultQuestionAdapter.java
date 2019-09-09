package com.AbhiDev.edurecomm.Exams;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wireout.Activities.CareerLandingPage;
import com.wireout.Exams.Activities.ExamInstructionActivity;
import com.wireout.Exams.Activities.ExplanationActivity;
import com.wireout.R;
import com.wireout.adapters.CareerOptionsAdapter;
import com.wireout.adapters.ExamListAdapter;
import com.wireout.models.CareerOption;
import com.wireout.models.exams.Question;

import java.util.List;

import io.github.kexanie.library.MathView;

public class ResultQuestionAdapter extends  RecyclerView.Adapter<ResultQuestionAdapter.MyViewHolder>  {

    List<Question> questions;
    Context context;
    List<QuestionResponse> answers;
    boolean isLoading = false, isMoreDataAvailable = true;
    private LayoutInflater inflater;
    private ResultQuestionAdapter.OnLoadMoreListener loadMoreListener;

    public ResultQuestionAdapter(Context context, List<Question> questions,List<QuestionResponse> answers) {
        this.context = context;
        this.answers = answers;
        this.questions = questions;
        Log.d("noofquestions",questions.size()+"");
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public ResultQuestionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.exam_result_item,parent,false);
        return new ResultQuestionAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ResultQuestionAdapter.MyViewHolder holder, final int position) {
        final Question quest = questions.get(position);

            final QuestionResponse answer = answers.get(position);
            holder.question.setText(quest.getQuestionText());
            holder.questNo.setText("Q" + (position + 1) + ".");
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ExplanationActivity.class);
                    quest.setSerialNo(position + 1);
                    i.putExtra("question", quest);
                    i.putExtra("answer", answer);
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
    public interface OnLoadMoreListener{
        void onLoadMore();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView questNo;
        MathView question;
        View parent;

        public MyViewHolder(View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            questNo=itemView.findViewById(R.id.questno);
            parent=this.itemView;
        }
    }
}
