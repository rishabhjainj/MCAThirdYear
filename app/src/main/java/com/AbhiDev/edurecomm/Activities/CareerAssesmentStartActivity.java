package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.AbhiDev.edurecomm.Activities.Analysis.AnalysisStatusActivity;
import com.AbhiDev.edurecomm.Activities.Analysis.DetailMenu;
import com.AbhiDev.edurecomm.R;
import com.AbhiDev.edurecomm.common.MyApplication;
import com.facebook.login.Login;
import com.wireout.Activities.Analysis.AnalysisStatusActivity;

import com.wireout.Activities.Analysis.DetailMenu;
import com.wireout.common.MyApplication;
import com.wireout.R;

public class CareerAssesmentStartActivity extends BaseActivity {
    TextView startButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.career_assesment_intro);
        startButton = findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn()) {
                    if(MyApplication.getInstance().prefManager.getString(AnalysisStatusActivity.ANALYSIS_ENTERED)==null)
                    startActivity(new Intent(CareerAssesmentStartActivity.this, DetailMenu.class));
                    else
                        startActivity(new Intent(CareerAssesmentStartActivity.this, AnalysisStatusActivity.class));
                    finish();
                }
                else{
                    showMessage("Login to start Career Assessment.");
                    startActivity(new Intent(CareerAssesmentStartActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(CareerAssesmentStartActivity.this,AptitudeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
