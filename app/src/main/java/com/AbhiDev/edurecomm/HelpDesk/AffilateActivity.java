package com.AbhiDev.edurecomm.HelpDesk;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import com.wireout.Activities.BaseActivity;
import com.wireout.R;

public class AffilateActivity extends BaseActivity {

    CardView cardView1,cardView2,cardView3;
    ImageView img1,img2,img3;
    ExpandableRelativeLayout exp1,exp2,exp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affilate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Reward Program");

        cardView1=(CardView)findViewById(R.id.card1_aff);
        cardView2=(CardView)findViewById(R.id.card2_aff);
        cardView3=(CardView)findViewById(R.id.card3_aff);

        img1=(ImageView)findViewById(R.id.img1_aff);
        img2=(ImageView)findViewById(R.id.img2_aff);
        img3=(ImageView)findViewById(R.id.img3_aff);

        exp1=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_aff1_help);
        exp2=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_aff2_help);
        exp3=(ExpandableRelativeLayout)findViewById(R.id.expandableLayout_aff3_help);

        exp1.collapse();
        exp2.collapse();
        exp3.collapse();


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp1.isExpanded())
                {
                    img1.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img1.setImageResource(R.drawable.ic_action_uparraow);
                }

                exp1.toggle();
                exp1.expand();
                exp1.moveChild(0);
                exp1.move(100);
                exp1.setClosePosition(0);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp2.isExpanded())
                {
                    img2.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img2.setImageResource(R.drawable.ic_action_uparraow);
                }
                exp2.toggle();
                exp2.expand();
                exp2.moveChild(0);
                exp2.move(100);
                exp2.setClosePosition(0);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (exp3.isExpanded())
                {
                    img3.setImageResource(R.drawable.ic_action_plus);
                }
                else
                {
                    img3.setImageResource(R.drawable.ic_action_uparraow);
                }

                exp3.toggle();
                exp3.expand();
                exp3.moveChild(0);
                exp3.move(100);
                exp3.setClosePosition(0);
            }
        });

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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public void showMessage(String message) {
        super.showMessage(message);
    }

    @Override
    public void showNetworkError(String message) {
        super.showNetworkError(message);
    }
}
