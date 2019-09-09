package com.AbhiDev.edurecomm.Activities.Analysis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.fourpicsoneword.VerbalAbilityIntroScreen;
import com.wireout.listeners.AnalysisEventListener;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.presenters.AnalysisPresenter;

import java.util.HashMap;
import java.util.Map;

public class LifeChoicesSection extends AnalysisActivity implements AnalysisEventListener {
    AnalysisPresenter presenter;
    int layoutWidth ,layoutHeight;
    int p[]=new int [3];
    String pstring[]=new String[3];
    int pindex=0;
    OnEntityReceivedListener<CareerAnalysis> lifeChoicesListener;
    String lifeChoicesSelections;
    ImageView backgroundImage;
    public static final String LIFECHOICESSECTIONCOMPLETED="Life_Choices_Section_Completed";
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,questions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AnalysisPresenter(this);
        presenter.startLifeChoices();
        lifeChoicesListener = new OnEntityReceivedListener<CareerAnalysis>(this) {
            @Override
            public void onReceived(CareerAnalysis entity) {
                //initUi(entity);
                //showMessage("saved");
            }
        };
        if(getIntent().getSerializableExtra("questionsSection2")!=null) {
            meornotmeQuestions = (BooleanQuestion) getIntent().getSerializableExtra("questionsSection2");
        }
        else{
            showMessage("null");
        }
        if(getIntent().getSerializableExtra("questionsSection3")!=null) {
            abilityQuestions = (BooleanQuestion) getIntent().getSerializableExtra("questionsSection3");
        }
        else{
            showMessage("null");
        }
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
        layoutHeight = displayMetrics.widthPixels/4;
        layoutWidth = displayMetrics.widthPixels/4;

    }
    public void exitSectionButton() {
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LifeChoicesSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //presenter.previous();
        if(count==0){
            Intent intent = new Intent(LifeChoicesSection.this, YourEgogramSection.class);
            intent.putExtra("questionsSection2",meornotmeQuestions);
            intent.putExtra("questionsSection3",abilityQuestions);
            intent.putExtra("questionsSection4",interestQuestions);
            intent.putExtra("questionsSection5",egogramQuestions);
            intent.putExtra("questions",questions);
            startActivity(intent);
            finish();
        }
        else
        showMessage("Back button has been disabled for analysis purpose.");
    }
    @Override
    public void showLifeChoicesIntroScreen() {
        count=0;
        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.lifechoices_start_screen));
        backgroundImage = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.lifechoices_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LifeChoicesSection.this,VerbalAbilityIntroScreen.class);
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
                showLifeChoicesScreen();
            }
        });
    }
    @Override
    public void showLifeChoicesScreen() {
        count=1;
        setContentView(R.layout.life_choices_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        exitSectionButton();
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLifeChoicesSectionEndScreen();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(f1,1);
                check(f2,2);
                check(f3,3);
                check(f4,4);
                check(f5,5);
                check(f6,6);
                check(f7,7);
                check(f8,8);
                check(f9,9);

                //showMessage(p[0]+","+p[1]+","+p[2]);
                inttostring(p[0],0);
                inttostring(p[1],1);
                inttostring(p[2],2);

                lifeChoicesSelections = new String();
                lifeChoicesSelections= pstring[0]+","+pstring[1]+","+pstring[2];

               // showMessage(lifeChoicesSelections);
                Map<String,String> map = new HashMap<>();
                map.put("section6",lifeChoicesSelections);
                presenter.postAnalysis(map,lifeChoicesListener);

                p=new int [3];
                pstring=new String[3];
                pindex=0;
                showLifeChoicesSectionEndScreen();
            }
        });

        initialize();

        setHeightAndWidth();
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1=myfun(f1,r1,1);
            }
        });

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f2=myfun(f2,r2,2);
            }
        });

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f3=myfun(f3,r3,3);
            }
        });

        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f4=myfun(f4,r4,4);
            }
        });

        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f5=myfun(f5,r5,5);
            }
        });

        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f6=myfun(f6,r6,6);
            }
        });

        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f7=myfun(f7,r7,7);
            }
        });

        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f8=myfun(f8,r8,8);
            }
        });


        final String[] text = {" "};

        e9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                f9=myfun(f9,r9,9);
                AlertDialog.Builder builder = new AlertDialog.Builder(LifeChoicesSection.this);
                builder.setTitle("Tell your preferences ?");
                // I'm using fragment here so I'm using getView() to provide ViewGroup
                // but you can provide here any other instance of ViewGroup from your Fragment / Activity
                View viewInflated = LayoutInflater.from(LifeChoicesSection.this).inflate(R.layout.text_box_dialog, (ViewGroup) findViewById(android.R.id.content), false);
                final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                builder.setView(viewInflated);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        text[0] = input.getText().toString();

                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }
    public void inttostring(int p,int l)
    {
        if(p==1)
        {
            pstring[l]="Parent";
        }
        else if(p==2)
        {
            pstring[l]="Siblings";
        }
        else if(p==3)
        {
            pstring[l]="Relatives";
        }
        else if(p==4)
        {
            pstring[l]="Adventure";
        }
        else if(p==5)
        {
            pstring[l]="Chocolates";
        }
        else if(p==6)
        {
            pstring[l]="Money";
        }
        else if(p==7)
        {
            pstring[l]="Honesty";
        }
        else if(p==8)
        {
            pstring[l]="Ambition";
        }
        else if(p==9)
        {
            pstring[l]="Siblings";
        }



    }
    public void setHeightAndWidth(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(layoutWidth,
                layoutHeight); // or set height to any fixed value you want

        r1.setLayoutParams(lp);
        r2.setLayoutParams(lp);
        r3.setLayoutParams(lp);
        r4.setLayoutParams(lp);
        r5.setLayoutParams(lp);
        r6.setLayoutParams(lp);
        r7.setLayoutParams(lp);
        r8.setLayoutParams(lp);
        r9.setLayoutParams(lp);


    }
    public void check(int f,int index)
    {
        if(f==1) {
            p[pindex++] = index;
        }
        //    return 0;
    }
    public void showLifeChoicesSectionEndScreen(){
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
        Picasso.with(this).load(R.drawable.ic_verbalaptitude).into(sectionImage);
        sectionDuration.setText("30 sec");
        sectionName.setText("Verbal Aptitude");
        MyApplication.getInstance().prefManager.saveString(LIFECHOICESSECTIONCOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),"starting Verbal aptitude",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(LifeChoicesSection.this,VerbalAbilityIntroScreen.class);
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
                startActivity(new Intent(LifeChoicesSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
}
