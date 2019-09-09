package com.AbhiDev.edurecomm.Exams.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wireout.Exams.ExamQuestionModel;
import com.wireout.R;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.models.exams.Question;

import io.github.kexanie.library.MathView;

public class YesNoQuestionFragment extends Fragment {
    MathView question;
    private static final String DESCRIBABLE_KEY = "model_key";
    private ExamQuestionModel model;
    String tex = "This come from string. You can insert inline formula:" +
            " \\(ax^2 + bx + c = 0\\) " +
            "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";
    public YesNoQuestionFragment(){

    }

    public void showMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.yesno_question_layout,container,false);
        question = view.findViewById(R.id.quetxtview);
        model =   (ExamQuestionModel) getArguments().getSerializable(DESCRIBABLE_KEY);
        question.setText(model.getQuestion());
        return view;
    }
    public static YesNoQuestionFragment newInstance(int index) {
        YesNoQuestionFragment fragment = new YesNoQuestionFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }
    public static YesNoQuestionFragment newInstance(Question model) {
        YesNoQuestionFragment fragment = new YesNoQuestionFragment();
        Bundle b = new Bundle();
        b.putSerializable(DESCRIBABLE_KEY, model);
        fragment.setArguments(b);
        return fragment;
    }
}
