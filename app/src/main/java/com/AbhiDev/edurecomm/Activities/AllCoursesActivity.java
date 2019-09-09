package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.AbhiDev.edurecomm.R;
import com.AbhiDev.edurecomm.adapters.RelatedCoursesAdapter;
import com.AbhiDev.edurecomm.adapters.TopCoursesItemAdapter;
import com.AbhiDev.edurecomm.apiservices.Repository;
import com.AbhiDev.edurecomm.listeners.OnEntitiesReceivedListener;
import com.AbhiDev.edurecomm.models.Course;
import com.AbhiDev.edurecomm.models.CourseList;
import com.AbhiDev.edurecomm.models.RelatedCourse;
import com.AbhiDev.edurecomm.presenters.CoursesPresenter;
import com.AbhiDev.edurecomm.viewactions.CourseViewAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCoursesActivity extends BaseActivity implements CourseViewAction {

    RecyclerView courseRecyclerView;
    String choice;
    ArrayList<String> categoryIds =new ArrayList<>();
    int index=1;
    Map<String,String> queryMap = new HashMap<>();
    OnEntitiesReceivedListener<CourseList> moreCourseListener;
    RelatedCoursesAdapter courseAdapter;
    TopCoursesItemAdapter topCoursesItemAdapter;
    OnEntitiesReceivedListener<CourseList> courseListener;
    CoursesPresenter presenter;
    ArrayList<CourseList> coursesList;
    static final int FILTERS_REQUEST_CODE = 1;
    ImageView filters;
    List<CourseList> courseList;
    String searchQuery;
    List<RelatedCourse> courseLists;
    OnEntitiesReceivedListener<CourseList> exploreListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coursesList = new ArrayList<>();
        setContentView(R.layout.activity_lottie);
        choice = getIntent().getExtras().get("landing")+"";
        presenter = new CoursesPresenter(this,new Repository());
        switch (choice){
            case "explore":
                initUi(false);
                exploreListener = new OnEntitiesReceivedListener<CourseList>(this) {
                    @Override
                    public void onReceived(List<CourseList> entities) {
                        setTopCourses(entities);
                    }
                };
                courseListener = new OnEntitiesReceivedListener<CourseList>(this) {
                    @Override
                    public void onReceived(List<CourseList> courses) {
                        coursesList.addAll(courses);
                        initUi(false);
                        setTopCourses(courses);
                    }
                };
                searchQuery = getIntent().getExtras().get("search")+"";
                queryMap = new HashMap<>();
                queryMap.put("search",searchQuery);
                presenter.searchCourses(queryMap,exploreListener);
                break;
            case "yes":
                initUi(true);
                courseLists = (List<RelatedCourse>) getIntent().getSerializableExtra("LIST");
                setRelatedCoursesRecyclerView(courseLists);
                break;
            case "exc":
                initUi(true);
                courseList = (List<CourseList>)getIntent().getSerializableExtra("LIST");
                coursesList.addAll(courseList);
                setTopCourses(courseList);
                break;
            case "no":
                courseListener = new OnEntitiesReceivedListener<CourseList>(this) {
                    @Override
                    public void onReceived(List<CourseList> courses) {
                        coursesList.addAll(courses);
                        initUi(false);
                        setTopCourses(courses);
                    }
                };
                presenter.getCourses(courseListener);
                break;
        }
        moreCourseListener = new OnEntitiesReceivedListener<CourseList>(this) {
            @Override
            public void onReceived(List<CourseList> entities) {
                addToRecyclerView(entities);
            }
        };
//        courseListener = new OnEntitiesReceivedListener<CourseList>(this) {
//            @Override
//            public void onReceived(List<CourseList> courses) {
//                initUi();
//                setTopCourses(courses);
//            }
//        };
//        presenter = new CoursesPresenter(this,new Repository());
//        presenter.getCourses(courseListener);
    }

    @Override
    public void addToRecyclerView(final List<CourseList> courseLists) {
        if(topCoursesItemAdapter!=null){

            coursesList.addAll(courseLists);
            topCoursesItemAdapter.notifyDataChanged();
            if(courseLists.size()==20){
                // showMessage("setmoredata");
                topCoursesItemAdapter.setMoreDataAvailable(true);
            }
            else{
                //showMessage("not av");
                topCoursesItemAdapter.setMoreDataAvailable(false);
            }

            topCoursesItemAdapter.setLoadMoreListener(new TopCoursesItemAdapter.OnLoadMoreListener(){

                @Override
                public void onLoadMore() {
                    courseRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            index++;
                            if(categoryIds.size()>0)
                                presenter.applyFilters(queryMap,categoryIds, index,moreCourseListener);
                            else
                                presenter.loadMoreCourses(queryMap,index, moreCourseListener);
                            // a method which requests remote data
                        }
                    });

                }
            });

        }
    }

    public void setRelatedCoursesRecyclerView(List<RelatedCourse> courses)
    {
//
        courseRecyclerView.hasFixedSize();
        courseRecyclerView.removeAllViews();
        courseRecyclerView.setNestedScrollingEnabled(false);
        courseRecyclerView.removeAllViewsInLayout();
//
        courseAdapter=new RelatedCoursesAdapter(this,courses){
            @Override
            public void like(int id) {
                presenter.likeCourse(id);
            }
        };

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        courseRecyclerView.setLayoutManager(layoutManager);
        courseRecyclerView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();

    }
    public void initUi(Boolean hide){
        setContentView(R.layout.activity_all_courses);

        filters = findViewById(R.id.filter_search);
        if(hide){
            filters.setVisibility(View.GONE);
        }
        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllCoursesActivity.this,CategoryBasedFilters.class);
                startActivityForResult(intent,FILTERS_REQUEST_CODE);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        courseRecyclerView = findViewById(R.id.subjects_recyclerview);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // viewAction.showToast(getIntent().getStringExtra("keyword"));
        super.onActivityResult(requestCode, resultCode, data);
        index=1;
        if(requestCode == FILTERS_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                // call presenter logic and re-render the product list
                Bundle filterData = data.getBundleExtra("filterData");
                queryMap = new HashMap<>();
                if(getIntent().getExtras().get("search")!=null){
                    searchQuery = getIntent().getExtras().get("search")+"";
                    queryMap.put("search",searchQuery);
                }
                //getSupportActionBar().setTitle("Toys");
                categoryIds = (ArrayList<String>) filterData.get("categoryIds");
                // showMessage("got filter request"+categoryIds.get(0).toString());
                // recyclerView.removeAllViewsInLayout();
                presenter.applyFilters(queryMap,categoryIds,index,courseListener);

            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this,"You did not apply the filters!",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void feedUniName(String name) {

    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initUi(Course course) {

    }

    public void setTopCourses(List<CourseList> courses)
    {

        this.coursesList.clear();
        this.coursesList.addAll(courses);
        courseRecyclerView.hasFixedSize();
        courseRecyclerView.removeAllViews();
        courseRecyclerView.setNestedScrollingEnabled(false);
        courseRecyclerView.removeAllViewsInLayout();

        if(courses.size()>0) {
            topCoursesItemAdapter = new TopCoursesItemAdapter(this, coursesList) {
                @Override
                public void like(int id) {
                    presenter.likeCourse(id);
                }
            };


            topCoursesItemAdapter.setLoadMoreListener(new TopCoursesItemAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    courseRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (coursesList.size() == 20) {
                                index++;
                                if(categoryIds.size()>0)
                                    presenter.applyFilters(queryMap,categoryIds, index,moreCourseListener);
                                else
                                    presenter.loadMoreCourses(queryMap,index, moreCourseListener);
                                    // a method which requests remote data
                            }
                        }
                    });
                }
            });
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            courseRecyclerView.setLayoutManager(layoutManager);
            courseRecyclerView.setAdapter(topCoursesItemAdapter);
            topCoursesItemAdapter.notifyDataSetChanged();
        }

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

}
