package com.AbhiDev.edurecomm.ViewMore;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;


import com.wireout.Activities.BaseActivity;
import com.wireout.R;
import com.wireout.adapters.ViewMoreTrendingCoursesAdapter;
import com.wireout.common.PrefManager;
import com.wireout.models.CourseList;
import com.wireout.presenters.TrendingCoursesPresenter;
import com.wireout.repositories.TrendingCoursesRepository;
import com.wireout.viewactions.TrendingCoursesViewAction;

import java.util.List;

public class TrendingCoursesActivity extends BaseActivity implements TrendingCoursesViewAction{

    RecyclerView recyclerView;
    ViewMoreTrendingCoursesAdapter adapter;
    TrendingCoursesPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more__trending_courses);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_viewMore_trendingCourses);

        presenter=new TrendingCoursesPresenter(new TrendingCoursesRepository(),getApplicationContext(),this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        Intent intent=getIntent();
//        Bundle bundle=intent.getBundleExtra("Bundle");
//        ArrayList<ShortTermCoursesData> shortTermCoursesData=(ArrayList<ShortTermCoursesData>)bundle.getSerializable("ShortTermCoursesData");

        presenter.getCourses();

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
    public void setTrendingCoursesRecyclerView(List<CourseList> courseList) {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        adapter=new ViewMoreTrendingCoursesAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),courseList,displayMetrics){
            @Override
            public void OnLiked(int id) {
                presenter.likeCourse(id);
            }
        };

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void startVideos() {

    }
}
