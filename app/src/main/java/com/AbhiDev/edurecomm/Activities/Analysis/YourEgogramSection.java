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
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.presenters.AnalysisPresenter;

import java.util.ArrayList;

public class YourEgogramSection extends AnalysisActivity{
    //id : 4
    AnalysisPresenter presenter;
    public static final String EGOGRAMCOMPLETED = "Ego_Gram_Completed";
    Intent i;
    ImageView backgroundImage;
    ImageView imageView;
    int count =0;
    float increaseFactor;
    BooleanSectionResponse section4;
    OnEntityReceivedListener<BooleanSectionResponse> listener;
    ArrayList<BooleanQuestionResponse> questionResponseArrayList;
    BooleanQuestion questions,motivationalQuestions,interestQuestions,abilityQuestions,meornotmeQuestions;

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
        section4 = new BooleanSectionResponse();
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
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            motivationalQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
        showEgoGramIntroScreen();
    }
    public void changeToolbar(){
        gameImage = findViewById(R.id.icon);
        toolTitle = findViewById(R.id.tooltilte);
        Picasso.with(this).load(R.drawable.ic_egogram).into(gameImage);
        toolTitle.setText("Your Egogram");
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
                BooleanQuestionResponse questionResponse = questionResponseArrayList.get(count);
                questionResponse.setAnswer(true);

            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                BooleanQuestionResponse questionResponse = questionResponseArrayList.get(count);
                questionResponse.setAnswer(true);
            }
        });
    }
    public void exitSectionButton(){
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YourEgogramSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
    @Override
    public void showEgoGramIntroScreen() {
        count=0;
        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.youregogram_start_screen));
        backgroundImage = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.youregogram_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YourEgogramSection.this, LifeChoicesSection.class);
                intent.putExtra("questionsSection2",meornotmeQuestions);
                intent.putExtra("questionsSection3",abilityQuestions);
                intent.putExtra("questionsSection4",interestQuestions);
                intent.putExtra("questionsSection5",questions);
                intent.putExtra("questions",motivationalQuestions);
                startActivity(intent);
                finish();
            }
        });
        startSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                count=1;
                showEgoGramQuestionScreen();
            }
        });
    }
    public void showEgoGramQuestionScreen() {
        setContentView(R.layout.questions_layout);
        changeToolbar();
        agreeDisagreeFunc();
        final TextView questionTextView = findViewById(R.id.quetxtview);
        final int noOfQuestions = questions.getQuestions().size();
        Picasso.with(getApplicationContext()).load(R.drawable.ic_agree).into(agreeButton);
        Picasso.with(getApplicationContext()).load(R.drawable.ic_disagree).into(disagreeButton);
        float value = (float)100.0/noOfQuestions;
        toolTitle = findViewById(R.id.tooltilte);
        toolTitle.setText("Your Egogram");
        sectionImage = findViewById(R.id.icon);
        Picasso.with(this).load(R.drawable.ic_egogram).into(sectionImage);
        increaseFactor =Math.round(value);
        BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
        questionResponse.setQuestion(questions.getQuestions().get(0).getId());
        questionResponseArrayList.add(questionResponse);
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
                    showEgoGramEndScreen();
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
                    section4.setSection(4);
                    section4.setAnswers(questionResponseArrayList);
                    section4.submit(listener);
                    showEgoGramEndScreen();
                }
            }
        });
    }

    public void showEgoGramEndScreen(){

        setContentView(R.layout.analysis_section_end_screen);
        TextView sectionName;
        TextView sectionDuration;
        ImageView exitSection;
        final ImageView continueSection;
        sectionDuration = findViewById(R.id.duration);
        sectionName = findViewById(R.id.section_name);
        sectionImage = findViewById(R.id.section_image);
        Picasso.with(this).load(R.drawable.ic_lifechoices).into(sectionImage);
        exitSection = findViewById(R.id.exit);
        continueSection = findViewById(R.id.continue_next);
        sectionDuration.setText("10 sec");
        sectionName.setText("Life Choices");
        MyApplication.getInstance().prefManager.saveString(EGOGRAMCOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"starting Flexibility Game",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(YourEgogramSection.this, LifeChoicesSection.class);
                intent.putExtra("questionsSection2",meornotmeQuestions);
                intent.putExtra("questionsSection3",abilityQuestions);
                intent.putExtra("questionsSection4",interestQuestions);
                intent.putExtra("questionsSection5",questions);
                intent.putExtra("questions",motivationalQuestions);
                startActivity(intent);
                finish();
            }
        });
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YourEgogramSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(count==0){
            Intent intent = new Intent(YourEgogramSection.this, InterstSection.class);
            intent.putExtra("questionsSection2",meornotmeQuestions);
            intent.putExtra("questionsSection3",abilityQuestions);
            intent.putExtra("questionsSection4",interestQuestions);
            intent.putExtra("questionsSection5",questions);
            intent.putExtra("questions",motivationalQuestions);
            startActivity(intent);
            finish();
        }
        else
        showMessage("Back button has been disabled for analysis purpose.");
    }
}
