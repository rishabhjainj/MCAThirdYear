package com.AbhiDev.edurecomm.Activities.Analysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wireout.Activities.BaseActivity;
import com.wireout.Activities.CareerAssesmentStartActivity;
import com.wireout.R;

public class DetailMenu extends BaseActivity {
    EditText phone,name,sysId,comment;
    Button next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        next = findViewById(R.id.next1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailMenu.this, AnalysisStatusActivity.class));
                finish();
            }
        });

    }
}
