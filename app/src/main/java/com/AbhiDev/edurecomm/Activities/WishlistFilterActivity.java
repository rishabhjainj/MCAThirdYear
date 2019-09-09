package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class WishlistFilterActivity extends BaseActivity {
    RadioButton  courses, stCourses,internship;
    RadioGroup sortGroup;
    TextView resetButton;
    public Integer sortId = 0;
    Button apply;
    public SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist_filters_activity);

        searchView = (SearchView) findViewById(R.id.searchView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Filters");

        initUi();


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

    private void initUi()
    {
        internship = findViewById(R.id.internship);
        courses = (RadioButton) findViewById(R.id.courses);
        stCourses = (RadioButton) findViewById(R.id.stCourses);

        apply=findViewById(R.id.btn_apply_filters);


        resetButton = findViewById(R.id.resetButton);
//        resetButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resetFilters();
//            }
//        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(internship.isChecked()){
                    sortId=3;
                }
                 if (courses.isChecked())
                {
                    sortId=1;
                }
                else if (stCourses.isChecked())
                {
                    sortId=2;
                }

                if (sortId==0)
                {
                    Toast.makeText(getApplicationContext(),"No filter is applied right now",Toast.LENGTH_LONG).show();
                }

                PrefManager prefManager=new PrefManager(getApplicationContext());
                prefManager.saveInt("filterState",sortId);

                Intent intent=new Intent();

                intent.putExtra("filter",sortId);
                intent.putExtra("searchQuery",searchView.getQuery());
                setResult(RESULT_OK, intent);


//                startActivity(intent);
                finish();
            }
        });

        final int filter=getIntent().getIntExtra("filterState",1);
        String searchQuery = getIntent().getExtras().getString("searchquery");
        searchView.setQuery(searchQuery,true);



        switch (filter){
            case 1 : courses.setChecked(true); break;
            case 2 : stCourses.setChecked(true); break;
            case 3:internship.setChecked(true); break;
            default: //Toast.makeText(getApplicationContext(),"filter = "+filter,Toast.LENGTH_LONG).show();
        }



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(internship.isChecked()){
                    internship.isChecked();
                }
                if (courses.isChecked())
                {
                    sortId=1;
                }
                else if (stCourses.isChecked())
                {
                    sortId=2;
                }
                if (sortId==0)
                {
                    Toast.makeText(getApplicationContext(),"No filter is applied right now",Toast.LENGTH_LONG).show();
                }

                PrefManager prefManager=new PrefManager(getApplicationContext());
                prefManager.saveInt("filterState",sortId);

                Intent intent=new Intent();

                intent.putExtra("filter",sortId);
                setResult(RESULT_OK, intent);

//                startActivity(intent);
                finish();
            }
        });
    }


    public void resetFilters(){
        sortId=0;
    }
}
