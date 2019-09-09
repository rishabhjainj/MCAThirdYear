package com.AbhiDev.edurecomm.Exams.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.wireout.Activities.BaseActivity;
import com.wireout.Exams.ExamQuestionModel;
import com.wireout.Exams.Fragment.CheckboxFragment;
import com.wireout.Exams.Fragment.OptionsFragment;
import com.wireout.Exams.Fragment.OptionsNonMathFragment;
import com.wireout.Exams.Fragment.YesNoQuestionFragment;
import com.wireout.Exams.QuestionResponse;
import com.wireout.Exams.TestResponse;
import com.wireout.Fragments.AptitudeFragment;
import com.wireout.Fragments.ExploreFragment;
import com.wireout.Fragments.SearchFragment;
import com.wireout.R;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.BooleanSectionResponse;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.models.exams.Question;

import junit.framework.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerView;

public class ExamQuestionScreen extends BaseActivity implements LinearTimer.TimerListener ,OptionsFragment.OnDataPass,CheckboxFragment.OnDataPass {

    Button next;
    String formattedTime;
    TextView tooltitle;
    TextView questNo;
    RoundCornerProgressBar progressBar;
    ViewStub stub;
    ImageView exitBtn;
    String classCode="";
    OnEntityReceivedListener<TestResponse> listener;
    LinearLayout l1;
    private long duration = 30*60 * 1000;
    TextView time;
     int count=0;
    private LinearTimerView linearTimerView;
    ExamSubCategory exam;
    List<Question> questions;
    List<QuestionResponse> responseList;
    private LinearTimer linearTimer;
    int flag=0;
    int adapQuesCount=0;
    String currSolution="";
    QuestionResponse response;
    int front=0;
    int last=0;
    Boolean nextDiff=false;
    Boolean diff=true;
    ArrayList<Question> adaptiveQuestions;
    ArrayList<Question> resultQuestions;
    @Override
    public void onDataPass(String data) {
        //showMessage(data+count);
        flag=1;
        currSolution=data;
        if(!exam.getQuestionOrdering().equals("adaptive")) {
            response = new QuestionResponse();
            response.setQuestion(questions.get(count).getId());
            response.setAnswer(data);
            responseList.add(count, response);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Your Class Code:");

        adaptiveQuestions = new ArrayList<>();
        final EditText input = new EditText(this);
       // input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.T);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                classCode = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        listener = new OnEntityReceivedListener<TestResponse>(this) {
            @Override
            public void onReceived(TestResponse entity) {
                showMessage("saved");
            }
        };
        setContentView(R.layout.activity_exam_question_screen);

        questNo = findViewById(R.id.quesno);
        exitBtn = findViewById(R.id.endbtn);
        responseList = new ArrayList<>();
        progressBar = findViewById(R.id.progress_1);
        progressBar.setProgressColor(getResources().getColor(R.color.green_analysis));
        progressBar.setProgressBackgroundColor(getResources().getColor(R.color.light_grey));
        progressBar.setMax(100);
        questions= new ArrayList<>();
        resultQuestions = new ArrayList<>();
        exam = (ExamSubCategory) getIntent().getSerializableExtra("exam");
        //change and remove adaptive here
        changeQuestionOrdering(exam.getQuestionOrdering());
        questions = exam.getQuestions();
        if(exam.getTime()!=null)
        duration =exam.getTime()*60000;
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar.setProgress((100f/questions.size()));
        questNo.setText(count+1+"/"+questions.size());




        diff=false;
        displayView(questions.get(count).getQuestionType(),questions.get(count));
        resultQuestions.add(questions.get(0));

        next = findViewById(R.id.next1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exam.getQuestionOrdering().equals("adaptive")) {

                    if(flag==1){
                        if(diff){
                            if(currSolution.equals(exam.getQuestions().get(last).getCorrectAnswer())){
                               // showMessage("correct");
                                //now show the que with high difficulty
                                nextDiff=true;

                            }
                            else{
                                nextDiff=false;
                              //  showMessage("wrong");
                            }
                        }
                        else{
                            if(currSolution.equals(exam.getQuestions().get(front).getCorrectAnswer())){
                              //  showMessage("correct");
                                //now show the que with high difficulty
                                nextDiff=true;

                            }
                            else{
                                nextDiff=false;
                              //  showMessage("wrong");
                            }
                        }
                        response = new QuestionResponse();
                        if(diff) {
                            response.setQuestion(adaptiveQuestions.get(last).getId());
                            response.setAnswer(currSolution);
                            responseList.add(count, response);
                        }
                        else{
                            response.setQuestion(adaptiveQuestions.get(front).getId());
                            response.setAnswer(currSolution);
                            responseList.add(count, response);
                        }
                        if(nextDiff){
                            last--;
                            showDifficultQuestion();
                            diff=true;
                        }
                        else{
                            front++;
                            showEasyQuestion();
                            diff=false;
                        }

                    }
                    if (flag == 0) {
                        //showMessage("wrong");
                        response = new QuestionResponse();
                        if(diff){
                            response.setQuestion(adaptiveQuestions.get(last).getId());
                            response.setAnswer("na");
                            responseList.add(count, response);
                            last--;
                            showDifficultQuestion();
                        }
                        else{
                            response.setQuestion(adaptiveQuestions.get(front).getId());
                            response.setAnswer("na");
                            responseList.add(count, response);
                            front++;
                            showEasyQuestion();
                        }
                    }
//                    if (count < questions.size() - 1) {
//                        count++;
//                        flag = 0;
//                        questNo.setText(count + 1 + "/" + questions.size());
//                        progressBar.setProgress((100f / questions.size()) * (count + 1));
//                        displayView(questions.get(count).getQuestionType(), questions.get(count));
//
//                    }
//                    else {
//                        submitData();
//                        if (exam.getShowResult()) {
//                            Intent intent = new Intent(ExamQuestionScreen.this, ExamResultActivity.class);
//                            intent.putExtra("answers", (Serializable) responseList);
//                            intent.putExtra("questions", (Serializable) exam);
//                            TestResponse testResponse = new TestResponse();
//                            testResponse.setTest(exam.getId());
//                            testResponse.setQuestionResponses(responseList);
//                            testResponse.submit(listener);
//                            startActivity(intent);
//                        } else {
//                            showMessage("Test Submitted. Results will be declared soon.");
//                        }
//                    }
                }
                else {
                    if (flag == 0) {
                        response = new QuestionResponse();
                        response.setAnswer("na");
                        response.setQuestion(exam.getQuestions().get(count).getId());
                        responseList.add(count, response);
                    }
                    if (count < questions.size() - 1) {
                        count++;
                        flag = 0;
                        questNo.setText(count + 1 + "/" + questions.size());
                        progressBar.setProgress((100f / questions.size()) * (count + 1));
                        displayView(questions.get(count).getQuestionType(), questions.get(count));

                    } else {
                        submitData();
                        if (exam.getShowResult()) {
                            Intent intent = new Intent(ExamQuestionScreen.this, ExamResultActivity.class);
                            intent.putExtra("answers", (Serializable) responseList);
                            intent.putExtra("questions", (Serializable) exam);
                            TestResponse testResponse = new TestResponse();
                            testResponse.setTest(exam.getId());
                            testResponse.setQuestionResponses(responseList);
                            testResponse.submit(listener);
                            startActivity(intent);
                        } else {
                            showMessage("Test Submitted. Results will be declared soon.");
                        }
                    }
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
    public void showEasyQuestion(){
        if(front!=last){
            count++;
            questNo.setText(count + 1 + "/" + adaptiveQuestions.size());
            progressBar.setProgress((100f / adaptiveQuestions.size()) * (count + 1));
            displayView(adaptiveQuestions.get(front).getQuestionType(), adaptiveQuestions.get(front));
            resultQuestions.add(adaptiveQuestions.get(front));
        }
        else{
            submitAdaptiveResponse();
        }
    }
    public void showDifficultQuestion(){
        if(front!=last){
            count++;
            questNo.setText(count + 1 + "/" + adaptiveQuestions.size());
            progressBar.setProgress((100f / adaptiveQuestions.size()) * (count + 1));
            displayView(adaptiveQuestions.get(last).getQuestionType(), adaptiveQuestions.get(last));
            resultQuestions.add(adaptiveQuestions.get(last));
        }
        else{
            submitAdaptiveResponse();
        }

    }
    public void submitAdaptiveResponse(){
        submitData();
        if (exam.getShowResult()) {
            Intent intent = new Intent(ExamQuestionScreen.this, ExamResultActivity.class);
            intent.putExtra("answers", (Serializable) responseList);
            exam.setQuestions(resultQuestions);
            intent.putExtra("questions", (Serializable) exam);
            TestResponse testResponse = new TestResponse();
            testResponse.setTest(exam.getId());
            testResponse.setQuestionResponses(responseList);
            testResponse.submit(listener);
            startActivity(intent);
        } else {
            showMessage("Test Submitted. Results will be declared soon.");
        }
    }
    public void submitData(){
        TestResponse testResponse = new TestResponse();
        testResponse.setClassCode(classCode);
        testResponse.setTest(exam.getId());
        testResponse.setQuestionResponses(responseList);
        testResponse.submit(listener);
       // showMessage(responseList.size()+"");
    }
    @Override
    public void animationComplete() {
       // showMessage(count+1+"="+exam.getQuestions().size());
        if(count+1<=exam.getQuestions().size()) {
            while (count+1 <= exam.getQuestions().size()) {
                response = new QuestionResponse();
                response.setAnswer("na");
                response.setQuestion(exam.getQuestions().get(count).getId());
                responseList.add(count, response);
                count++;
            }
        }
        submitData();
        Intent intent = new Intent(ExamQuestionScreen.this,ExamResultActivity.class);
        intent.putExtra("answers",(Serializable)responseList);
        intent.putExtra("questions",(Serializable)exam);
        startActivity(intent);
        //showMessage(count+"");
    }

    public void changeQuestionOrdering(String ordering){
        switch(ordering){
            case "default":
               // showMessage("default case");
                break;
            case "random":
                //showMessage("random case shuffling questions");
                Collections.shuffle(exam.getQuestions());
                break;
            case "adaptive":
                //showMessage("adaptive");
                Collections.sort(exam.getQuestions(), new Comparator<Question>(){
                    public int compare(Question o1, Question o2){
                        return o1.getDifficulty() - o2.getDifficulty();

                    }
                });
                adaptiveQuestions.addAll(exam.getQuestions());
                last = adaptiveQuestions.size();
                break;
        }
    }
    public void displayView(String type,Question model) {

        //will display correct question UI based on question type.

       // showMessage(type);
//        TYPE_MCQ_SINGLE = 'mcq_single'
//        TYPE_MCQ_MULTI = 'mcq_multi'
//        TYPE_SHORT_ANS = 'short_ans'
        switch (type) {
            case "mcq_multi":
                //tvTitle.setText(getResources().getString(R.string.signin_tile));
                showFragmentCheckbox( model);

                break;
            case "yes_no":
                showFragmentYesNo(model);
                break;
            case "mcq_single":
                if(!model.getIsMathematical())
                    showTextFragmentOption(model);
                else
                    showFragmentOption(model);
                break;
//            case "end":
//                Intent intent = new Intent(ExamQuestionScreen.this,ExamResultActivity.class);
//                intent.putExtra("questions",(Serializable)questions);
//                startActivity(intent);
//                break;

        }
    }
    public void showFragment(Fragment fragment, int position) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }
    public void showFragmentCheckbox( Question model) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        Fragment fragment = CheckboxFragment.newInstance(model);
        mTransactiont.replace(R.id.main_container, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }
    public void showFragmentYesNo( Question model) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        Fragment fragment = YesNoQuestionFragment.newInstance(model);
        mTransactiont.replace(R.id.main_container, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }
    public void showFragmentOption( Question model) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        Fragment fragment = OptionsFragment.newInstance(model);
        mTransactiont.replace(R.id.main_container, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }
    public void showTextFragmentOption(Question model){
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        Fragment fragment = OptionsNonMathFragment.newInstance(model);
        mTransactiont.replace(R.id.main_container, fragment, fragment.getClass().getName());
        mTransactiont.commit();
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
}
