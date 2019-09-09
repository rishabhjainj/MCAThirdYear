package com.AbhiDev.edurecomm.Activities.Analysis;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wireout.Exams.Fragment.OptionsFragment;
import com.wireout.Exams.Fragment.OptionsNonMathFragment;
import com.wireout.Exams.QuestionResponse;
import com.wireout.Exams.TestResponse;
import com.wireout.R;
import com.wireout.models.exams.MultiChoiceAnswer;
import com.wireout.models.exams.Question;

import java.util.List;

public class CommSectionFragment extends Fragment {
    private static final String DESCRIBABLE_KEY = "model_key";
    private QuestionModel model;
    Gson gson;
    OnDataPass dataPasser;
    String json;
    List<MultiChoiceAnswer> options;
    TextView option1,option2,option3,option4;
    TestResponse testResponse;
    QuestionResponse questionResponse;
    RadioButton rb1,rb2,rb3,rb4;
    TextView question;


    public interface OnDataPass {
        public void onDataPass(String data);

    }

    public void passData(String data) {
        if(data!=null||!data.equals(""))
        dataPasser.onDataPass(data);
        else
            dataPasser.onDataPass("none");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }

    public CommSectionFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options_text_layout, container, false);
        question = view.findViewById(R.id.quetxtview);
        model =   (QuestionModel) getArguments().getSerializable(DESCRIBABLE_KEY);
        question.setText(model.getQuestionText());
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);

        rb1 = view.findViewById(R.id.rb1);
        rb2 = view.findViewById(R.id.rb2);
        rb3 = view.findViewById(R.id.rb3);
        rb4 = view.findViewById(R.id.rb4);

                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb1.setChecked(true);
                        passData(model.getOption1());
                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb2.setChecked(true);
                        passData(model.getOption2());
                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb3.setChecked(true);
                        passData(model.getOption3());
                    }
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb4.setChecked(true);
                        passData(model.getOption4());

                    }
                });

                rb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb1.setChecked(true);
                        passData(model.getOption1());
                    }
                });
                rb2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb2.setChecked(true);
                        passData(model.getOption2());
                    }
                });
                rb3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb3.setChecked(true);
                        passData(model.getOption3());
                    }
                });
                rb4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb4.setChecked(true);
                        passData(model.getOption4());

                    }
                });


                option1.setText(model.getOption1());
                option2.setText(model.getOption2());
                option3.setText(model.getOption3());
                option4.setText(model.getOption4());



        return view;
    }
    public void clearRadioChecked() {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
    }

    public void showMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
    public static OptionsFragment newInstance(int index) {
        OptionsFragment fragment = new OptionsFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    public static CommSectionFragment newInstance(QuestionModel model) {
        CommSectionFragment fragment = new CommSectionFragment();
        Bundle b = new Bundle();
        b.putSerializable(DESCRIBABLE_KEY, model);
        fragment.setArguments(b);
        return fragment;
    }
}
