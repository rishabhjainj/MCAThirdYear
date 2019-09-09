package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

public class FrameworkIntroScreenActivity extends BaseActivity {
    ImageView start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framework_layout);
        start = findViewById(R.id.getStarted);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn()) {
                    startActivity(new Intent(FrameworkIntroScreenActivity.this, CareerAssesmentStartActivity.class));
                    finish();
                }
                else{
                    showMessage("Login to start Career Assessment.");
                    startActivity(new Intent(FrameworkIntroScreenActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });

    }
}
