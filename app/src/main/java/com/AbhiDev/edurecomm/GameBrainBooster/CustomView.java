package com.AbhiDev.edurecomm.GameBrainBooster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.google.gson.Gson;
import com.wireout.Activities.Analysis.AnalysisActivity;
import com.wireout.Activities.Analysis.SectionEndActivity;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.models.career_analysis.BooleanQuestion;

/**
 * Created by rahul on 13/3/18.
 */

public class CustomView extends View {

    Paint p;
    public int life;
    public int level;
    Typeface t;
    Context context;

    Bitmap hint;

    private int hintflag=0;

    Intent i, i1,intent;

    static boolean analysis;
    BooleanQuestion questions,meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions;

    String[] hints;
    Gson gson;
    String json;

    //////////////////////////// SCORE ///////////////////////
    public static int game8_CorrectlyAnswered=0;
    //////////////////////////////////////////////////////////

    public CustomView(Context context, BooleanQuestion questions,BooleanQuestion meornotmeQuestions,BooleanQuestion abilityQuestions
            ,BooleanQuestion interestQuestions,BooleanQuestion egogramQuestions) {
        super(context);

        this.context=context;
        this.meornotmeQuestions = meornotmeQuestions;
        this.abilityQuestions = abilityQuestions;
        this.interestQuestions = interestQuestions;
        this.egogramQuestions = egogramQuestions;
        p = new Paint();

        gson = new Gson();
        json = gson.toJson(meornotmeQuestions);
        MyApplication.getInstance().prefManager.saveString("questionsSection2",json);

        json = gson.toJson(abilityQuestions);
        MyApplication.getInstance().prefManager.saveString("questionsSection3",json);

        json = gson.toJson(interestQuestions);
        MyApplication.getInstance().prefManager.saveString("questionsSection4",json);

        json = gson.toJson(egogramQuestions);
        MyApplication.getInstance().prefManager.saveString("questionsSection5",json);


        json = gson.toJson(questions);
        MyApplication.getInstance().prefManager.saveString("questions",json);
        life = 3;
        hints = new String[13];
        this.questions = questions;


        intent = new Intent(getContext(),SectionEndActivity.class);

        intent.putExtra("questions",questions);
        intent.putExtra("questionsSection2",meornotmeQuestions);
        intent.putExtra("questionsSection3",abilityQuestions);
        intent.putExtra("questionsSection4",interestQuestions);
        intent.putExtra("questionsSection5",egogramQuestions);
        intent.putExtra("section","7");

        i = new Intent(getContext(), AnalysisActivity.class);
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
        i.putExtra("state","1");
       // i1 = new Intent(getContext(), GameOver.class);

    }


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        p = new Paint();

        life = 3;
        hints = new String[13];

        i = new Intent(getContext(), AnalysisActivity.class);
        i.putExtra("state","1");
       // i1 = new Intent(getContext(), GameOver.class);



    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        p = new Paint();

        life = 3;
        hints = new String[13];


        i = new Intent(getContext(), SectionEndActivity.class);

       // i1 = new Intent(getContext(), GameOver.class);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        hints[0]="Focus on \"you take away 4\"";
        hints[1]="Light other candles";
        hints[2]="Look your feet Closely!";
        hints[3]="Look at first assumption";
        hints[4]="It's not a science question";
        hints[5]="Use Gravity";
        hints[6]="Look at sign";
        hints[7]="Think about how you wear a shirt";
        hints[8]="It has nothing to do with the buttons";
        hints[9]="Use B.O.D.M.A.S. rule";
        hints[10]="Shake the tree";
        hints[11]="Your answer is in question";
        hints[12]="Imagine that the owl is on your right";



        t = Typeface.createFromAsset(context.getAssets(), "raleway.ttf");

        p.setTypeface(t);

        p.setColor(Color.BLACK);
        p.setAlpha(99);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawRect(0, 0, getWidth(), getWidth() / 8, p);
        p.setColor(Color.WHITE);
        p.setTextSize(getWidth() / 20);
        canvas.drawText("Level: " + level, getWidth() / 2 - getWidth() / 10, getHeight() / 18, p);


        Bitmap a = BitmapFactory.decodeResource(getResources(), R.drawable.hiddenobject_hint);
        hint = Bitmap.createScaledBitmap(a, getWidth() / 10, getWidth() / 10, false);


        //Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.brain_skip);
        //skip = Bitmap.createScaledBitmap(b, getWidth() / 7, getWidth() / 7, false);

        canvas.drawBitmap(hint, getWidth() - hint.getWidth() - getWidth() / 60, getHeight() / 100, null);

        if (life <= 0) {

            intent = new Intent(getContext(),SectionEndActivity.class);
            json = MyApplication.getInstance().prefManager.getString("questionsSection2");
            gson = new Gson();
            BooleanQuestion meornotme = gson.fromJson(json,BooleanQuestion.class);
            intent.putExtra("questionsSection2",meornotme);

            json = MyApplication.getInstance().prefManager.getString("questions");
            meornotme = gson.fromJson(json,BooleanQuestion.class);
            intent.putExtra("questions",meornotme);

            json = MyApplication.getInstance().prefManager.getString("questionsSection3");
            meornotme = gson.fromJson(json,BooleanQuestion.class);
            intent.putExtra("questionsSection3",meornotme);

            json = MyApplication.getInstance().prefManager.getString("questionsSection4");
            meornotme = gson.fromJson(json,BooleanQuestion.class);
            intent.putExtra("questionsSection4",meornotme);

            json = MyApplication.getInstance().prefManager.getString("questionsSection5");
            meornotme = gson.fromJson(json,BooleanQuestion.class);
            intent.putExtra("questionsSection5",meornotme);

//            intent.putExtra("questionsSection3",abilityQuestions);
//            intent.putExtra("questionsSection4",interestQuestions);
//            intent.putExtra("questionsSection5",egogramQuestions);
           intent.putExtra("section","7");

            getContext().startActivity(intent);



//            if (analysis) {
//                i.putExtra("flag","false");
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                getContext().getApplicationContext().startActivity(i);
//
//            }
//            else {
//
//                i1.putExtra("flag","true");
//                i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                getContext().getApplicationContext().startActivity(i);
//
//
//            }
        }

        canvas.drawText("Life: " + life, getWidth() / 60, getHeight() / 18, p);


        if (hintflag == 1) {
            p.setColor(Color.BLACK);
            p.setAlpha(99);

            ///canvas.drawRect(0,getHeight()-getHeight()/12, getWidth(), getHeight(), p);
            canvas.drawRect(0,getWidth()/8, getWidth(), getWidth()/5, p);


            p.setColor(Color.WHITE);
            p.setTextAlign(Paint.Align.CENTER);
            p.setTextSize(getWidth()/20);
            canvas.drawText(hints[level-1], getWidth()/2,getWidth()/8+getHeight()/32, p );

        }

        /*else {
            canvas.drawBitmap(skip, getWidth()/2 - skip.getWidth()/2, getHeight()-skip.getHeight()-getHeight()/80, null);
        }*/
    }

    public boolean onTouchEvent(MotionEvent event) {


        int x = (int) event.getX(), y = (int) event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if ((x>getWidth()-hint.getWidth()-getWidth()/60)&& y<getHeight()/100+hint.getHeight()) {

                    if (hintflag == 0) {
                        hintflag=1;

                    }

                    else {
                        hintflag=0;
                    }
                    invalidate();
                }

                /*if (hintflag==0) {
                    if (x>getWidth()/2 - skip.getWidth()/2 && x<getWidth()/2 - skip.getWidth()/2+skip.getWidth() && y>getHeight()-skip.getHeight()-getHeight()/80) {
                        callNextActivity();
                    }
                }*/


        }



        return true;
    }


    /*public void callNextActivity() {

        if (level==1)
            i2 = new Intent(getContext(), Level2Activity.class);
        if (level==2)
            i2 = new Intent(getContext(), Level3Activity.class);
        if (level==3)
            i2 = new Intent(getContext(), Level4Activity.class);
        if (level==4)
            i2 = new Intent(getContext(), Level5Activity.class);
        if (level==5)
            i2 = new Intent(getContext(), Level6Activity.class);
        if (level==6)
            i2 = new Intent(getContext(), Level7Activity.class);
        if (level==7)
            i2 = new Intent(getContext(), Level8Activity.class);
        if (level==8){
            i2 = new Intent(getContext(), EndActivity.class);
        }



        i2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //i2.putExtra("flag","false");
        getContext().getApplicationContext().startActivity(i2);
    }*/

}
