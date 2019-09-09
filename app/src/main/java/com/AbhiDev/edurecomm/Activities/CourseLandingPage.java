package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.AbhiDev.edurecomm.R;
import com.AbhiDev.edurecomm.ViewMore.AmbassdorsActivity;
import com.AbhiDev.edurecomm.adapters.CareerBlogAdapter;
import com.AbhiDev.edurecomm.adapters.CareerOptionsAdapter;
import com.AbhiDev.edurecomm.adapters.LandingPageMentorAdapter;
import com.AbhiDev.edurecomm.adapters.RelatedCoursesAdapter;
import com.AbhiDev.edurecomm.apiservices.Repository;
import com.AbhiDev.edurecomm.common.MyApplication;
import com.AbhiDev.edurecomm.listeners.OnEntitiesReceivedListener;
import com.AbhiDev.edurecomm.listeners.OnUserReceivedListener;
import com.AbhiDev.edurecomm.models.Articles;
import com.AbhiDev.edurecomm.models.CareerOption;
import com.AbhiDev.edurecomm.models.Course;
import com.AbhiDev.edurecomm.models.CourseList;
import com.AbhiDev.edurecomm.models.Mentor;
import com.AbhiDev.edurecomm.models.RelatedCourse;
import com.AbhiDev.edurecomm.models.User;
import com.AbhiDev.edurecomm.presenters.CoursesPresenter;
import com.AbhiDev.edurecomm.presenters.CurrentUserPresenter;
import com.AbhiDev.edurecomm.viewactions.CourseViewAction;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class CourseLandingPage extends BaseActivity implements CourseViewAction, OnUserReceivedListener {
    ImageView backBtn;
    TextView popularityPercent;
    String universityName;
    RecyclerView collegeRecyclerView;
    FloatingActionButton floatingActionButton;
    TextView courseName;
    TextView userName;
    TextView courseDesc;
    TextView viewAllCourses,viewAllCareer,viewAllMentors,viewAllBlogs;
    TextView tootitle;
    TextView learnMore;
    TextView courseTagline;
    TextView rupee,rupee2;
    RecyclerView similarCourseRecycler;
    RelatedCoursesAdapter topCoursesItemAdapter;
    CareerOptionsAdapter subjectsAdapter;
    ImageView courseImage;
    Double actualPrice;
    Double discountPercent;
    Double discountedPrice;
    DisplayMetrics displayMetrics;
    CoursesPresenter presenter;
    RelativeLayout topLayout;
    TextView cutRate;
    TextView numLikes;
    TextView mode;
    TextView courseDuration;
    TextView ratingText;
    TextView university;
    TextView actualprice;
    TextView offPercent;
    //ProperRatingBar ratingBar;
    OnEntitiesReceivedListener<Mentor> mentorListener;
    RecyclerView trendingRecyclerView;
    LinearLayout cardView;
    LandingPageMentorAdapter adapter;
    ImageView modeImage;
    CareerBlogAdapter wikipediaAdapter;
    TextView courseShortname;
    RecyclerView recyclerView;
    TextView registerButton;
    OnEntitiesReceivedListener<Articles> listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        String id  = getIntent().getExtras().get("id")+"";

        presenter = new CoursesPresenter(this,new Repository());
        presenter.getCoursesById(id);

       // setSimilarCourseRecycler(courses);

        listener = new OnEntitiesReceivedListener<Articles>(this) {
            @Override
            public void onReceived(List<Articles> articles) {
                setTrendingRecyclerView(articles);
            }
        };
        mentorListener = new OnEntitiesReceivedListener<Mentor>(this) {
            @Override
            public void onReceived(List<Mentor> mentors) {
                setAmbassdorRecyclerView(mentors);
            }
        };


    }

    @Override
    public void addToRecyclerView(List<CourseList> courseLists) {

    }

    @Override
    public void feedUniName(String name) {
        universityName = name;
    }

    public void setTrendingRecyclerView(List<Articles> articles) {
        trendingRecyclerView.setHasFixedSize(true);
        trendingRecyclerView.removeAllViews();
        trendingRecyclerView.setNestedScrollingEnabled(false);
        trendingRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        trendingRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        wikipediaAdapter =
                new CareerBlogAdapter(this,articles);
        trendingRecyclerView.setAdapter(wikipediaAdapter);
        trendingRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        wikipediaAdapter.notifyDataSetChanged();

    }
    public void setAmbassdorRecyclerView(List<Mentor> ambassdorsDataList) {
        adapter=new LandingPageMentorAdapter(getApplicationContext(),ambassdorsDataList){
            @Override
            public void OnLiked(int id) {
                presenter.likeCampaigners(id);
            }
        };
        StaggeredGridLayoutManager linearLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void initUi(final Course course){
        setContentView(R.layout.course_landing_page);
        userName = findViewById(R.id.username);
        if(MyApplication.getInstance().prefManager.isLoggedIn()){
            new CurrentUserPresenter(this,this).getCurrentUser();
        }
        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.am_recycler_view);

        //view all
        viewAllBlogs = findViewById(R.id.view_career_blogs);
        viewAllCareer = findViewById(R.id.view_all_career);
        viewAllCourses = findViewById(R.id.view_courses);
        viewAllMentors = findViewById(R.id.view_all_mentors);


        courseName = findViewById(R.id.courseName);
        presenter.getCampaigners(mentorListener);
        tootitle = findViewById(R.id.tooltilte);
        trendingRecyclerView = findViewById(R.id.career_recyclerview);
       // courseImage = findViewById(R.id.course_icon);
        popularityPercent = findViewById(R.id.popularity_percent);
        courseDesc = findViewById(R.id.goal1);
        learnMore = findViewById(R.id.learnmore);
        courseTagline = findViewById(R.id.course_tagLine);
        ratingText = findViewById(R.id.ratingtext);
        //ratingBar = findViewById(R.id.rating);
        actualprice =findViewById(R.id.actualprice);
        cutRate = findViewById(R.id.cutrate);
        modeImage= findViewById(R.id.modeImage);
        offPercent =findViewById(R.id.offpercentage);
        mode= findViewById(R.id.mode);
        numLikes = findViewById(R.id.num_likes);
        registerButton = findViewById(R.id.registerButton);
        rupee = findViewById(R.id.rupee);
        rupee2 = findViewById(R.id.rupee2);
        courseDuration = findViewById(R.id.duration);
        university =findViewById(R.id.university);
        courseShortname=findViewById(R.id.courseShortName);
        cardView = (LinearLayout)findViewById(R.id.linearLayout);

        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collegeRecyclerView = findViewById(R.id.college_recycler_view);
        similarCourseRecycler = findViewById(R.id.similar_courses_recyclerview);
        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        topLayout = findViewById(R.id.appbar);
        Double height=displayMetrics.heightPixels/(3.2);
        topLayout.getLayoutParams().height=height.intValue();
        topLayout.getLayoutParams().width=displayMetrics.widthPixels;


        presenter.getArticles(listener);
        courseName.setText(course.getName());
        tootitle.setText(course.getName());
//        Picasso.with(this)
//                .load(course.getImage())
//                .into(courseImage);
        popularityPercent.setText(course.getPopularity()+"%");
        courseDesc.setText(course.getDescription());
        courseTagline.setText(course.getTagline());
        numLikes.setText(course.getNumLikes()+"");

        viewAllMentors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseLandingPage.this, AmbassdorsActivity.class));
            }
        });
        viewAllCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CourseLandingPage.this, AllCoursesActivity.class);
                myIntent.putExtra("LIST", (Serializable) course.getRelatedCourses());
                myIntent.putExtra("landing","yes");
                startActivity(myIntent);
            }
        });
        viewAllCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CourseLandingPage.this, CareerActivity.class);
                myIntent.putExtra("LIST", (Serializable) course.getCareerOptions());
                myIntent.putExtra("landing","yes");
                startActivity(myIntent);
            }
        });
        viewAllBlogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CourseLandingPage.this, TrendingArticlesActivity.class);
                myIntent.putExtra("landing","yes");
                startActivity(myIntent);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseLandingPage.this, AptitudeActivity.class));
            }
        });
        if(course.getIsFree()!=null)
            if(!course.getIsFree()) {
                if (course.getDiscount() != null) {
                    discountPercent = course.getDiscount();
                    discountedPrice = course.getFee();
                    actualPrice = (100*discountedPrice)/(100-discountPercent);
                    actualprice.setText((int)Math.round(course.getFee())+"");
                    offPercent.setText(course.getDiscount() + "% off");
                    cutRate.setText((int)Math.round(actualPrice) + "");
                } else {
                    if (course.getFee() != null) {
                        offPercent.setVisibility(View.GONE);
                        cutRate.setVisibility(View.GONE);
                        actualprice.setText(course.getFee() + "");
                    }
                    else{
                        actualprice.setText("PAID");
                        offPercent.setVisibility(View.GONE);
                        cutRate.setVisibility(View.GONE);
                        rupee.setVisibility(View.GONE);
                        rupee2.setVisibility(View.GONE);
                    }
                }
            }
            else{
                offPercent.setVisibility(View.GONE);
                cutRate.setVisibility(View.GONE);
                actualprice.setText("FREE");
                rupee.setVisibility(View.GONE);
                rupee2.setVisibility(View.GONE);
            }
        if(course.getRating()!=null){
            double d = course.getRating();
            int rating = (int)d;
            ratingText.setText(course.getRating()+"");
           // ratingBar.setRating(rating);
        }
        if(course.getDuration()!=null){
            courseDuration.setText(course.getDuration());
        }
        if(course.getCourseType()!=null){
            Log.d("coursetype",course.getCourseType());
            if(course.getCourseType().equals("Online")){
                Picasso.get()
                        .load(R.drawable.ic_online)
                        .into(modeImage);
            }
            else
            {
                Picasso.get()
                        .load(R.drawable.ic_offline)
                        .into(modeImage);
            }
            mode.setText(course.getCourseType());
        }
        if(course.getUniversity()!=null){
            university.setText(course.getUniversity().getName());
        }

        if(course.getName()!=null){
            courseShortname.setText(course.getSchool().get(0).getName());
        }
        setCollegeRecyclerView(course.getCareerOptions());
        if(course.getRelatedCourses().size()>1) {
            if(course.getRelatedCourses().size()<=3)
            setTopCourses(course.getRelatedCourses().subList(0, course.getRelatedCourses().size() - 1));
            else{
                setTopCourses(course.getRelatedCourses().subList(0,2));
            }
        }
        else if(course.getRelatedCourses().size()==1)
        {
            // hide the layout
        }
        learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CourseLandingPage.this,OverviewActivity.class);
                i.putExtra("coursename",course.getName());
                i.putExtra("courseimage",course.getImage());
                i.putExtra("courseoverview",course.getDescription());
                startActivity(i);
            }
        });
    }
    public void setCollegeRecyclerView(List<CareerOption> careerOptions) {


        collegeRecyclerView.setHasFixedSize(true);
        collegeRecyclerView.removeAllViews();
        collegeRecyclerView.setNestedScrollingEnabled(false);
        collegeRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        collegeRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        subjectsAdapter = new CareerOptionsAdapter(this,careerOptions,displayMetrics);
        collegeRecyclerView.setAdapter(subjectsAdapter);
        collegeRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        subjectsAdapter.notifyDataSetChanged();
    }
    public void setTopCourses(List<RelatedCourse> courses)
    {
        topCoursesItemAdapter=new RelatedCoursesAdapter(this,courses);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        similarCourseRecycler.setNestedScrollingEnabled(false);
        similarCourseRecycler.setLayoutManager(layoutManager);
        similarCourseRecycler.setAdapter(topCoursesItemAdapter);
        topCoursesItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void onUserReceived(User user) {
        if(userName!=null){
            userName.setText("Hi "+user.getFirstName()+",");
        }
    }
}
