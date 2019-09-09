package com.AbhiDev.edurecomm.Exams;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wireout.Activities.ExplanationActivityText;
import com.wireout.Exams.Activities.ExplanationActivity;
import com.wireout.R;
import com.wireout.models.exams.Question;

import java.util.List;

import io.github.kexanie.library.MathView;

public class AnswerStatusAdapter extends  RecyclerView.Adapter<AnswerStatusAdapter.MyViewHolder>  {

    List<Question> questions;
    Context context;
    List<QuestionResponse> answers;
    private LayoutInflater inflater;

    public AnswerStatusAdapter(Context context, List<Question> questions,List<QuestionResponse> answers) {
        this.context = context;
        this.answers = answers;
        this.questions = questions;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public AnswerStatusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.ques_status_item_layout,parent,false);
        return new AnswerStatusAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AnswerStatusAdapter.MyViewHolder holder, final int position) {
        final Question quest = questions.get(position);
        holder.button.setText(position+1+"");
        final QuestionResponse answer = answers.get(position);
        Log.d("answer"+position,answers.get(position).getAnswer()+"");
        if(!answers.get(position).getAnswer().equals("na")) {
            if (quest.getCorrectAnswer().equals(answers.get(position).getAnswer())) {
                holder.button.setBackground(context.getResources().getDrawable(R.drawable.round_btn_green));
            }
            else{
                holder.button.setBackground(context.getResources().getDrawable(R.drawable.round_bg_red));
            }
        }
        else{
            holder.button.setBackground(context.getResources().getDrawable(R.drawable.round_btn_blue));
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quest.setSerialNo(position + 1);
                if(quest.getIsMathematical()){
                    Intent i = new Intent(context, ExplanationActivity.class);
                    i.putExtra("math","true");

                    i.putExtra("question", quest);
                    i.putExtra("answer", answer);
                    context.startActivity(i);
                }
                else{
                    Intent i = new Intent(context, ExplanationActivityText.class);
                    i.putExtra("math","true");

                    i.putExtra("question", quest);
                    i.putExtra("answer", answer);
                    context.startActivity(i);
                }
            }
        });
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        Button button;
        View parent;

        public MyViewHolder(View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.ques_status);
            parent=this.itemView;
        }
    }
}
