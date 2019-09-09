package com.AbhiDev.edurecomm.Exams.Fragment;

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
import com.wireout.Exams.QuestionResponse;
import com.wireout.Exams.TestResponse;
import com.wireout.R;
import com.wireout.models.exams.MultiChoiceAnswer;
import com.wireout.models.exams.Question;

import java.util.List;


public class OptionsNonMathFragment extends Fragment {
    private static final String DESCRIBABLE_KEY = "model_key";
    private Question model;
    Gson gson;
    OptionsFragment.OnDataPass dataPasser;
    String json;
    List<MultiChoiceAnswer> options;
    TextView option1,option2,option3,option4;
    TestResponse testResponse;
    QuestionResponse questionResponse;
    RadioButton rb1,rb2,rb3,rb4;
    TextView question;
    String quest = "When \\(a \\ne 0\\), there are two solutions to \\(ax^2 + bx + c = 0\\) and they are $$x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}.$$";

    public interface OnDataPass {
        public void onDataPass(String data);

    }

    public void passData(String data) {
        dataPasser.onDataPass(data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OptionsFragment.OnDataPass) context;
    }

    public OptionsNonMathFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options_text_layout, container, false);
        question = view.findViewById(R.id.quetxtview);
        model =   (Question) getArguments().getSerializable(DESCRIBABLE_KEY);
        question.setText(model.getQuestionText());
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);

        rb1 = view.findViewById(R.id.rb1);
        rb2 = view.findViewById(R.id.rb2);
        rb3 = view.findViewById(R.id.rb3);
        rb4 = view.findViewById(R.id.rb4);

        options = model.getMultiChoiceAnswers();
        if(options!=null) {
            if (options.size() > 0) {
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb1.setChecked(true);
                        passData(options.get(0).getAnswerText());
                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb2.setChecked(true);
                        passData(options.get(1).getAnswerText());
                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb3.setChecked(true);
                        passData(options.get(2).getAnswerText());
                    }
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb4.setChecked(true);
                        if (options.size() > 3)
                            passData(options.get(3).getAnswerText());
                        else {
                            passData("none");
                        }
                    }
                });

                options = model.getMultiChoiceAnswers();
                rb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb1.setChecked(true);
                        passData(options.get(0).getAnswerText());
                    }
                });

                rb2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb2.setChecked(true);
                        passData(options.get(1).getAnswerText());
                    }
                });
                rb3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb3.setChecked(true);
                        passData(options.get(2).getAnswerText());
                    }
                });

                rb4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearRadioChecked();
                        rb4.setChecked(true);
                        if (options.size() > 3)
                            passData(options.get(3).getAnswerText());
                        else {
                            passData("none");
                        }
                    }
                });
                if (options.get(0).getAnswerText() != null) {
                    option1.setText(options.get(0).getAnswerText());
                }
                if (options.get(1).getAnswerText() != null) {
                    option2.setText(options.get(1).getAnswerText());
                }
                if (options.get(2).getAnswerText() != null) {
                    option3.setText(options.get(2).getAnswerText());
                }
                if (options.size() > 3) {
                    if (options.get(3) != null) {
                        option4.setText(options.get(3).getAnswerText());
                    }
                } else {
                    option4.setText("None");
                }
            }
        }
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

    public static OptionsNonMathFragment newInstance(Question model) {
        OptionsNonMathFragment fragment = new OptionsNonMathFragment();
        Bundle b = new Bundle();
        b.putSerializable(DESCRIBABLE_KEY, model);
        fragment.setArguments(b);
        return fragment;
    }
}

