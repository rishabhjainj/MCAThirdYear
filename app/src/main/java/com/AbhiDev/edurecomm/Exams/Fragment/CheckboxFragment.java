package com.AbhiDev.edurecomm.Exams.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wireout.Exams.ExamQuestionModel;
import com.wireout.R;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.models.exams.Question;

import io.github.kexanie.library.MathView;

public class CheckboxFragment extends Fragment {
    private static final String DESCRIBABLE_KEY = "model_key";
    private Question model;

    CheckBox c1,c2,c3,c4;
    TextView question;
    CheckboxFragment.OnDataPass dataPasser;
    //String quest = "When \\(a \\ne 0\\), there are two solutions to \\(ax^2 + bx + c = 0\\) and they are $$x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}.$$";

    String answer="";
    public CheckboxFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkbox_type_questions, container, false);
        question = view.findViewById(R.id.quetxtview);
        model =   (Question) getArguments().getSerializable(DESCRIBABLE_KEY);
        question.setText(model.getQuestionText());
        c1=view.findViewById(R.id.checkBox1);
        c2=view.findViewById(R.id.checkBox2);
        c3=view.findViewById(R.id.checkBox3);
        c4=view.findViewById(R.id.checkBox4);
        c1.setText(model.getMultiChoiceAnswers().get(0).getAnswerText());
        c2.setText(model.getMultiChoiceAnswers().get(1).getAnswerText());
        c3.setText(model.getMultiChoiceAnswers().get(2).getAnswerText());
        c4.setText(model.getMultiChoiceAnswers().get(3).getAnswerText());

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAll();
            }
        });
        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAll();
            }
        });
        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAll();
            }
        });
        c4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAll();
            }
        });

        return view;
    }
    public void checkAll(){
        answer="";
            if(c1.isChecked()) {
            answer = answer + c1.getText();
             }
            if(c2.isChecked()){
                answer=answer+","+c2.getText();
            }
            if(c3.isChecked()){
                answer=answer+","+c3.getText();
            }
            if(c4.isChecked()){
                answer=answer+","+c4.getText();
            }
            passData(answer);
            //showMessage(answer);

    }

    public interface OnDataPass {
        public void onDataPass(String data);

    }
    public void passData(String data) {
        dataPasser.onDataPass(data);
    }

    public void showMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
    public static CheckboxFragment newInstance(int index) {
        CheckboxFragment fragment = new CheckboxFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (CheckboxFragment.OnDataPass) context;
    }

    public static CheckboxFragment newInstance(Question model) {
        CheckboxFragment fragment = new CheckboxFragment();
        Bundle b = new Bundle();
        b.putSerializable(DESCRIBABLE_KEY, model);
        fragment.setArguments(b);
        return fragment;
    }
}