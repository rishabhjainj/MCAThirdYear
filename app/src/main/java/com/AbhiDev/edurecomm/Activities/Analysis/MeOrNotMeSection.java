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

public class MeOrNotMeSection extends AnalysisActivity {
    AnalysisPresenter presenter;
    BooleanSectionResponse section2;
    Intent i;
    ImageView imageView;
    int count =0;
    OnEntityReceivedListener<BooleanSectionResponse> listener;
    ArrayList<BooleanQuestionResponse> questionResponseArrayList;
    float increaseFactor;
    ImageView backgroundImage;
    BooleanQuestion questions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions;
    public static final String MEORNOTMECOMPLETED = "Me_Or_Not_Me_Completed";

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
        section2 = new BooleanSectionResponse();
        questionResponseArrayList = new ArrayList<>();
        if(getIntent().getSerializableExtra("questionsSection2")!=null) {
            questions = (BooleanQuestion) getIntent().getSerializableExtra("questionsSection2");
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
            motivationalQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questions");


        presenter.startMeOrNotMe();
    }

    @Override
    public void showMeOrNotMeIntro() {
        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.meormenot_start_screen));
        backgroundImage = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.meormenot_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MeOrNotMeSection.this, AbilitySection.class);
                intent.putExtra("questionsSection2",questions);
                intent.putExtra("questionsSection3",abilityQuestions);
                intent.putExtra("questionsSection4",interestQuestions);
                intent.putExtra("questionsSection5",egogramQuestions);
                intent.putExtra("questions",motivationalQuestions);
                startActivity(intent);
                finish();
            }
        });
        startSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
    }

    public void exitSectionButton(){
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeOrNotMeSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(presenter.sequence==0){
            Intent intent = new Intent(MeOrNotMeSection.this,AnalysisBioDataSection.class);
            intent.putExtra("questionsSection2",questions);
            intent.putExtra("questionsSection3",abilityQuestions);
            intent.putExtra("questionsSection4",interestQuestions);
            intent.putExtra("questionsSection5",egogramQuestions);
            intent.putExtra("questions",motivationalQuestions);
            startActivity(intent);
            finish();
        }
        else
        showMessage("Back button has been disabled for analysis purpose.");
    }
    public void iAmNotSelected(){
//        Picasso.with(this).load(R.drawable.ic_iamnot_selected).into(disagreeButton);
//        Picasso.with(this).load(R.drawable.ic_iam).into(agreeButton);
        agreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_iam));
        disagreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_iamnot_selected));

    }
    public void iAmSelected(){
//        Picasso.with(this).load(R.drawable.ic_iam_selected).into(agreeButton);
//        Picasso.with(this).load(R.drawable.ic_iamnot).into(disagreeButton);
        agreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_iam_selected));
        disagreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_iamnot));
    }
    public void IamIamNotFunc(){
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAmSelected();
                BooleanQuestionResponse questionResponse = questionResponseArrayList.get(count);
                questionResponse.setAnswer(true);

            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAmNotSelected();
                BooleanQuestionResponse questionResponse = questionResponseArrayList.get(count);
                questionResponse.setAnswer(false);

            }
        });
    }

    @Override
    public void showMeOrNotMeQue1() {
        setContentView(R.layout.meornotme_que_layout);
        final ImageView flag = findViewById(R.id.flag);
        final ImageView flag1 = findViewById(R.id.flag1);
        final TextView questionTextView = findViewById(R.id.quetxtview);
        final int noOfQuestions = questions.getQuestions().size();
        float value = (float)100.0/noOfQuestions;
        IamIamNotFunc();
        increaseFactor =Math.round(value);
        if(count<noOfQuestions-2){
            Picasso.with(getApplicationContext()).load(questions.getQuestions().get(count+1).getImage()).into(flag);
            Picasso.with(getApplicationContext()).load(questions.getQuestions().get(count+2).getImage()).into(flag1);

        }
        BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
        questionResponse.setQuestion(questions.getQuestions().get(0).getId());
        questionResponseArrayList.add(questionResponse);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        imageView = findViewById(R.id.image);
        Log.d("sectionNamesno",noOfQuestions+",value"+value+",increase"+increaseFactor);
        exitSectionButton();
        questionTextView.setText(questions.getQuestions().get(count).getText());
        if(questions.getQuestions().get(0).getImage()!=null){
            Picasso.with(this).load(questions.getQuestions().get(0).getImage()).into(imageView);
        }
        setProgressBarData(Math.round(increaseFactor*count));
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count++;
                agreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_iam));
                disagreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_iamnot));
                if(count<noOfQuestions-2){
                    Picasso.with(getApplicationContext()).load(questions.getQuestions().get(count+1).getImage()).into(flag);
                    Picasso.with(getApplicationContext()).load(questions.getQuestions().get(count+2).getImage()).into(flag1);

                }
                if(count<noOfQuestions){
                    questionTextView.setText(questions.getQuestions().get(count).getText());
                    BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
                    questionResponse.setQuestion(questions.getQuestions().get(count).getId());
                    questionResponseArrayList.add(questionResponse);
                    //showMessage(count+"");
                    questionResponseArrayList.add(questionResponse);
                    if(questions.getQuestions().get(count).getImage()!=null){
                        Picasso.with(getApplicationContext()).load(questions.getQuestions().get(count).getImage()).into(imageView);
                    }
                    setProgressBarData(Math.round(increaseFactor*count));
                    Log.d("sectioncount",count+"");

                }
//
                else{
                    section2.setSection(5);
                    section2.setAnswers(questionResponseArrayList);
                    section2.submit(listener);
                    showMeOrNotMeEndScreen();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                agreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_iam));
                disagreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_iamnot));
                if(count<noOfQuestions-2){
                    Picasso.with(getApplicationContext()).load(questions.getQuestions().get(count+1).getImage()).into(flag);
                    Picasso.with(getApplicationContext()).load(questions.getQuestions().get(count+2).getImage()).into(flag1);

                }
                if(count<noOfQuestions){
                    questionTextView.setText(questions.getQuestions().get(count).getText());
                    BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
                    questionResponse.setQuestion(questions.getQuestions().get(count).getId());
                    questionResponseArrayList.add(questionResponse);
                    //showMessage(count+"");
                    if(questions.getQuestions().get(count).getImage()!=null){
                        Picasso.with(getApplicationContext()).load(questions.getQuestions().get(count).getImage()).into(imageView);
                    }
                    setProgressBarData(Math.round(increaseFactor*count));


                }
//
                else{
                    section2.setSection(5);
                    section2.setAnswers(questionResponseArrayList);
                    section2.submit(listener);
                    showMeOrNotMeEndScreen();
                }
            }
        });


    }
    public void showMeOrNotMeEndScreen(){
        setContentView(R.layout.analysis_section_end_screen);
        TextView sectionName;
        TextView sectionDuration;
        ImageView exitSection;
        final ImageView continueSection;
        sectionDuration = findViewById(R.id.duration);
        sectionName = findViewById(R.id.section_name);
        exitSection = findViewById(R.id.exit);
        continueSection = findViewById(R.id.continue_next);
        sectionImage = findViewById(R.id.section_image);
        Picasso.with(this).load(R.drawable.ic_ability).into(sectionImage);
        sectionDuration.setText("30 sec");
        sectionName.setText("Ability");
        MyApplication.getInstance().prefManager.saveString(MEORNOTMECOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //Toast.makeText(getApplicationContext(),"starting Ability",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MeOrNotMeSection.this, AbilitySection.class);
                intent.putExtra("questionsSection2",questions);
                intent.putExtra("questionsSection3",abilityQuestions);
                intent.putExtra("questionsSection4",interestQuestions);
                intent.putExtra("questionsSection5",egogramQuestions);
                intent.putExtra("questions",motivationalQuestions);
                startActivity(intent);
                finish();
            }
        });
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeOrNotMeSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
}
