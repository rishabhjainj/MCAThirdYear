package com.AbhiDev.edurecomm.Activities.Analysis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wireout.Activities.BrainBoosterIntroScreen;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.models.career_analysis.Section9;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.game2.FlexibilityGameIntro;
import com.wireout.models.Analysis.MCQ;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.presenters.AnalysisPresenter;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;

public class EmotionalIntelligenceSection extends AnalysisActivity {
    AnalysisPresenter presenter;
    Section9 section9;
    String binaryString;
    char[] agreedisagree;
    ImageView backgroundImage;
    char q1,q2,q3,q4,q5,q7,q8,q9,q10;
    OnEntityReceivedListener<Section9> listener;

    public static final String EMOTIONALSECTIONCOMPLETED = "Emotional_Section_Completed";
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,questions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section9 = new Section9(12);
        String s="             ";
        agreedisagree = s.toCharArray();

        presenter = new AnalysisPresenter(this);
        listener= new OnEntityReceivedListener<Section9>(this) {
            @Override
            public void onReceived(Section9 entity) {
                //showMessage("Saved");
            }
        };
        presenter.startEmotional();
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
    }

    public void exitSectionButton(){
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmotionalIntelligenceSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
    @Override
    public void showEmotionalIntroScreen() {
        count=0;
        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.emotionalintelligence_start_screen));
        backgroundImage = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.emotionalintelligence_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EmotionalIntelligenceSection.this, FlexibilityGameIntro.class);
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
                presenter.next();
            }
        });
    }
    @Override
    public void showMoodScreen() {
        count=1;
        // currentMood java class for implementation
        setContentView(R.layout.current_mood_layout);
        next = (Button)findViewById(R.id.next1);
        exitSectionButton();
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showMessage(choice+"");
                switch (choice){
                    case 1:
                        section9.setMood("Happy");
                        break;
                    case 2:
                        section9.setMood("Blushing");
                        break;
                    case 3:
                        section9.setMood("Shy");
                        break;
                    case 4:
                        section9.setMood("Hurt");
                        break;
                    case 5:
                        section9.setMood("Surprised");
                        break;
                    case 6:
                        section9.setMood("Sick");
                        break;
                    case 7:
                        section9.setMood("Devil");
                        break;
                    case 8:
                        section9.setMood("Pleasant");
                        break;
                    case 9:
                        section9.setMood("Sleepy");
                        break;
                }
                //section9.setMood();
                //showMessage(section9.getMood());
               presenter.next();

            }
        });

        initialize();
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(1,0,0,0,0,0,0,0,0);
            }
        });


        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,1,0,0,0,0,0,0,0);
            }
        });

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,1,0,0,0,0,0,0);
            }
        });


        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,1,0,0,0,0,0);
            }
        });


        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,1,0,0,0,0);
            }
        });

        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,0,1,0,0,0);
            }
        });

        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,0,0,1,0,0);
            }
        });

        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,0,0,0,1,0);
            }
        });

        e9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,0,0,0,0,1);
            }
        });


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
    @Override
    public void showQuestion1Screen(MCQ mcq) {
        setContentView(R.layout.questions_layout);
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[0]='1';


            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[0]='0';
            }
        });

        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[0]!='1'&&agreedisagree[0]!='0')
                    agreedisagree[0]=' ';
                presenter.next();
            }
        });
        choices = findViewById(R.id.choices);
        question = (TextView)findViewById(R.id.quetxtview);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[0]!='1'&&agreedisagree[0]!='0')
                    agreedisagree[0]=' ';
                presenter.next();
            }
        });
        question.setText(R.string.quest1);
        exitSectionButton();
        setProgressBarData(8);

//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
    }

    @Override
    public void onBackPressed() {
        if(count==0){
            Intent i = new Intent(EmotionalIntelligenceSection.this, BrainBoosterIntroScreen.class);
            i.putExtra("questionsSection2",meornotmeQuestions);
            i.putExtra("questionsSection3",abilityQuestions);
            i.putExtra("questionsSection4",interestQuestions);
            i.putExtra("questionsSection5",egogramQuestions);
            i.putExtra("questions",questions);
            startActivity(i);
            finish();
        }
        else
        showMessage("Back button has been disabled for analysis purpose.");
    }

    @Override
    public void showQuestion2Screen(MCQ mcq) {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[1]!='1'&&agreedisagree[1]!='0')
                    agreedisagree[1]=' ';
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest2);

        exitSectionButton();
        setProgressBarData(16);
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[1]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[1]='0';
            }
        });

//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showMessage(button.getText()+"");

                if(agreedisagree[1]!='1'&&agreedisagree[1]!='0')
                    agreedisagree[1]=' ';
                presenter.next();

            }
        });
    }

    @Override
    public void showQuestion3Screen(MCQ mcq) {

        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[2]!='1'&&agreedisagree[2]!='0')
                    agreedisagree[2]=' ';
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest3);
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        exitSectionButton();
        setProgressBarData(24);
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[2]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[2]='0';
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                if(agreedisagree[2]!='1'&&agreedisagree[2]!='0')
                    agreedisagree[2]=' ';
                presenter.next();

            }
        });
    }

    @Override
    public void showQuestion4Screen(MCQ mcq) {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[3]!='1'&&agreedisagree[3]!='0')
                    agreedisagree[3]=' ';
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest4);

        exitSectionButton();
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(32);
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[3]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[3]='0';
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                if(agreedisagree[3]!='1'&&agreedisagree[3]!='0')
                    agreedisagree[3]=' ';
                presenter.next();

            }
        });
    }


    @Override
    public void showQuestion5Screen(MCQ mcq) {
        //parent 6 for this

        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[4]!='1'&&agreedisagree[4]!='0')
                    agreedisagree[4]=' ';
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest5);
        exitSectionButton();
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(40);
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[4]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[4]='0';
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                if(agreedisagree[4]!='1'&&agreedisagree[4]!='0')
                    agreedisagree[4]=' ';
                presenter.next();

            }
        });
    }
    @Override
    public void showQuestion6Screen(MCQ mcq) {
        //parent 6 for this

        setContentView(R.layout.checkbox_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest6);
        final ArrayList<CheckBox> checkboxes = new ArrayList<>();

        checkboxes.add((CheckBox) findViewById(R.id.checkBox));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox2));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox3));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox4));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox5));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox6));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox7));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox8));
        exitSectionButton();
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(48);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choicesSelected = "";
                int counter = 0;

                for(CheckBox checkBox : checkboxes){
                    counter++;
                    if(checkBox.isChecked())
                        choicesSelected += counter + ",";
                }

                if(!choicesSelected.equals("")){
                    choicesSelected = choicesSelected.substring(0,choicesSelected.length()-1);
                }

                Log.d("Parent6", choicesSelected);

                if(choicesSelected.equals("")) {
                    section9.setMcq("0");
                }
                else{
                    section9.setMcq(choicesSelected);
                   // showMessage(choicesSelected);
                }
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");

//                }
                presenter.next();

            }
        });
    }
    @Override
    public void showQuestion7Screen(MCQ mcq) {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[5]!='1'&&agreedisagree[5]!='0')
                    agreedisagree[5]=' ';
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest7);
        exitSectionButton();
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(56);
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[5]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[5]='0';
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                if(agreedisagree[5]!='1'&&agreedisagree[5]!='0')
                    agreedisagree[5]=' ';
                presenter.next();

            }
        });
    }
    @Override
    public void showSeekBarScreen(){
        setContentView(R.layout.seekbar_layout);
        final BubbleSeekBar seekBar = (BubbleSeekBar)findViewById(R.id.seek);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.seekbarques);
        exitSectionButton();
        setProgressBarData(64);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
               // showMessage(seekBar.getProgress()+"");
                section9.setFamilyTime(seekBar.getProgress());
                presenter.next();

            }
        });
    }
    @Override
    public void showQuestion8Screen() {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[6]!='1'&&agreedisagree[6]!='0')
                    agreedisagree[6]=' ';
                presenter.next();
            }
        });
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[6]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[6]='0';

            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest8);
        exitSectionButton();
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(72);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                if(agreedisagree[6]!='1'&&agreedisagree[6]!='0')
                    agreedisagree[6]=' ';
                presenter.next();

            }
        });
    }
    @Override
    public void showQuestion9Screen(MCQ mcq) {
        //parent 6 for this

        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[7]!='1'&&agreedisagree[7]!='0')
                    agreedisagree[7]=' ';
                presenter.next();

            }
        });
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[7]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[7]='0';
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest9);
        exitSectionButton();
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(80);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                if(agreedisagree[7]!='1'&&agreedisagree[7]!='0')
                    agreedisagree[7]=' ';
                presenter.next();

            }
        });
    }


    @Override
    public void showQuestion10Screen() {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[8]!='1'&&agreedisagree[8]!='0')
                    agreedisagree[8]=' ';
                presenter.next();
            }
        });
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[8]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[8]='0';
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest10);
        exitSectionButton();
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(88);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                if(agreedisagree[8]!='1'&&agreedisagree[8]!='0')
                    agreedisagree[8]=' ';
                presenter.next();

            }
        });
    }

    @Override
    public void showQuestion11Screen() {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agreedisagree[9]!='1'&&agreedisagree[9]!='0')
                    agreedisagree[9]=' ';
                showEmotionalSectionEndScreen();
            }
        });
        agreeButton = findViewById(R.id.agree_btn);
        disagreeButton = findViewById(R.id.disagree_btn);

        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agreeSelected();
                agreedisagree[9]='1';
            }
        });
        disagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disagreeSelected();
                agreedisagree[9]='0';
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest11);
        exitSectionButton();
//        int genderselected = choices.getCheckedRadioButtonId();
//        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(100);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                if(agreedisagree[9]!='1'&&agreedisagree[9]!='0')
                    agreedisagree[9]=' ';

                for(int i=0; i<agreedisagree.length; i++)
                    binaryString += agreedisagree[i];

               section9.setBooleanResponses(binaryString);
              // showMessage(binaryString);
               Gson gson = new Gson();
               String json = gson.toJson(section9,Section9.class);
               json = json.replace("\u0000", ""); // removes NUL chars
                json = json.replace("\\u0000", "");
                Log.d("removedunicode",json);
                section9 = gson.fromJson(json,Section9.class);
               section9.submit(listener);
               showEmotionalSectionEndScreen();

            }
        });
    }


    public void showEmotionalSectionEndScreen(){
        setContentView(R.layout.analysis_section_end_screen);
        TextView sectionName;
        TextView sectionDuration;
        ImageView exitSection;
        final ImageView continueSection;
        sectionDuration = findViewById(R.id.duration);
        sectionName = findViewById(R.id.section_name);
        sectionImage = findViewById(R.id.section_image);
        Picasso.with(this).load(R.drawable.ic_fexibility).into(sectionImage);
        exitSection = findViewById(R.id.exit);
        continueSection = findViewById(R.id.continue_next);
        sectionDuration.setText("45 sec");
        sectionName.setText("Flexibility Game");
        MyApplication.getInstance().prefManager.saveString(EMOTIONALSECTIONCOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"starting Flexibility Game",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EmotionalIntelligenceSection.this, FlexibilityGameIntro.class);
                i.putExtra("questionsSection2",meornotmeQuestions);
                i.putExtra("questionsSection3",abilityQuestions);
                i.putExtra("questionsSection4",interestQuestions);
                i.putExtra("questionsSection5",egogramQuestions);
                i.putExtra("questions",questions);
                Gson gson  = new Gson();
                String json  = gson.toJson(section9,Section9.class);
               // showMessage(section9.getBooleanResponses());
                startActivity(i);
                finish();
            }
        });
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmotionalIntelligenceSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
}
