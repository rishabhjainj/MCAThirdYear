package com.AbhiDev.edurecomm.Activities.Analysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.squareup.picasso.Picasso;
import com.wireout.Exams.TestResponse;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.listeners.AnalysisEventListener;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.mathGame.GameIntro;
import com.wireout.models.QuestionResponse;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.models.exams.Question;
import com.wireout.presenters.AnalysisPresenter;

import java.util.ArrayList;
import java.util.List;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerView;

public class LogicalReasoningSection extends AnalysisActivity implements AnalysisEventListener, CommSectionFragment.OnDataPass,LinearTimer.TimerListener {
    AnalysisPresenter presenter;
    public static final String LOGICALSECTIONCOMPLETED = "Logical_Section_Completed";
    ImageView imageView,backgroundImage;
    int count =0;
    float increaseFactor;
    ArrayList<String> section5;
    TextView tooltitle;
    public static final String LOGICALSCORE = "Logical_Reasoning_Score";
    String formattedTime;
    ArrayList<String> questionsList;
    ArrayList<QuestionResponse> responseList;
    ArrayList<String> questionResponseArrayList;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions,questions;

    List<QuestionModel> questionModels;

    Button next;
    TextView questNo;
    RoundCornerProgressBar progressBar;
    ImageView exitBtn;
    OnEntityReceivedListener<TestResponse> listener;
    private long duration = 30*60 * 1000;
    TextView time;
    int countt=0;
    QuestionModel model1,model2,model3,model4,model5,model6,model7,model8,model9,model10;
    private LinearTimerView linearTimerView;
    private LinearTimer linearTimer;
    int flag=0;
    String currSolution="";
    com.wireout.Exams.QuestionResponse response;
    int front=0;
    int last=0;
    Boolean nextDiff=false;
    Boolean diff=true;
    ArrayList<Question> resultQuestions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getSerializableExtra("questionsSection2")!=null)
            meornotmeQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection2");
        if(getIntent().getSerializableExtra("questionsSection3")!=null)
            abilityQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection3");
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
        presenter = new AnalysisPresenter(this);
        section5 = new ArrayList<>();
        responseList = new ArrayList<>();
        questionsList = new ArrayList<>();
        questionModels = new ArrayList<>();
        model1 = new QuestionModel();
        model2 = new QuestionModel();
        model3 = new QuestionModel();
        model4 = new QuestionModel();
        model5 = new QuestionModel();
        model6 = new QuestionModel();
        model7 = new QuestionModel();
        model8 = new QuestionModel();
        model9 = new QuestionModel();
        model10 = new QuestionModel();


        model1.setQuestionText("The difference of two numbers is 5 and the difference of their squares is 135. The sum of the numbers is:");
        model1.setOption1("27");
        model1.setOption2("25");
        model1.setOption3("30");
        model1.setOption4("32");
        model1.setCorrectAnswer("27");

        model2.setQuestionText("By selling 33 metres of cloth , one gains the selling price of 11 metres . Find the gain percent");
        model2.setOption1("33.3");
        model2.setOption2("50");
        model2.setOption3("25");
        model2.setOption4("16.6");
        model2.setCorrectAnswer("50");

        model3.setQuestionText("In a certain code, ‘LAKE’ is written as ‘MZLD’. How will ‘BACK’ be written in that same code?");
        model3.setOption1("ZYXP");
        model3.setOption2("CDJZ");
        model3.setOption3("CZDJ");
        model3.setOption4("CZDJ");
        model3.setCorrectAnswer("CZDJ");

        model4.setQuestionText("A person incurs loss for by selling a watch for Rs.1140.At what price should the watch be sold to earn a 5% profit?");
        model4.setOption1("1195");
        model4.setOption2("1200");
        model4.setOption3("1100");
        model4.setOption4("1260");
        model4.setCorrectAnswer("1260");

        model5.setQuestionText("April 4 is a Sunday. Then July 11 of that same year will be");
        model5.setOption1("Wednesday");
        model5.setOption2("Tuesday");
        model5.setOption3("Sunday");
        model5.setOption4("Monday");
        model5.setCorrectAnswer("Sunday");

        model6.setQuestionText("Three fourth of one-fifth of a number is 60. Then 15% of the number is");
        model6.setOption1("400");
        model6.setOption2("60");
        model6.setOption3("450");
        model6.setOption4("1200");
        model6.setCorrectAnswer("60");

        model7.setQuestionText("6 persons went to a hotel for taking their meal. 5 of them spent Rs.10 each on their meals and the 6th spent Rs.5 more than the average expenditure of all the six. What was the total money spent by them");
        model7.setOption1("33");
        model7.setOption2("36");
        model7.setOption3("66");
        model7.setOption4("45");
        model7.setCorrectAnswer("66");

        model8.setQuestionText("24 is divided into two parts such that seven times the first part added to five times the second part makes 146. The first part is");
        model8.setOption1("11");
        model8.setOption2("13");
        model8.setOption3("16");
        model8.setOption4("17");
        model8.setCorrectAnswer("13");

        model9.setQuestionText("A positive number when decreased by 4, is equal to 21 times the reciprocal of the number. The number is");
        model9.setOption1("3");
        model9.setOption2("5");
        model9.setOption3("7");
        model9.setOption4("9");
        model9.setCorrectAnswer("7");

        model10.setQuestionText("Choose the correct alternative from the given ones that will complete the series. ADG, XVT, BEH, WUS, ?");
        model10.setOption1("VTR");
        model10.setOption2("CFI");
        model10.setOption3("DFJ");
        model10.setOption4("STU");
        model10.setCorrectAnswer("VTR");

        questionModels.add(model1);
        questionModels.add(model2);
        questionModels.add(model3);
        questionModels.add(model4);
        questionModels.add(model5);
        questionModels.add(model6);
        questionModels.add(model7);
        questionModels.add(model8);
        questionModels.add(model9);
        questionModels.add(model10);


        questionResponseArrayList = new ArrayList<>();



        if(getIntent().getSerializableExtra("questionsSection3")!=null)
            abilityQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection3");
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
        showCommSkillIntroScreen();

    }

    @Override
    public void onDataPass(String data) {
        while(questionResponseArrayList.size()<countt){
            questionResponseArrayList.add("none");
        }
        questionResponseArrayList.add(countt,data);
    }

    public void exitSectionButton(){
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogicalReasoningSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }



    public void showCommSkillIntroScreen() {
        count=0;
        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.motivational_start_screen));
        backgroundImage = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.logical_reasoning_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogicalReasoningSection.this, HandwritingSection.class);
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
                showCommSkillQuestionScreen();
            }
        });
    }
    public void showCommSkillQuestionScreen() {
//        setContentView(R.layout.questions_layout);
//        agreeDisagreeFunc();
//        changeToolbar();
//        final TextView questionTextView = findViewById(R.id.quetxtview);
//        final int noOfQuestions = 10;
//        Picasso.with(getApplicationContext()).load(R.drawable.ic_agree).into(agreeButton);
//        Picasso.with(getApplicationContext()).load(R.drawable.ic_disagree).into(disagreeButton);
//        float value = (float)100.0/noOfQuestions;
//        toolTitle = findViewById(R.id.tooltilte);
//        toolTitle.setText("Motivational Quotient");
//        sectionImage = findViewById(R.id.icon);
//        Picasso.with(this).load(R.drawable.ic_motivationquotient).into(sectionImage);
//        increaseFactor =Math.round(value);
//        next = (Button)findViewById(R.id.next1);

//        //imageView = findViewById(R.id.image);
//        Log.d("sectionNamesno",noOfQuestions+",value"+value+",increase"+increaseFactor);
//        exitSectionButton();
//        questionTextView.setText(questions.getQuestions().get(count).getText());
//        setProgressBarData(Math.round(increaseFactor*count));
//        skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                count++;
//                Picasso.with(getApplicationContext()).load(R.drawable.ic_agree).into(agreeButton);
//                Picasso.with(getApplicationContext()).load(R.drawable.ic_disagree).into(disagreeButton);
//                if(count<noOfQuestions){
//                    questionTextView.setText(questions.getQuestions().get(count).getText());
//                    setProgressBarData(Math.round(increaseFactor*count));
//                    BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
//                    questionResponse.setQuestion(questions.getQuestions().get(count).getId());
//                    questionResponseArrayList.add(questionResponse);
//                    Log.d("sectioncount",count+"");
//
//                }
////
//                else{
//                    showMotivationalEndScreen();
//                }
//            }
//        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                count++;
//                Picasso.with(getApplicationContext()).load(R.drawable.ic_agree).into(agreeButton);
//                Picasso.with(getApplicationContext()).load(R.drawable.ic_disagree).into(disagreeButton);
//                if(count<noOfQuestions){
//                    questionTextView.setText(questions.getQuestions().get(count).getText());
//                    setProgressBarData(Math.round(increaseFactor*count));
//                    BooleanQuestionResponse questionResponse = new BooleanQuestionResponse();
//                    questionResponse.setQuestion(questions.getQuestions().get(count).getId());
//                    questionResponseArrayList.add(questionResponse);
//                    Log.d("sectioncount",count+"");
//
//                }
////
//                else{
//                    section5.setSection(3);
//                    section5.setAnswers(questionResponseArrayList);
//                    section5.submit(listener);
//                    showMotivationalEndScreen();
//                }
//            }
//        });
        setContentView(R.layout.activity_exam_question_screen);
        skip = (Button)findViewById(R.id.skip1);
        tooltitle = findViewById(R.id.tooltilte);
        tooltitle.setText("Logical Reasoning");
        questNo = findViewById(R.id.quesno);
        exitBtn = findViewById(R.id.endbtn);
        responseList = new ArrayList<>();
        progressBar = findViewById(R.id.progress_1);
        progressBar.setProgressColor(getResources().getColor(R.color.green_analysis));
        progressBar.setProgressBackgroundColor(getResources().getColor(R.color.light_grey));
        progressBar.setMax(100);
        resultQuestions = new ArrayList<>();
        //change and remove adaptive here
        duration =5*60000;
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar.setProgress((100f/questionsList.size()));
        questNo.setText(countt+1+"/"+questionsList.size());




        diff=false;

        showTextFragmentOption(model1);
        next = findViewById(R.id.next1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countt < questionModels.size() - 1) {
                    countt++;
                    questNo.setText(countt + 1 + "/" + questionModels.size());
                    progressBar.setProgress((100f / questionModels.size()) * (countt + 1));
                    showTextFragmentOption(questionModels.get(countt));

                } else {
                    while(questionResponseArrayList.size()<10){
                        questionResponseArrayList.add("none");
                    }
                    int score = calculateScore();
                    //showMessage(score+"");
                    MyApplication.getInstance().prefManager.saveString(LOGICALSCORE,score+"");
                    showCommSkillEndScreen();
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countt < questionModels.size() - 1) {
                    countt++;
                    questNo.setText(countt + 1 + "/" + questionModels.size());
                    progressBar.setProgress((100f / questionModels.size()) * (countt + 1));
                    showTextFragmentOption(questionModels.get(countt));

                } else {
                    while(questionResponseArrayList.size()<10){
                        questionResponseArrayList.add("none");
                    }
                    int score = calculateScore();
                    //showMessage(score+"");
                    MyApplication.getInstance().prefManager.saveString(LOGICALSCORE,score+"");
                    showCommSkillEndScreen();
                }
            }
        });
        linearTimerView = (LinearTimerView) findViewById(R.id.linearTimer);
        time = (TextView) findViewById(R.id.time);
        linearTimer = new LinearTimer.Builder()
                .linearTimerView(linearTimerView)
                .duration(duration)
                .timerListener(this)
                .getCountUpdate(LinearTimer.COUNT_DOWN_TIMER, 1000)
                .build();

        try {
            linearTimer.startTimer();


        } catch (IllegalStateException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void showTextFragmentOption(QuestionModel model){
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        Fragment fragment = CommSectionFragment.newInstance(model);
        mTransactiont.replace(R.id.main_container, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }
    public void showCommSkillEndScreen(){
        while(questionResponseArrayList.size()<10){
            questionResponseArrayList.add("none");
        }
        setContentView(R.layout.analysis_section_end_screen);
        TextView sectionName;
        TextView sectionDuration;
        ImageView exitSection;
        final ImageView continueSection;
        Log.d("responselist",questionResponseArrayList+"");
        sectionDuration = findViewById(R.id.duration);
        sectionName = findViewById(R.id.section_name);
        sectionImage = findViewById(R.id.section_image);
        Picasso.with(this).load(R.drawable.ic_handwriting).into(sectionImage);
        exitSection = findViewById(R.id.exit);
        continueSection = findViewById(R.id.continue_next);
        sectionDuration.setText("30 sec");
        sectionName.setText("Handwriting Section");
        MyApplication.getInstance().prefManager.saveString(LOGICALSECTIONCOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"starting Flexibility Game",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LogicalReasoningSection.this, HandwritingSection.class);
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
                startActivity(new Intent(LogicalReasoningSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });

    }

    public int calculateScore(){
        int i = 0;
        int correct=0;
        for(String answer: questionResponseArrayList){
            if(answer.equals(questionModels.get(i).getCorrectAnswer())){
                correct++;
            }
            i++;
        }
        return correct;
    }
    @Override
    public void onBackPressed() {

        if(count==0){
            Intent intent = new Intent(LogicalReasoningSection.this, CommSkillSection.class);
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
    public void timerTick(long tickUpdateInMillis) {

        Log.i("Time left", String.valueOf(tickUpdateInMillis));


        long minutes = (tickUpdateInMillis / 1000)  / 60;
        int seconds = (int)((tickUpdateInMillis / 1000) % 60);

        if(seconds<10)
            formattedTime = minutes+":0"+seconds;
        else
            formattedTime = minutes+":"+seconds;
        //showMessage(formattedTime);
        time.setText(formattedTime);

    }
    @Override
    public void onTimerReset() {
        time.setText("");

    }
    @Override
    public void animationComplete() {
        // showMessage(count+1+"="+exam.getQuestions().size());

        showCommSkillEndScreen();

        //showMessage(count+"");
    }
}
