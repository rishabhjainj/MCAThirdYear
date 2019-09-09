package com.AbhiDev.edurecomm.fourpicsoneword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wireout.Activities.Analysis.LifeChoicesSection;
import com.wireout.Activities.BackPressed;
import com.wireout.Activities.BrainBoosterIntroScreen;
import com.wireout.R;
import com.wireout.models.career_analysis.BooleanQuestion;


/**
 * Created by Avishi Goyal on 13-07-2017.
 */

public class VerbalAbilityIntroScreen extends BackPressed {

    LinearLayout info ,play ,exit;
    ImageView guide,start,skip,instr,gameImage;
    ImageView imageView;
    TextView gameName,instructions,textskip,textstart,textinfo;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,questions;
    RelativeLayout backgroundIntro;
    Button startSection,skipSection;


    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_screen_layout);
        imageView = findViewById(R.id.imageView);
        //backgroundIntro = findViewById(R.id.background);
        //backgroundIntro.setBackground(getResources().getDrawable(R.drawable.verbal_ap_start_screen));
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
        Picasso.with(this).load(R.drawable.verbal_ap_start_screen).into(imageView);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerbalAbilityIntroScreen.this, BrainBoosterIntroScreen.class);
                i.putExtra("questionsSection2",meornotmeQuestions);
                i.putExtra("questionsSection3",abilityQuestions);
                i.putExtra("questionsSection4",interestQuestions);
                i.putExtra("questionsSection5",egogramQuestions);
                i.putExtra("questions",questions);
                startActivity(i);
                finish();
            }
        });
        startSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(VerbalAbilityIntroScreen.this,VerbalAbilityGame.class);
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
        Intent i=new Intent(VerbalAbilityIntroScreen.this,LifeChoicesSection.class);
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
        startActivity(i);
        finish();


    }
}
