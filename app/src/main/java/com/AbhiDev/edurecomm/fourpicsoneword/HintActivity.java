package com.AbhiDev.edurecomm.fourpicsoneword;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.wireout.Activities.BaseActivity;
import com.wireout.R;

import java.util.ArrayList;

public class HintActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        ArrayList<String> jumbledWords = (ArrayList<String>) getIntent().getExtras().get("jumbledWords");
        ((TextView) findViewById(R.id.textView1)).setText(jumbledWords.get(0));
        ((TextView) findViewById(R.id.textView2)).setText(jumbledWords.get(1));
        ((TextView) findViewById(R.id.textView3)).setText(jumbledWords.get(2));
        ((ImageView) findViewById(R.id.closeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
