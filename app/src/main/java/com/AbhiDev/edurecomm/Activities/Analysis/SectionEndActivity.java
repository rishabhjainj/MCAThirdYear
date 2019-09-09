package com.AbhiDev.edurecomm.Activities.Analysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.Activities.BaseActivity;
import com.wireout.Activities.BrainBoosterIntroScreen;

import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.mathGame.GameIntro;
import com.wireout.models.career_analysis.BooleanQuestion;

public class SectionEndActivity extends BaseActivity {
    TextView sectionName;
    TextView sectionDuration;
    ImageView exitSection;
    ImageView sectionImage;
    ImageView continueSection;

    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions,questions;

    public static final String BRAINBOOSTERCOMPLETED = "Brain_Booster_Completed";
    public static final String MATHGAMECOMPLETED = "Math_Game_Completed";
    public static final String FLEXIBILITYGAMECOMPLETED = "Flexibility_Game_Completed";
    public static final String VERBALABILITYCOMPLETED = "Verbal_Ability_Completed";

    @Override
    public void onBackPressed() {
        showMessage("Back button has been disabled for analysis purpose.");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_section_end_screen);
        sectionDuration = findViewById(R.id.duration);
        sectionName = findViewById(R.id.section_name);
        exitSection = findViewById(R.id.exit);
        sectionImage = findViewById(R.id.section_image);
        continueSection = findViewById(R.id.continue_next);
        if(getIntent().getSerializableExtra("questionsSection2")!=null) {
            meornotmeQuestions = (BooleanQuestion) getIntent().getSerializableExtra("questionsSection2");
        }else
            showMessage("null");
        if(getIntent().getSerializableExtra("questionsSection3")!=null)
            abilityQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection3");
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
        Intent i = getIntent();
        int section =Integer.parseInt(i.getStringExtra("section"));
        switch (section){

            case 6:
                sectionDuration.setText("1 min");
                sectionName.setText("Brain Teaser");
                Picasso.with(this).load(R.drawable.ic_brainbooster).into(sectionImage);
                continueSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // Toast.makeText(getApplicationContext(),"starting brain booster",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SectionEndActivity.this, BrainBoosterIntroScreen.class);
                        i.putExtra("questionsSection2",meornotmeQuestions);
                        i.putExtra("questionsSection3",abilityQuestions);
                        i.putExtra("questionsSection4",interestQuestions);
                        i.putExtra("questionsSection5",egogramQuestions);
                        i.putExtra("questions",questions);
                startActivity(i);
                finish();
                    }
                });
                exitSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(SectionEndActivity.this, AnalysisStatusActivity.class));
                        MyApplication.getInstance().prefManager.saveString(VERBALABILITYCOMPLETED,"true");
                        finish();
                    }
                });
                break;
            case 7:
                sectionDuration.setText("1 min");
                sectionName.setText("Emotional Intelligence");
                Picasso.with(this).load(R.drawable.ic_emotioalintelligence).into(sectionImage);
                continueSection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(),"starting emotional intelligence",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SectionEndActivity.this, EmotionalIntelligenceSection.class);
                    i.putExtra("questionsSection2",meornotmeQuestions);
                    i.putExtra("questionsSection3",abilityQuestions);
                    i.putExtra("questionsSection4",interestQuestions);
                    i.putExtra("questionsSection5",egogramQuestions);
                    i.putExtra("questions",questions);
                startActivity(i);
                finish();
                }
            });
            exitSection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SectionEndActivity.this, AnalysisStatusActivity.class));
                    MyApplication.getInstance().prefManager.saveString(BRAINBOOSTERCOMPLETED,"true");
                    finish();
                }
            });
            break;
            case 9:
                sectionDuration.setText("45 sec");
                sectionName.setText("Quick Maths");
                Picasso.with(this).load(R.drawable.ic_quickmaths).into(sectionImage);
                continueSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(),"starting Quick Maths",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SectionEndActivity.this, GameIntro.class);
                        i.putExtra("questionsSection2",meornotmeQuestions);
                        i.putExtra("questionsSection3",abilityQuestions);
                        i.putExtra("questionsSection4",interestQuestions);
                        i.putExtra("questionsSection5",egogramQuestions);
                        i.putExtra("questions",questions);
                startActivity(i);
                finish();
                    }
                });
                exitSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(SectionEndActivity.this, AnalysisStatusActivity.class));
                        MyApplication.getInstance().prefManager.saveString(FLEXIBILITYGAMECOMPLETED,"true");
                        finish();
                    }
                });
                break;
            case 10:
                if(getIntent().getSerializableExtra("questions")!=null)
                    questions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
                sectionDuration.setText("1 min");
                sectionName.setText("Motivational Quotient");
                Picasso.with(this).load(R.drawable.ic_motivationquotient).into(sectionImage);
                continueSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(SectionEndActivity.this,MotivationalQuotientSection.class);
                        i.putExtra("questionsSection2",meornotmeQuestions);
                        i.putExtra("questionsSection3",abilityQuestions);
                        i.putExtra("questionsSection4",interestQuestions);
                        i.putExtra("questionsSection5",egogramQuestions);
                        i.putExtra("questions",questions);
                        startActivity(i);
                    }
                });
                exitSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(SectionEndActivity.this, AnalysisStatusActivity.class));
                        MyApplication.getInstance().prefManager.saveString(MATHGAMECOMPLETED,"true");
                        finish();
                    }
                });
                break;
        }

    }
}
