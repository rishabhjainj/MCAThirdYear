package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
public class SectionInfoActivity extends BaseActivity {
    ImageView backBtn;
    ImageView sectionImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section_info_activity);
        sectionImage = findViewById(R.id.sectionImage);
        Intent intent = getIntent();
        String section = intent.getExtras().getString("id");
        switch (section){
            case "0":
                setBackgroundImage(R.drawable.historygoals_start_screen);
                break;
            case "1":
                setBackgroundImage(R.drawable.meormenot_start_screen);

                break;
            case "2":
                setBackgroundImage(R.drawable.ability_start_screen);

                break;
            case "3":
                setBackgroundImage(R.drawable.interests_start_screen);

                break;
            case "4":
                setBackgroundImage(R.drawable.youregogram_start_screen);

                break;
            case "5":
                setBackgroundImage(R.drawable.lifechoices_start_screen);

                break;
            case "6":
                setBackgroundImage(R.drawable.verbal_ap_start_screen);

                break;
            case "7":
                setBackgroundImage(R.drawable.brainteaser_start_screen);

                break;
            case "8":
                setBackgroundImage(R.drawable.emotionalintelligence_start_screen);

                break;
            case "9":
                setBackgroundImage(R.drawable.flexibilitygame_start_screen);

                break;
            case "10":
                setBackgroundImage(R.drawable.quickmaths_start_screen);

                break;
            case "11":
                setBackgroundImage(R.drawable.motivational_start_screen);

                break;
            case "12":
                setBackgroundImage(R.drawable.communication_skill_start_screen);
                //change background for comm skills here
                break;
            case "13":
                setBackgroundImage(R.drawable.logical_reasoning_start_screen);
                //change background for logical reasoning here
                break;
            case "14":
                setBackgroundImage(R.drawable.handwritinganalysis_start_screen);

                break;





        }
        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    public void setBackgroundImage(Integer imageId){
        Picasso.with(this).load(imageId).into(sectionImage);
    }
}
