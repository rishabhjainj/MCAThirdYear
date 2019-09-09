package com.AbhiDev.edurecomm.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wireout.Exams.QuestionResponse;
import com.wireout.R;
import com.wireout.models.exams.MultiChoiceAnswer;
import com.wireout.models.exams.Question;

import io.github.kexanie.library.MathView;

public class ExplanationActivityText extends BaseActivity {
    Question question;
    QuestionResponse answer;
    TextView quesNo;
    Toolbar toolbar;
    TextView ques,option1,option2,option3,option4,explanation;
    int correct=0;
    int myResponse=0;
    String math;
    AppCompatRadioButton radioButton1,radioButton2,radioButton3,radioButton4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        math = getIntent().getStringExtra("math");
        question =(Question) getIntent().getSerializableExtra("question");
        answer = (QuestionResponse) getIntent().getSerializableExtra("answer");
        setContentView(R.layout.activity_explanation_layout_text);

        toolbar = findViewById(R.id.toolbar);
        radioButton1 = findViewById(R.id.rb1);
        radioButton2 = findViewById(R.id.rb2);
        radioButton3 = findViewById(R.id.rb3);
        radioButton4 = findViewById(R.id.rb4);


        quesNo = findViewById(R.id.questno);
        ques = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        explanation = findViewById(R.id.explanation);

        if(answer.getAnswer().equals("na")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        }
        for(MultiChoiceAnswer answer: question.getMultiChoiceAnswers()){
            correct+=1;
            if(answer.getAnswerText().equals(question.getCorrectAnswer())){
                // showMessage("correct answer:"+question.getCorrectAnswer());
                break;
            }
        }
        // showMessage(question.getCorrectAnswer()+correct);
        if(!this.answer.getAnswer().equals("na")) {
            for (MultiChoiceAnswer answer : question.getMultiChoiceAnswers()) {
                myResponse += 1;
                if (answer.getAnswerText().equals(this.answer.getAnswer())) {
                    //showMessage("correct answer:"+question.getCorrectAnswer());
                    break;
                }
            }

            if (correct == myResponse) {
                toolbar.setBackgroundColor(getResources().getColor(R.color.green500));
            } else {
                toolbar.setBackgroundColor(getResources().getColor(R.color.red));
            }
        }

        quesNo.setText("Q"+question.getSerialNo());
        ques.setText(question.getQuestionText());
        //Toast.makeText(this,correct+"",Toast.LENGTH_SHORT).show();
        if(correct!=myResponse){
            switch (myResponse){
                case 1: radioButton1.setHighlightColor(getResources().getColor(R.color.green500));
                    radioButton1.setChecked(true);
                    radioButton1.setSupportButtonTintList(
                            ContextCompat.getColorStateList(this,
                                    R.color.single_choice_state_red));
                    //radioButton1.setBackgroundColor(getResources().getColor(R.color.green500));
                    break;
                case 2:radioButton2.setHighlightColor(getResources().getColor(R.color.green500));
                    radioButton2.setChecked(true);
                    radioButton2.setSupportButtonTintList(
                            ContextCompat.getColorStateList(this,
                                    R.color.single_choice_state_red));
                    //radioButton2.setBackgroundColor(getResources().getColor(R.color.green500));
                    break;
                case 3:radioButton3.setHighlightColor(getResources().getColor(R.color.green500));

                    radioButton3.setChecked(true);
                    radioButton3.setSupportButtonTintList(
                            ContextCompat.getColorStateList(this,
                                    R.color.single_choice_state_red));
                    //radioButton3.setBackgroundColor(getResources().getColor(R.color.green500));
                    break;
                case 4:radioButton4.setHighlightColor(getResources().getColor(R.color.green500));
                    radioButton4.setChecked(true);
                    radioButton4.setSupportButtonTintList(
                            ContextCompat.getColorStateList(this,
                                    R.color.single_choice_state_red));
                    // radioButton2.setBackgroundColor(getResources().getColor(R.color.green500));
                    break;
            }
        }
        switch (correct){
            case 1: radioButton1.setHighlightColor(getResources().getColor(R.color.green500));
                radioButton1.setChecked(true);
                radioButton1.setSupportButtonTintList(
                        ContextCompat.getColorStateList(this,
                                R.color.set_single_select_green));
                //radioButton1.setBackgroundColor(getResources().getColor(R.color.green500));
                break;
            case 2:radioButton2.setHighlightColor(getResources().getColor(R.color.green500));
                radioButton2.setChecked(true);
                radioButton2.setSupportButtonTintList(
                        ContextCompat.getColorStateList(this,
                                R.color.set_single_select_green));
                //radioButton2.setBackgroundColor(getResources().getColor(R.color.green500));
                break;
            case 3:radioButton3.setHighlightColor(getResources().getColor(R.color.green500));

                radioButton3.setChecked(true);
                radioButton3.setSupportButtonTintList(
                        ContextCompat.getColorStateList(this,
                                R.color.set_single_select_green));
                //radioButton3.setBackgroundColor(getResources().getColor(R.color.green500));
                break;
            case 4:radioButton4.setHighlightColor(getResources().getColor(R.color.green500));
                radioButton4.setChecked(true);
                radioButton4.setSupportButtonTintList(
                        ContextCompat.getColorStateList(this,
                                R.color.set_single_select_green));
                // radioButton2.setBackgroundColor(getResources().getColor(R.color.green500));
                break;
        }


        if(question.getMultiChoiceAnswers().get(0).getAnswerText()!=null){
            option1.setText(question.getMultiChoiceAnswers().get(0).getAnswerText());
        }
        if(question.getMultiChoiceAnswers().get(1).getAnswerText()!=null){
            option2.setText(question.getMultiChoiceAnswers().get(1).getAnswerText());
        }
        if(question.getMultiChoiceAnswers().get(2).getAnswerText()!=null){
            option3.setText(question.getMultiChoiceAnswers().get(2).getAnswerText());
        }
        if(question.getMultiChoiceAnswers().size()>3) {
            if (question.getMultiChoiceAnswers().get(3) != null) {
                option4.setText(question.getMultiChoiceAnswers().get(3).getAnswerText());
            }
        }
        else{
            option4.setText("None");
        }

        explanation.setText(question.getExplanation());

    }
}
