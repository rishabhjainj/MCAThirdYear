package com.AbhiDev.edurecomm.GameBrainBooster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.wireout.Activities.BaseActivity;
import com.wireout.GameBrainBooster.Levels.Level1Activity;
import com.wireout.R;
import com.wireout.models.career_analysis.BooleanQuestion;

import java.io.File;

public class MainActivity extends BaseActivity {

    Typeface t;
    TextView title, sbt;
    Intent intent;
    Button b;
    Intent i;
    BooleanQuestion questions,meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions;

    @Override
    public void onBackPressed() {
        showMessage("Back button has been disabled for analysis purpose.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        deleteCache(this);

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CustomView c=new CustomView(this,questions,meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions);
        c.life=3;
        CustomView.game8_CorrectlyAnswered=0;

        i = new Intent(this, Level1Activity.class);
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
        if(intent.getExtras().get("flag")==("true")){
            i.putExtra("flag","true");
            CustomView.analysis = false;
        }
        else{
            CustomView.analysis = true;
            i.putExtra("flag","false");
        }

        t = Typeface.createFromAsset(getAssets(), "raleway.ttf");

        title = (TextView) findViewById(R.id.textView);
        sbt = (TextView) findViewById(R.id.textView4);
        b = (Button) findViewById(R.id.button);

        title.setTypeface(t);
        sbt.setTypeface(t);

        b.setTypeface(t);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();

            deleteDir(dir);
        }catch (Exception e){

        }
    }


    public  static boolean deleteDir(File dir) {
        if((dir != null) && dir.isDirectory()) {
            String[] c=  dir.list();

            for (int i=0; i < c.length; ++i) {
                boolean success = deleteDir(new File(dir, c[i]));

                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!=null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.id.bbmain));
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
