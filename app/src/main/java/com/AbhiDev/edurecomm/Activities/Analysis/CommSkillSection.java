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
import com.wireout.Exams.Activities.ExamQuestionScreen;
import com.wireout.Exams.Activities.ExamResultActivity;
import com.wireout.Exams.Fragment.OptionsFragment;
import com.wireout.Exams.Fragment.OptionsNonMathFragment;
import com.wireout.Exams.TestResponse;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.listeners.AnalysisEventListener;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.mathGame.GameIntro;
import com.wireout.models.QuestionResponse;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.models.career_analysis.BooleanQuestionResponse;
import com.wireout.models.career_analysis.BooleanSectionResponse;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.models.exams.Question;
import com.wireout.presenters.AnalysisPresenter;

import java.util.ArrayList;
import java.util.List;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerView;

public class CommSkillSection extends AnalysisActivity implements AnalysisEventListener, CommSectionFragment.OnDataPass,LinearTimer.TimerListener  {
    //id : 3
    AnalysisPresenter presenter;
    public static final String COMMSKILLSECTIONCOMPLETED = "Comm_Section_Completed";
    ImageView imageView,backgroundImage;
    int count =0;
    float increaseFactor;
    ArrayList<String> section5;
    String formattedTime;
    ArrayList<String> questionsList;
    ArrayList<QuestionResponse> responseList;
    ArrayList<String> questionResponseArrayList;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions,questions;

    List<QuestionModel> questionModels;
    TextView tooltitle;
    Button next;
    TextView questNo;
    RoundCornerProgressBar progressBar;
    ImageView exitBtn;
    OnEntityReceivedListener<TestResponse> listener;
    private long duration = 30*60 * 1000;
    TextView time;
    List<String> correct;
    public static final String COMMSCORE="Communication_Score";
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


//         correct = new ArrayList<>();
//         correct.add("d) Has never eaten");
//        correct.add("d) Has never eaten");
//        correct.add("d) Has never eaten");
//        correct.add("d) Has never eaten");
//        correct.add("d) Has never eaten");
//        correct.add("d) Has never eaten");
//        correct.add("d) Has never eaten");
//        correct.add("d) Has never eaten");
//        correct.add("d) Has never eaten");

        model1.setQuestionText("She _______ (never eaten) an octopus.");
        model1.setOption1("Have not eaten");
        model1.setOption2("Have never eaten");
        model1.setOption3("Has ever eaten");
        model1.setOption4("Has never eaten");
        model1.setCorrectAnswer("Has never eaten");

        model2.setQuestionText("Anita _______ (believe) that for ages.");
        model2.setOption1("Did believed");
        model2.setOption2("Had been believed");
        model2.setOption3("Has believed");
        model2.setOption4("Have believed");
        model2.setCorrectAnswer("Has believed");

        model3.setQuestionText("I am not hungry. I ______ (eat/already).");
        model3.setOption1("Was already eaten");
        model3.setOption2("Have already eaten");
        model3.setOption3("Had already eaten");
        model3.setOption4("Had eaten already");
        model3.setCorrectAnswer("Have already eaten");

        model4.setQuestionText("I ______ (work) here for 3 years now.");
        model4.setOption1("Worked");
        model4.setOption2("Working");
        model4.setOption3("Have been working");
        model4.setOption4("Had been working");
        model4.setCorrectAnswer("Have been working");

        model5.setQuestionText("I cannot remember when was the last time I _____ (be) here.");
        model5.setOption1("Be");
        model5.setOption2("Become");
        model5.setOption3("Was");
        model5.setOption4("Was Being");
        model5.setCorrectAnswer("Was");

        model6.setQuestionText("Find Error: Everyone knows (1)/ that leopard is (2)/ faster than (3)/ all other animals. (4)");
        model6.setOption1("1");
        model6.setOption2("2");
        model6.setOption3("3");
        model6.setOption4("4");
        model6.setCorrectAnswer("1");

        model7.setQuestionText("Find Error: Now days workers are less interested (1)/ in money as such (2)/ and appear to be more concerned (3)/ about opportunities for autonomy and freedom. (4)");
        model7.setOption1("1");
        model7.setOption2("2");
        model7.setOption3("3");
        model7.setOption4("4");
        model7.setCorrectAnswer("1");

        model8.setQuestionText("Find Error: Only very wealthy tourists (1)/can afford (2)/ to stay (3)/ at Imperial Hotel. (4)");
        model8.setOption1("1");
        model8.setOption2("2");
        model8.setOption3("3");
        model8.setOption4("4");
        model8.setCorrectAnswer("4");

        model9.setQuestionText("Find Error: The train (1)/ arrived (2)/ well in time (3)/ Mumbai CST. (4)");
        model9.setOption1("1");
        model9.setOption2("2");
        model9.setOption3("3");
        model9.setOption4("4");
        model9.setCorrectAnswer("4");

        model10.setQuestionText("Find Error: Money (1)/ which is a source of (2)/ the happiness in life (3)/ becomes a source of peril and confusion unless we control it. (4)");
        model10.setOption1("1");
        model10.setOption2("2");
        model10.setOption3("3");
        model10.setOption4("4");
        model10.setCorrectAnswer("3");

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
        //showMessage(countt+","+data);
    }

    public void exitSectionButton(){
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CommSkillSection.this, AnalysisStatusActivity.class));
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
        Picasso.with(this).load(R.drawable.communication_skill_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommSkillSection.this, LogicalReasoningSection.class);
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
        tooltitle.setText("Communication Skills");
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

                        while(questionResponseArrayList.size()<countt) {
                            questionResponseArrayList.add("none");
                        }
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

                    while(questionResponseArrayList.size()<countt) {
                        questionResponseArrayList.add("none");
                    }
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
        int score = calculateScore();
       // showMessage(score+"");
        MyApplication.getInstance().prefManager.saveString(COMMSCORE,score+"");
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
        sectionDuration.setText("5 min");
        sectionName.setText("Logical Reasoning");
        MyApplication.getInstance().prefManager.saveString(COMMSKILLSECTIONCOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"starting Flexibility Game",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CommSkillSection.this, LogicalReasoningSection.class);
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
                startActivity(new Intent(CommSkillSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });

    }

    public int calculateScore(){
        int i = 0;
        int correct=0;
        Log.d("lenghts",questionResponseArrayList.size()+","+questionModels.size());
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
            Intent intent = new Intent(CommSkillSection.this, MotivationalQuotientSection.class);
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
