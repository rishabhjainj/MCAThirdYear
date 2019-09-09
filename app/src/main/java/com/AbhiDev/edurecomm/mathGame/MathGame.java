package com.AbhiDev.edurecomm.mathGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.wireout.Activities.Analysis.InterstSection;
import com.wireout.Activities.Analysis.SectionEndActivity;

import com.wireout.Activities.Analysis.YourEgogramSection;
import com.wireout.Activities.BaseActivity;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.R;
import com.wireout.common.PrefManager;
import com.wireout.models.career_analysis.BooleanQuestion;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerView;

public class MathGame extends BaseActivity implements MathGameViewAction, LinearTimer.TimerListener {

    TextView operand1, operand2, operator,equalsign;
    TextView nextButton;
    EditText answer;
    MathGamePresenter presenter;
    private LinearTimerView linearTimerView;
    private LinearTimer linearTimer;
    private TextView time;
    private long duration = 50 * 1000;
    String mcorrect,mincorrect;
    OnEntityReceivedListener<CareerAnalysis> listener;
    CardView skipButton;
    TextView exitSection;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,questions;

    @Override
    public void onBackPressed() {

            showMessage("Back button has been disabled for analysis purpose.");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        listener = new OnEntityReceivedListener<CareerAnalysis>(this) {
            @Override
            public void onReceived(CareerAnalysis entity) {
                 //showMessage("saved");
            }
        };
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
//        exitSection = findViewById(R.id.endbtn);
//        exitSection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MathGame.this, AnalysisStatusActivity.class));
//                finish();
//            }
//        });

        linearTimerView = (LinearTimerView) findViewById(R.id.linearTimer);
        time = (TextView) findViewById(R.id.time);
        operand1 = (TextView) findViewById(R.id.operand1);
        operand2 = (TextView) findViewById(R.id.operand2);
        operator = (TextView) findViewById(R.id.operator);
        equalsign = (TextView) findViewById(R.id.equalsSign);

//        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/KBAStitchInTime.ttf");
//        operand1.setTypeface(custom_font);
//        operand2.setTypeface(custom_font);
//        operator.setTypeface(custom_font);
//        equalsign.setTypeface(custom_font);

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

        presenter = new MathGamePresenter(this, new PrefManager(this));
        initViews();
        presenter.nextProblem();
    }

    void initViews()
    {

        answer = (EditText) findViewById(R.id.answer);
        nextButton = (TextView) findViewById(R.id.nextButton);
        skipButton = (CardView) findViewById(R.id.skipButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.submitAnswer(answer.getText().toString());
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.skipProblem();
            }
        });

    }

    @Override
    public void setProblem(String operand1, String operand2, String operator) {
        this.operand1.setText(operand1);
        this.operand2.setText(operand2);
        this.operator.setText(operator);
        this.answer.setText("");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResults(String correct, String incorrect) {

        mcorrect=correct;
        mincorrect=incorrect;

    }


    @Override
    public void updateTimer(String time) {
        TextView timer = (TextView) findViewById(R.id.timer);
        if(timer != null)
            timer.setText(time + " seconds left!");
    }

    @Override
    public void animationComplete() {

        Intent i = new Intent(MathGame.this, SectionEndActivity.class);
        i.putExtra("section","10");
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
        Map<String,String> map = new HashMap<>();
        map.put("section11",presenter.correctResponses+"");
        //showMessage("correct"+presenter.correct);
        presenter.postAnalysis(map, listener);
        startActivity(i);
//        setContentView(R.layout.game3_game_over_screen);
//       // ((TextView) findViewById(R.id.result)).setText("You answered " + mcorrect + " questions correctly and " + mincorrect + " incorrectly!");
//
//        Button b = (Button) findViewById(R.id.start_next_game);
//        b.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(MathGame.this, game_no_5.class);
////                startActivity(i);
//            }
//        });
    }

    @Override
    public void timerTick(long tickUpdateInMillis) {

        Log.i("Time left", String.valueOf(tickUpdateInMillis));

        String formattedTime = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(tickUpdateInMillis),
                TimeUnit.MILLISECONDS.toSeconds(tickUpdateInMillis)
                        - TimeUnit.MINUTES
                        .toSeconds(TimeUnit.MILLISECONDS.toHours(tickUpdateInMillis)));

        time.setText(formattedTime);

    }

    @Override
    public void onTimerReset() {
        time.setText("");

    }
}
