package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.wireout.Internships.InternshipsAdapter;
import com.wireout.adapters.RelatedCoursesAdapter;
import com.wireout.adapters.TopCoursesItemAdapter;
import com.wireout.adapters.ViewMoreAmbassadorAdapter;
import com.wireout.apiservices.Repository;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Course;
import com.wireout.models.Internships;
import com.wireout.models.Message;
import com.wireout.models.RelatedCourse;
import com.wireout.R;
import com.wireout.adapters.AmbassdorsRecyclerViewAdapter;
import com.wireout.adapters.RecommendedCourseAdapter;
import com.wireout.adapters.RecommendedUniAdapter;
import com.wireout.adapters.ShortTermAdapter;
import com.wireout.common.PrefManager;
import com.wireout.models.CourseList;
import com.wireout.models.Mentor;
import com.wireout.models.ShortTermCourse;
import com.wireout.models.University;
import com.wireout.presenters.WishListPresenter;
import com.wireout.repositories.HomePageRepository;
import com.wireout.viewactions.WishlistViewAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WishlistActivity extends BaseActivity implements WishlistViewAction{

    ImageView filters;
    RecyclerView recyclerView;
    WishListPresenter presenter;
    ViewMoreAmbassadorAdapter adapter;
    TopCoursesItemAdapter courseAdapter;
    InternshipsAdapter internshipsAdapter;
    PrefManager prefManager;
    OnEntitiesReceivedListener<Mentor> mentorListener;
    OnEntitiesReceivedListener<Internships> internshipsListener;
    DisplayMetrics displayMetrics;
    Map<String,String> queryMap;
    OnEntitiesReceivedListener<CourseList> courseListener;

    int filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        savedInstanceState=new Bundle();
        savedInstanceState.putInt("filter",filter);

        mentorListener= new OnEntitiesReceivedListener<Mentor>(this) {
            @Override
            public void onReceived(List<Mentor> mentors) {
                initUi();
                setWishListCampaigners(mentors);
            }
        };
        courseListener = new OnEntitiesReceivedListener<CourseList>(this) {
            @Override
            public void onReceived(List<CourseList> courses) {
                initUi();
                setRelatedCoursesRecyclerView(courses);
            }
        };
        internshipsListener = new OnEntitiesReceivedListener<Internships>(this) {
            @Override
            public void onReceived(List<Internships> entities) {
                initUi();
                setInternshipRecyclerView(entities);
            }
        };
        queryMap = new HashMap<>();
        queryMap.put("liked","true");

//        filter=getIntent().getIntExtra("filter",0);

        prefManager=new PrefManager(getApplicationContext());


        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        presenter=new WishListPresenter(new Repository(),this,getApplicationContext());


        Log.d("filterState",prefManager.getInt("filterState")+"");


        switch (prefManager.getInt("filterState"))
        {
            case 1:
                setContentView(R.layout.activity_lottie);
                presenter.getCourses(queryMap,courseListener);
                    break;
            case 2: setContentView(R.layout.activity_lottie);
                    presenter.getCampaigners(queryMap,mentorListener);
                    break;
            case 3: setContentView(R.layout.activity_lottie);
                    presenter.getInternships(queryMap,internshipsListener);
                    break;
            default: presenter.getCourses(queryMap,courseListener);
                        break;
        }

    }

    public void initUi(){
        setContentView(R.layout.activity_wishlist);
        filters=findViewById(R.id.filterWish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.recyclerView_wishlist);
        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),WishlistFilterActivity.class);
                intent.putExtra("filterState",prefManager.getInt("filterState"));
                startActivityForResult(intent,1);
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
    public void setWishListRecyclerView(List<University> universityDataList) {

//        adapter=new RecommendedUniAdapter(this,universityDataList,displayMetrics){
//            @Override
//            public void OnLiked(int id) {
//                presenter.likeUniversity(id);
//            }
//        };
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setWishListCourses(List<CourseList> courseList) {
//       courseAdapter=new RecommendedCourseAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),courseList){
//            @Override
//            public void OnLiked(int id) {
//                presenter.likeCourses(id);
//            }
//        };
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(courseAdapter);
    }

    @Override
    public void setWishListShortTermCourses(List<ShortTermCourse> shortTermCourseList) {

    }

    public void setRelatedCoursesRecyclerView(List<CourseList> courses)
    {
//
        recyclerView.hasFixedSize();
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
//
        courseAdapter=new TopCoursesItemAdapter(this,courses){
            @Override
            public void like(int id) {
                presenter.likeCourse(id);
            }
        };

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();

    }
    public void setInternshipRecyclerView(List<Internships> internships)
    {
//
        recyclerView.hasFixedSize();
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
//
        internshipsAdapter=new InternshipsAdapter(this,internships){
            @Override
            public void like(int id) {
                presenter.likeInternship(id);
            }
        };

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(internshipsAdapter);
        internshipsAdapter.notifyDataSetChanged();

    }
    @Override
    public void setWishListCampaigners(List<Mentor> campaignersList) {
        adapter=new ViewMoreAmbassadorAdapter(getApplicationContext(),campaignersList){
            @Override
            public void OnLiked(int id) {
                presenter.likeCampaigners(id);
            }
        };
        StaggeredGridLayoutManager linearLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1)
        {
            if (resultCode== RESULT_OK)
            {
                filter=data.getIntExtra("filter",0);
                if (filter==1)
                {
                    presenter.getCourses(queryMap,courseListener);
                }
                else if (filter==2)
                {
                    presenter.getCampaigners(queryMap,mentorListener);
                }
                else if(filter==3){

                    presenter.getInternships(queryMap,internshipsListener);
                }
            }
        }
    }
}
