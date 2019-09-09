package com.AbhiDev.edurecomm.GameBrainBooster.Levels;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wireout.Activities.Analysis.SectionEndActivity;

import com.wireout.Activities.BaseActivity;
import com.wireout.GameBrainBooster.CustomView;
import com.wireout.GameBrainBooster.OrientationManager;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.presenters.BooleanQuestionPresenter;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.models.career_analysis.BooleanQuestion;

import java.util.HashMap;
import java.util.Map;

public class Level8Activity extends BaseActivity implements OrientationManager.OrientationListener {

    BooleanQuestionPresenter presenter;
    OnEntityReceivedListener<CareerAnalysis> lifeChoicesListener;
    @Override
    public void onBackPressed() {
        showMessage("Back button has been disabled for analysis purpose.");
    }

    Drawable wrong, right;
    Intent intent;


    ImageView check;
    ImageButton owl;
    CustomView c;
    public static final String GAME8_CORRECTLY_ANSWERED = "game8_CorrectlyAnswered";
    Typeface t;

    TextView text;
    Intent i;

    int x,y;

    OrientationManager orientationManager;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        intent = getIntent();

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        presenter = new BooleanQuestionPresenter();

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level8);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       // i = new Intent(this, EndActivity.class);

        lifeChoicesListener = new OnEntityReceivedListener<CareerAnalysis>(this) {
            @Override
            public void onReceived(CareerAnalysis entity) {
                //initUi(entity);
               // showMessage("saved");
            }
        };

        x = getResources().getDisplayMetrics().widthPixels;
        y = getResources().getDisplayMetrics().heightPixels;


        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView2);

        owl = (ImageButton) findViewById(R.id.imageButton5);
        owl.setX(2*x);

        c = (CustomView) findViewById(R.id.custom);
        c.level = 13;

        text = (TextView) findViewById(R.id.textView2);
        text.setTypeface(t);

        orientationManager = new OrientationManager(this, SensorManager.SENSOR_DELAY_NORMAL, this);
        orientationManager.enable();
    }

    public void onSkip(View v) {
        orientationManager.disable();
        MyApplication.getInstance().prefManager.saveString(GAME8_CORRECTLY_ANSWERED, CustomView.game8_CorrectlyAnswered + "");
        i = new Intent(Level8Activity.this, SectionEndActivity.class);
        Map<String,String> map = new HashMap<>();
        map.put("section8",CustomView.game8_CorrectlyAnswered + "");
        presenter.postAnalysis(map,lifeChoicesListener);
        i.putExtra("section","7");
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }



    public  void imgClicked(View v){

        orientationManager.disable();

        CustomView.game8_CorrectlyAnswered++;

        check.setBackground(right);

        check.setVisibility(View.VISIBLE);
        timerDelayRemoveView(800, check);

        i = new Intent(Level8Activity.this, SectionEndActivity.class);
        Map<String,String> map = new HashMap<>();
        map.put("section8",CustomView.game8_CorrectlyAnswered + "");
        presenter.postAnalysis(map,lifeChoicesListener);
        i.putExtra("section","7");
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
        startActivity(i);
    }

    @Override
    public void onOrientationChange(OrientationManager.ScreenOrientation screenOrientation) {
        switch(screenOrientation){
            case LANDSCAPE:
                loadmainAniamtion();

                orientationManager.disable();
                break;
        }

    }


    public void loadmainAniamtion() {


        ObjectAnimator textAnimator;

        textAnimator = ObjectAnimator.ofFloat(owl, "x", x+50, x/2-x/7);
        textAnimator.setDuration(1000L);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(textAnimator);
        animatorSet.start();
    }


    public void timerDelayRemoveView(long time, final ImageView img) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                img.setVisibility(View.GONE);
            }
        }, time);
    }

    public  void life(){
        if (c.life>0)
            c.life-=1;
        c.invalidate();
    }

    @Override
    protected void onStop() {
        super.onStop();

        orientationManager.disable();
    }

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.bb_level8));
        System.gc();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

}
