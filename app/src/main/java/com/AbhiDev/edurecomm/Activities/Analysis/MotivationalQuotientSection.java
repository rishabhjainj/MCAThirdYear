package com.AbhiDev.edurecomm.Activities.Analysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.BooleanQuestionResponse;
import com.wireout.models.career_analysis.BooleanSectionResponse;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.mathGame.GameIntro;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.presenters.AnalysisPresenter;

import java.util.ArrayList;

public class MotivationalQuotientSection extends AnalysisActivity{
    //id : 3
    AnalysisPresenter presenter;
    public static final String MOTIVATIONALCOMPLETED = "Motivational_Completed";
    ImageView imageView,backgroundImage;
    int count =0;
    float increaseFactor;
    BooleanSectionResponse section5;
    OnEntityReceivedListener<BooleanSectionResponse> listener;
    ArrayList<BooleanQuestionResponse> questionResponseArrayList;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions,questions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AnalysisPresenter(this);
        listener = new OnEntityReceivedListener<BooleanSectionResponse>(this) {
            @Override
            public void onReceived(BooleanSectionResponse entity) {
                // showMessage("saved");
            }
        };
        section5 = new BooleanSectionResponse();
        questionResponseArrayList = new ArrayList<>();
        if(getIntent().getSerializableExtra("questionsSection2")!=null) {
            meornotmeQuestions = (BooleanQuestion) getIntent().getSerializableExtra("questionsSection2");
        }
        else{
            showMessage("null");
        }
        if(getIntent().getSerializableExtra("questionsSection3")!=null)
            abilityQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection3");
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
            showEmotionalIntroScreen();

    }
    public void changeToolbar(){
        gameImage = findViewById(R.id.icon);
        toolTitle = findViewById(R.id.tooltilte);
        Picasso.with(this).load(R.drawable.ic_motivationquotient).into(gameImage);
        toolTitle.setText("Motivational Quotient");
    }
    public void disagreeSelected(){
//        Picasso.with(this).load(R.drawable.ic_disagree_selected).into(disagreeButton);
//        Picasso.with(this).load(R.drawable.ic_agree).into(agreeButton);
        disagreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_disagree_selected));
        agreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_agree));

    }
    public void agreeSelected(){
//        Picasso.with(this).load(R.drawable.ic_agree_selected).into(agreeButton);
//        Picasso.with(this).load(R.drawable.ic_disagree).into(disagreeButton);
        disagreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_disagree));
        agreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_agree_selected));
    }
    public void agreeDisagreeFunc(){
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
            }
        });
    }
    public void exitSectionButton(){
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MotivationalQuotientSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
    public void showEmotionalIntroScreen() {
        count=0;
        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.motivational_start_screen));
        backgroundImage = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.motivational_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotivationalQuotientSection.this, CommSkillSection.class);
                intent.putExtra("questionsSection2",meornotmeQuestions);
                intent.putExtra("questionsSection3",abilityQuestions);
                intent.putExtra("questionsSection4",interestQuestions);
                intent.putExtra("questionsSection5",egogramQuestions);
                intent.putExtra("questions",questions);
                startActivity(intent);
                finish();
            }
        });
        startSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=1;
                showMotivationalQuestionScreen();
            }
        });
    }
    public void showMotivationalQuestionScreen() {
        setContentView(R.layout.questions_layout);
        agreeDisagreeFunc();
        changeToolbar();
        final TextView questionTextView = findViewById(R.id.quetxtview);
        final int noOfQuestions = questions.getQuestions().size();
        Picasso.with(getApplicationContext()).load(R.drawable.ic_agree).into(agreeButton);
        Picasso.with(getApplicationContext()).load(R.drawable.ic_disagree).into(disagreeButton);
        float value = (float)100.0/noOfQuestions;
        toolTitle = findViewById(R.id.tooltilte);
        toolTitle.setText("Motivational Quotient");
        sectionImage = findViewById(R.id.icon);
        Picasso.with(this).load(R.drawable.ic_motivationquotient).into(sectionImage);
        increaseFactor =Math.round(value);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        //imageView = findViewById(R.id.image);
        Log.d("sectionNamesno",noOfQuestions+",value"+value+",increase"+increaseFactor);
        exitSectionButton();
        questionTextView.setText(questions.getQuestions().get(count).getText());
        setProgressBarData(Math.round(increaseFactor*count));
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                Picasso.with(getApplicationContext()).load(R.drawable.ic_agree).into(agreeButton);
                Picasso.with(getApplicationContext()).load(R.drawable.ic_disagree).into(disagreeButton);
                if(count<noOfQuestions){
                    questionTextView.setText(questions.getQuestions().get(count).getText());
                    setProgressBarData(Math.round(increaseFactor*count));
                    BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
                    questionResponse.setQuestion(questions.getQuestions().get(count).getId());
                    questionResponseArrayList.add(questionResponse);
                    Log.d("sectioncount",count+"");

                }
//
                else{
                    showMotivationalEndScreen();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                Picasso.with(getApplicationContext()).load(R.drawable.ic_agree).into(agreeButton);
                Picasso.with(getApplicationContext()).load(R.drawable.ic_disagree).into(disagreeButton);
                if(count<noOfQuestions){
                    questionTextView.setText(questions.getQuestions().get(count).getText());
                    setProgressBarData(Math.round(increaseFactor*count));
                    BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
                    questionResponse.setQuestion(questions.getQuestions().get(count).getId());
                    questionResponseArrayList.add(questionResponse);
                    Log.d("sectioncount",count+"");

                }
//
                else{
                    section5.setSection(3);
                    section5.setAnswers(questionResponseArrayList);
                    section5.submit(listener);
                    showMotivationalEndScreen();
                }
            }
        });
    }

    public void showMotivationalEndScreen(){

        setContentView(R.layout.analysis_section_end_screen);
        TextView sectionName;
        TextView sectionDuration;
        ImageView exitSection;
        final ImageView continueSection;
        sectionDuration = findViewById(R.id.duration);
        sectionName = findViewById(R.id.section_name);
        sectionImage = findViewById(R.id.section_image);
        Picasso.with(this).load(R.drawable.ic_handwriting).into(sectionImage);
        exitSection = findViewById(R.id.exit);
        continueSection = findViewById(R.id.continue_next);
        sectionDuration.setText("5 min");
        sectionName.setText("Communication Skills");
        MyApplication.getInstance().prefManager.saveString(MOTIVATIONALCOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"starting Flexibility Game",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MotivationalQuotientSection.this, CommSkillSection.class);
                intent.putExtra("questionsSection2",meornotmeQuestions);
                intent.putExtra("questionsSection3",abilityQuestions);
                intent.putExtra("questionsSection4",interestQuestions);
                intent.putExtra("questionsSection5",egogramQuestions);
                intent.putExtra("questions",questions);
                startActivity(intent);
                finish();
            }
        });
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MotivationalQuotientSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(count==0){
            Intent i=new Intent(MotivationalQuotientSection.this,GameIntro.class);
            i.putExtra("questionsSection2",meornotmeQuestions);
            i.putExtra("questionsSection3",abilityQuestions);
            i.putExtra("questionsSection4",interestQuestions);
            i.putExtra("questionsSection5",egogramQuestions);
            i.putExtra("questions",questions);
            startActivity(i);
        }
        else
        showMessage("Back button has been disabled for analysis purpose.");
    }

}
