package com.AbhiDev.edurecomm.GameBrainBooster.Levels;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.wireout.Activities.BaseActivity;
import com.wireout.GameBrainBooster.CustomView;
import com.wireout.R;
import com.wireout.models.career_analysis.BooleanQuestion;

public class Level5Activity extends BaseActivity implements View.OnClickListener{

    @Override
    public void onBackPressed() {
        showMessage("Back button has been disabled for analysis purpose.");
    }

    Button b1, b2, b3, b4;
    Drawable wrong, right;
    Intent intent, i;

    ImageView check;
    TextView text;

    CustomView c;

    Typeface t;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition( R.anim.slide_in, R.anim.slide_out );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5);

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
        i = new Intent(this, Level6Activity.class);
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");


        wrong = ContextCompat.getDrawable(this, R.drawable.brain_wrong);
        right = ContextCompat.getDrawable(this, R.drawable.brain_check);

        check = (ImageView) findViewById(R.id.imageView2);

        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);
        b4 = (Button) findViewById(R.id.button5);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);


        text = (TextView) findViewById(R.id.textView2);
        c = (CustomView) findViewById(R.id.custom);

        c.level=8;

        text.setTypeface(t);
        b1.setTypeface(t);
        b2.setTypeface(t);
        b3.setTypeface(t);
        b4.setTypeface(t);



    }

    public void onSkip(View v) {
        if(intent.getExtras().get("flag").equals("true")){
            i.putExtra("flag","true");
        }
        else{
            i.putExtra("flag","false");
        }
        startActivity(i);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button2) {

            life();
            check.setBackground(wrong);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(800, check);

        }

        if (v.getId() == R.id.button3) {

            life();
            check.setBackground(wrong);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(500, check);

        }

        if (v.getId() == R.id.button4) {

            life();
            check.setBackground(wrong);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(500, check);

        }

        if (v.getId() == R.id.button5) {


            CustomView.game8_CorrectlyAnswered++;


            check.setBackground(right);

            check.setVisibility(View.VISIBLE);
            timerDelayRemoveView(500, check);

            if(intent.getExtras().get("flag").equals("true")){
                i.putExtra("flag","true");
            }
            else{
                i.putExtra("flag","false");
            }
            startActivity(i);

        }
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

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.bb_level5));
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
