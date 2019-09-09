package com.AbhiDev.edurecomm.ViewMore;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.wireout.Activities.BaseActivity;
import com.wireout.R;
import com.wireout.adapters.ViewMoreCarrierAdapter;
import com.wireout.common.PrefManager;
import com.wireout.models.ShortTermCourse;
import com.wireout.presenters.ShortTermCoursesPresesnter;
import com.wireout.repositories.ShortTermCoursesRepository;
import com.wireout.viewactions.ShortTermCoursesViewAction;

import java.util.List;

public class ShortTermCoursesActivity extends BaseActivity implements ShortTermCoursesViewAction{

    RecyclerView recyclerView;
    ViewMoreCarrierAdapter adapter;
    ShortTermCoursesPresesnter presesnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more__short_term_carrier);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_viewMore_carrier);

        presesnter=new ShortTermCoursesPresesnter(this,getApplicationContext(),new ShortTermCoursesRepository());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        Intent intent=getIntent();
//        Bundle bundle=intent.getBundleExtra("Bundle");
//        ArrayList<ShortTermCoursesData> urls=(ArrayList<ShortTermCoursesData>)bundle.getSerializable("Urls");

        presesnter.getShortTermCourses();

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
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourseList) {
        adapter=new ViewMoreCarrierAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),shortTermCourseList){
            @Override
            public void like(int id) {
                presesnter.likeShortTermCourse(id);
            }
        };
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
