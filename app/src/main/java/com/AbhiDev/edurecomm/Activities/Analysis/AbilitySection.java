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

public class AbilitySection extends AnalysisActivity {
    //id : 1
    AnalysisPresenter presenter;
    Intent i;

    ImageView imageView;
    int count =0;
    ImageView backgroundImage;
    TextView agreeText,disagreeText;
    BooleanSectionResponse section3;
    OnEntityReceivedListener<BooleanSectionResponse> listener;
    ArrayList<BooleanQuestionResponse> questionResponseArrayList;

    float increaseFactor;
    BooleanQuestion meOrNotMeQuestions,questions,interestQuestions,egogramQuestions,motivationalQuestions;

    public static final String ABILITYCOMPLETED = "Ability_Completed";

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
        section3 = new BooleanSectionResponse();
        questionResponseArrayList = new ArrayList<>();
        if(getIntent().getSerializableExtra("questionsSection2")!=null) {
            meOrNotMeQuestions = (BooleanQuestion) getIntent().getSerializableExtra("questionsSection2");
        }
        else{
            showMessage("null");
        }
        if(getIntent().getSerializableExtra("questionsSection3")!=null)
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection3");
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            motivationalQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questions");


        showAbilityIntroScreen();


    }
    public void showAbilityIntroScreen(){
        count=0;
        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.ability_start_screen));
        backgroundImage = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.ability_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AbilitySection.this, InterstSection.class);
                intent.putExtra("questionsSection2",meOrNotMeQuestions);
                intent.putExtra("questionsSection3",questions);
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
                count=0;
                showAbilityQuestionScreen();
            }
        });

    }
    public void noSelected(){
//        Picasso.with(this).load(R.drawable.ic_no_selected).into(disagreeButton);
//        Picasso.with(this).load(R.drawable.ic_yes).into(agreeButton);
        disagreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_no_selected));
        agreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_yes));

    }
    public void yesSelected(){
//        Picasso.with(this).load(R.drawable.ic_yes_selected).into(agreeButton);
//        Picasso.with(this).load(R.drawable.ic_no).into(disagreeButton);
        disagreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_no));
        agreeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_yes_selected));
    }
    public void yesNoFunc(){
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesSelected();
                BooleanQuestionResponse questionResponse = questionResponseArrayList.get(count);
                questionResponse.setAnswer(true);

            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noSelected();
                BooleanQuestionResponse questionResponse = questionResponseArrayList.get(count);
                questionResponse.setAnswer(false);
            }
        });
    }
    public void exitSectionButton(){
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AbilitySection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(count==0){
            Intent intent =new Intent(AbilitySection.this, MeOrNotMeSection.class);
            intent.putExtra("questionsSection2",meOrNotMeQuestions);
            intent.putExtra("questionsSection3",questions);
            intent.putExtra("questionsSection4",interestQuestions);
            intent.putExtra("questionsSection5",egogramQuestions);
            intent.putExtra("questions",motivationalQuestions);
            startActivity(intent);
            finish();
        }
        else
        showMessage("Back button has been disabled for analysis purpose.");
    }

    public void showAbilityQuestionScreen() {
        setContentView(R.layout.questions_layout);
        yesNoFunc();
        final TextView questionTextView = findViewById(R.id.quetxtview);
        if(questions!=null) {
            final int noOfQuestions = questions.getQuestions().size();
            float value = (float) 100.0 / noOfQuestions;
            Picasso.with(this).load(R.drawable.ic_yes).into(agreeButton);
            Picasso.with(this).load(R.drawable.ic_no).into(disagreeButton);
            toolTitle = findViewById(R.id.tooltilte);
            toolTitle.setText("Ability");
            sectionImage = findViewById(R.id.icon);
            Picasso.with(this).load(R.drawable.ic_ability).into(sectionImage);
            increaseFactor = Math.round(value);
            BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
            questionResponse.setQuestion(questions.getQuestions().get(0).getId());
            questionResponseArrayList.add(questionResponse);
            next = (Button) findViewById(R.id.next1);
            skip = (Button) findViewById(R.id.skip1);
            agreeText = findViewById(R.id.agreetext);
            disagreeText = findViewById(R.id.disagreetext);
            agreeText.setText("Yes");
            disagreeText.setText("No");
            //imageView = findViewById(R.id.image);
            Log.d("sectionNamesno", noOfQuestions + ",value" + value + ",increase" + increaseFactor);
            exitSectionButton();
            questionTextView.setText(questions.getQuestions().get(count).getText());
            setProgressBarData(Math.round(increaseFactor * count));
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    Picasso.with(getApplicationContext()).load(R.drawable.ic_yes).into(agreeButton);
                    Picasso.with(getApplicationContext()).load(R.drawable.ic_no).into(disagreeButton);
                    if (count < noOfQuestions) {
                        questionTextView.setText(questions.getQuestions().get(count).getText());
                        BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
                        questionResponse.setQuestion(questions.getQuestions().get(count).getId());
                        questionResponseArrayList.add(questionResponse);
                       // showMessage(count+"");
                        setProgressBarData(Math.round(increaseFactor * count));
                        Log.d("sectioncount", count + "");

                    }
//
                    else {
                        section3.setSection(1);
                        section3.setAnswers(questionResponseArrayList);
                        section3.submit(listener);
                        showAbilityEndScreen();
                    }
                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    Picasso.with(getApplicationContext()).load(R.drawable.ic_yes).into(agreeButton);
                    Picasso.with(getApplicationContext()).load(R.drawable.ic_no).into(disagreeButton);
                    if (count < noOfQuestions) {
                        questionTextView.setText(questions.getQuestions().get(count).getText());
                        setProgressBarData(Math.round(increaseFactor * count));
                        BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
                        questionResponse.setQuestion(questions.getQuestions().get(count).getId());
                        questionResponseArrayList.add(questionResponse);
                       // showMessage(count+"");
                        questionResponseArrayList.add(questionResponse);
                        Log.d("sectioncount", count + "");

                    }
//
                    else {
                        section3.setSection(1);
                        section3.setAnswers(questionResponseArrayList);
                        section3.submit(listener);
                        showAbilityEndScreen();
                    }
                }
            });
        }


    }
    public void showAbilityEndScreen(){
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
        Picasso.with(this).load(R.drawable.ic_interest).into(sectionImage);
        sectionDuration.setText("30 sec");
        sectionName.setText("Interests");
        MyApplication.getInstance().prefManager.saveString(ABILITYCOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"starting Interest",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AbilitySection.this, InterstSection.class);
                intent.putExtra("questionsSection2",meOrNotMeQuestions);
                intent.putExtra("questionsSection3",questions);
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
                startActivity(new Intent(AbilitySection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
}
