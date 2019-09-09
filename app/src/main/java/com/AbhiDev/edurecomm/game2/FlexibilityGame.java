package com.AbhiDev.edurecomm.game2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wireout.Activities.Analysis.SectionEndActivity;

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

public class FlexibilityGame extends BaseActivity implements Game3ViewAction,LinearTimer.TimerListener{

    Button startButton;
    CardView yesButton, noButton, skipButton;
    TextView leftTextView, rightTextView;
    TextView gameName , gameInstruction;
    Intent intent;
    TextView exitSection;
    Game3Presenter presenter;
    PrefManager prefManager;
    private LinearTimerView linearTimerView;
    private LinearTimer linearTimer;
    OnEntityReceivedListener<CareerAnalysis> lifeChoicesListener;
    private TextView time;
    private long duration = 50 * 1000;

    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions,questions;
    HashMap<String, Integer> colorMap = new HashMap<>();


    @Override
    public void onBackPressed() {

        showMessage("Back button has been disabled for analysis purpose.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game3_play_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lifeChoicesListener = new OnEntityReceivedListener<CareerAnalysis>(this) {
            @Override
            public void onReceived(CareerAnalysis entity) {
               // showMessage("saved");
            }
        };
        intent = getIntent();
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

        linearTimerView = (LinearTimerView) findViewById(R.id.linear);
        time = (TextView) findViewById(R.id.timely);

        linearTimer = new LinearTimer.Builder()
                .linearTimerView(linearTimerView)
                .duration(duration)
                .timerListener(this)
                .getCountUpdate(LinearTimer.COUNT_DOWN_TIMER, 1000)
                .build();

        try {
            linearTimer.startTimer();
        }

        catch (IllegalStateException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        prefManager = new PrefManager(this);
        presenter = new Game3Presenter(this, prefManager);

        colorMap.put("black", R.color.black);
        colorMap.put("blue", R.color.blue);
        colorMap.put("green", R.color.green);
        colorMap.put("gray", R.color.gray);
        colorMap.put("purple", R.color.purple);
        colorMap.put("red", R.color.red);
        colorMap.put("yellow", R.color.yellow);
        colorMap.put("pink", R.color.pink);
        colorMap.put("brown", R.color.brown);

        presenter.start();
    }
    @Override
    public void showGameScreen(Game3Level currLevel)
    {



        leftTextView = (TextView) findViewById(R.id.left);
        rightTextView = (TextView) findViewById(R.id.right);
        yesButton = (CardView) findViewById(R.id.yes);
        noButton = (CardView) findViewById(R.id.no);
        skipButton = (CardView) findViewById(R.id.skipButton3);
        leftTextView.setText(currLevel.getLeftText());
        leftTextView.setTextColor(ContextCompat.getColor(this, colorMap.get(currLevel.getLeftColor())));
        rightTextView.setText(currLevel.getRightText());
        rightTextView.setTextColor(ContextCompat.getColor(this, colorMap.get(currLevel.getRightColor())));

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.submitAndNextMove(true);
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.submitAndNextMove(false);
            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.skip();
            }
        });
//        skipButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.nextMove(); //skip without any user response
//            }
//        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateTimer(String time) {
        TextView timer = (TextView) findViewById(R.id.timer);
        if(timer != null)
            timer.setText(time + " seconds left!");
    }
    @Override
    public void gameOver(String correct, String incorrect) {
//        setContentView(R.layout.game3_game_over_screen);
//       // ((TextView) findViewById(R.id.gameResult)).setText("You answered " + correct + " problems correctly and " + incorrect + " incorrectly!");
//        Button start=(Button)findViewById(R.id.game3_start);
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(FlexibilityGame.this, VerbalAbilityIntroScreen.class);
//                startActivity(i);
           // }
       // });

                Intent i = new Intent(FlexibilityGame.this, SectionEndActivity.class);
            i.putExtra("questionsSection2",meornotmeQuestions);
            i.putExtra("questionsSection3",abilityQuestions);
            i.putExtra("questionsSection4",interestQuestions);
            i.putExtra("questionsSection5",egogramQuestions);
            i.putExtra("questions",questions);
            i.putExtra("section","9");
            Map<String,String> map = new HashMap<>();
            map.put("section10",presenter.correct+"");
            //showMessage("correct"+presenter.correct);
            presenter.postAnalysis(map, lifeChoicesListener);
            startActivity(i);

    }

    @Override
    public void animationComplete() {
       gameOver("","");
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
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
