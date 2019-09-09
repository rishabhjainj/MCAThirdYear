package com.AbhiDev.edurecomm.mathGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.Activities.Analysis.MotivationalQuotientSection;

import com.wireout.Activities.BaseActivity;
import com.wireout.R;
import com.wireout.game2.FlexibilityGameIntro;
import com.wireout.models.career_analysis.BooleanQuestion;


public class GameIntro extends BaseActivity
{

    LinearLayout info ,play ,exit;
    TextView gameName;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions,questions;

    ImageView imageView;
    RelativeLayout backgroundIntro;
    Button startSection,skipSection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.quickmaths_start_screen));
        imageView = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.quickmaths_start_screen).into(imageView);
        startSection = (Button) findViewById(R.id.start);

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
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(GameIntro.this,MotivationalQuotientSection.class);
                i.putExtra("questionsSection2",meornotmeQuestions);
                i.putExtra("questionsSection3",abilityQuestions);
                i.putExtra("questionsSection4",interestQuestions);
                i.putExtra("questionsSection5",egogramQuestions);
                i.putExtra("questions",questions);
                startActivity(i);
            }
        });
        startSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(GameIntro.this,MathGame.class);
                i.putExtra("questionsSection2",meornotmeQuestions);
                i.putExtra("questionsSection3",abilityQuestions);
                i.putExtra("questionsSection4",interestQuestions);
                i.putExtra("questionsSection5",egogramQuestions);
                i.putExtra("questions",questions);
                startActivity(i);
            }
        });





    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(GameIntro.this, FlexibilityGameIntro.class);
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
        startActivity(i);
        finish();
    }
}
