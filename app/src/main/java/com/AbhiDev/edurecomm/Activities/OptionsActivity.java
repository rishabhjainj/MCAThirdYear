package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.wireout.Activities.Analysis.AnalysisActivity;
import com.wireout.Fragments.EventsFragment;
import com.wireout.Fragments.VideosFragment;
import com.wireout.R;

public class OptionsActivity extends AppCompatActivity {
    Intent intent;
    ImageView  startButton;
    TextView title;
    RoundCornerProgressBar progress1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);
        title = findViewById(R.id.tooltilte);
        intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        int option = Integer.parseInt(intent.getExtras().get("option")+"");
        switch(option){
            case 1:
                title.setText("Career News");
                VideosFragment nextFrag= new VideosFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag,"videofragment")
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                title.setText("Events");
                EventsFragment eventsFragment= new EventsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, eventsFragment,"eventfragment")
                        .addToBackStack(null)
                        .commit();
                break;
            case 3:
                setContentView(R.layout.bottom_profile_layout);
                startButton = (ImageView) findViewById(R.id.start_p);
                startButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(OptionsActivity.this,AnalysisActivity.class);
                        i.putExtra("state","2");
                        startActivity(i);
                    }
                });


        }


    }
    private void setProgressBarData(int completion){
        progress1 = (RoundCornerProgressBar) findViewById(R.id.progress_1);
        progress1.setProgressColor(getResources().getColor(R.color.dorange));
        progress1.setProgressBackgroundColor(getResources().getColor(R.color.ligorange));
        progress1.setMax(100);
        progress1.setProgress(completion);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
