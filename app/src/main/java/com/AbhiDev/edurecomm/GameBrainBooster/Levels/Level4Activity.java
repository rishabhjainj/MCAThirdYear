package com.AbhiDev.edurecomm.GameBrainBooster.Levels;

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


import com.wireout.Activities.BaseActivity;
import com.wireout.GameBrainBooster.CustomView;
import com.wireout.GameBrainBooster.OrientationManager;
import com.wireout.R;
import com.wireout.models.career_analysis.BooleanQuestion;

public class Level4Activity extends BaseActivity implements OrientationManager.OrientationListener {

    @Override
    public void onBackPressed() {
        showMessage("Back button has been disabled for analysis purpose.");
    }

    Drawable wrong, right;

    ImageButton ketchup;

    ImageView check, sauce;
    Intent intent, i;
    CustomView c;

    Typeface t;

    TextView text;

    OrientationManager orientationManager;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);
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

        intent = getIntent();

        i = new Intent(this, Level10Activity.class);
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView3);

        sauce = (ImageView) findViewById(R.id.imageView5);

        ketchup = (ImageButton) findViewById(R.id.imageButton4) ;
        ketchup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life();

                check.setBackground(wrong);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(800, check);





            }
        });

        c = (CustomView) findViewById(R.id.custom);
        c.level = 6;

        text = (TextView) findViewById(R.id.textView2);
        text.setTypeface(t);



        orientationManager = new OrientationManager(getApplicationContext(), SensorManager.SENSOR_DELAY_NORMAL, this);
        orientationManager.enable();

    }

    public void onSkip(View v) {
        orientationManager.disable();
        if(intent.getExtras().get("flag").equals("true")){
            i.putExtra("flag","true");
        }
        else{
            i.putExtra("flag","false");
        }
        startActivity(i);
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
    public void onOrientationChange(OrientationManager.ScreenOrientation screenOrientation) {
        switch(screenOrientation){
            case PORTRAIT:break;
            case REVERSED_PORTRAIT:


                CustomView.game8_CorrectlyAnswered++;

                sauce.setVisibility(View.VISIBLE);

                check.setBackground(right);

                check.setVisibility(View.VISIBLE);
                timerDelayRemoveView(800, check);

                orientationManager.disable();
                if(intent.getExtras().get("flag").equals("true")){
                    i.putExtra("flag","true");
                }
                else{
                    i.putExtra("flag","false");
                }
                startActivity(i);
                break;
            case REVERSED_LANDSCAPE:

                break;
            case LANDSCAPE:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        orientationManager.disable();
    }

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.puzzle5));
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
