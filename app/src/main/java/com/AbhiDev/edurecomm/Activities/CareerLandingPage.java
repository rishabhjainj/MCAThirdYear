package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.AbhiDev.edurecomm.R;
import com.AbhiDev.edurecomm.ViewMore.AmbassdorsActivity;
import com.AbhiDev.edurecomm.ViewMore.TrendingUniversitiesActivity;
import com.AbhiDev.edurecomm.adapters.CareerBlogAdapter;
import com.AbhiDev.edurecomm.adapters.CareerOptionsAdapter;
import com.AbhiDev.edurecomm.adapters.InstitutesAdapter;
import com.AbhiDev.edurecomm.adapters.LandingPageMentorAdapter;
import com.AbhiDev.edurecomm.adapters.RelatedCoursesAdapter;
import com.AbhiDev.edurecomm.apiservices.Repository;
import com.AbhiDev.edurecomm.common.MyApplication;
import com.AbhiDev.edurecomm.listeners.OnEntitiesReceivedListener;
import com.AbhiDev.edurecomm.listeners.OnUserReceivedListener;
import com.AbhiDev.edurecomm.models.Articles;
import com.AbhiDev.edurecomm.models.Career;
import com.AbhiDev.edurecomm.models.CareerList;
import com.AbhiDev.edurecomm.models.CareerOption;
import com.AbhiDev.edurecomm.models.EducationalRequirement;
import com.AbhiDev.edurecomm.models.Institution;
import com.AbhiDev.edurecomm.models.Mentor;
import com.AbhiDev.edurecomm.models.PersonalQuality;
import com.AbhiDev.edurecomm.models.RelatedCourse;
import com.AbhiDev.edurecomm.models.School;
import com.AbhiDev.edurecomm.models.User;
import com.AbhiDev.edurecomm.presenters.CareerPresenter;
import com.AbhiDev.edurecomm.viewactions.CareerViewAction;
import com.wireout.common.MyApplication;
import com.wireout.listeners.OnUserReceivedListener;
import com.wireout.models.CareerList;
import com.wireout.models.User;
import com.wireout.presenters.CurrentUserPresenter;
import com.wireout.R;

import com.wireout.ViewMore.AmbassdorsActivity;
import com.wireout.ViewMore.TrendingUniversitiesActivity;
import com.wireout.adapters.CareerBlogAdapter;
import com.wireout.adapters.CareerOptionsAdapter;
import com.wireout.adapters.InstitutesAdapter;
import com.wireout.adapters.LandingPageMentorAdapter;
import com.wireout.adapters.RelatedCoursesAdapter;
import com.wireout.apiservices.Repository;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Articles;
import com.wireout.models.Career;
import com.wireout.models.CareerOption;
import com.wireout.models.EducationalRequirement;
import com.wireout.models.Institution;
import com.wireout.models.Mentor;
import com.wireout.models.PersonalQuality;
import com.wireout.models.RelatedCourse;
import com.wireout.models.School;
import com.wireout.presenters.CareerPresenter;
import com.wireout.viewactions.CareerViewAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CareerLandingPage extends BaseActivity implements CareerViewAction, OnUserReceivedListener {
    ImageView backBtn;
    RecyclerView careerOptionsRecyclerView;
    RelatedCoursesAdapter topCoursesItemAdapter;
    RecyclerView popularCoursesRecyclerView;
    TextView userName;
    TextView careerName;
    RecyclerView institutesRecyclerView;
    OnEntitiesReceivedListener<Mentor> mentorListener;
    TextView learnMore;
    CareerOptionsAdapter subjectsAdapter;
    DisplayMetrics displayMetrics;
    List<String> subjects;
    TextView viewAllCourses,viewAllCareer,viewAllMentors,viewAllBlogs,viewInstitutes;
    TextView tootitle;
    RecyclerView trendingRecyclerView;
    CareerBlogAdapter wikipediaAdapter;
    List<String> courses;
    FloatingActionButton floatingActionButton;
    RelativeLayout topLayout;
    CareerPresenter presenter;
    InstitutesAdapter institutesAdapter;
    TextView careerPopPercentage;
    TextView careerPopText;
    RecyclerView recyclerView;
    TextView careerCategory;
    TextView careerSalary;
    TextView careerEmpOpp;
    TextView careerEduReq;
    LandingPageMentorAdapter adapter;
    OnEntitiesReceivedListener<Articles> listener;
    TextView careerPersonalQual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        presenter = new CareerPresenter(this,new Repository());
        if(getIntent().getStringExtra("id")!=null)
            presenter.getCareerById(getIntent().getStringExtra("id"));
        else
            showMessage("no");
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

    @Override
    public void addToRecyclerView(List<CareerList> careers) {

    }

    @Override
    public void initUi(final Career career) {
        setContentView(R.layout.career_landing_page);
        userName = findViewById(R.id.username);
        recyclerView = findViewById(R.id.am_recycler_view);
        floatingActionButton = findViewById(R.id.fab);
        careerName = findViewById(R.id.career_name);
        presenter.getCampaigners(mentorListener);
        backBtn = findViewById(R.id.backbtn);
        careerPopPercentage = findViewById(R.id.poppercentage);
        careerCategory = findViewById(R.id.category_text);
        careerEduReq = findViewById(R.id.edu_req_text);
        careerPopText = findViewById(R.id.poptext);
        careerSalary = findViewById(R.id.salary_text);
        careerEmpOpp = findViewById(R.id.employment_text);
        careerPersonalQual = findViewById(R.id.personal_text);
        trendingRecyclerView  = findViewById(R.id.career_recyclerview);
        tootitle = findViewById(R.id.tooltilte);

        if(MyApplication.getInstance().prefManager.isLoggedIn())
        {
            new CurrentUserPresenter(this,this).getCurrentUser();
        }

        presenter.getArticles(listener);
        tootitle.setText(career.getName());


        careerName.setText(career.getName());
        careerPopText.setText(career.getDescription());
        careerPopPercentage.setText(career.getPopularity()+"%");
        careerSalary.setText(career.getSalary());

        viewAllBlogs = findViewById(R.id.view_career_blogs);
        viewAllCareer = findViewById(R.id.view_all_career);
        viewAllCourses = findViewById(R.id.view_all_courses);
        viewAllMentors = findViewById(R.id.view_all_mentors);
        viewInstitutes= findViewById(R.id.view_all_inst);


        viewAllBlogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CareerLandingPage.this, TrendingArticlesActivity.class);
                myIntent.putExtra("landing","yes");
                startActivity(myIntent);
            }
        });
        viewAllCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CareerLandingPage.this, CareerActivity.class);
                myIntent.putExtra("LIST", (Serializable) career.getRelatedCareerOptions());
                myIntent.putExtra("landing","yes");
                startActivity(myIntent);
            }
        });
        viewAllCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CareerLandingPage.this, AllCoursesActivity.class);
                myIntent.putExtra("LIST", (Serializable) career.getCourses());
                myIntent.putExtra("landing","yes");
                startActivity(myIntent);
            }
        });
        viewAllMentors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CareerLandingPage.this, AmbassdorsActivity.class));
            }
        });
        viewInstitutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(CareerLandingPage.this, TrendingUniversitiesActivity.class);
                myIntent.putExtra("LIST", (Serializable) career.getInstitutions());
                myIntent.putExtra("landing","yes");
                startActivity(myIntent);
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CareerLandingPage.this, AptitudeActivity.class));
            }
        });
        List<String> personalQual = new ArrayList<>();
        for(PersonalQuality personalQuality : career.getPersonalQualities()){
            personalQual.add(personalQuality.getName());
        }
        StringBuilder personalQualBuilder = new StringBuilder();

        for(String names : personalQual){
            personalQualBuilder.append(names);
            personalQualBuilder.append(",");
        }


        String personalQualString = personalQualBuilder.toString();
        careerPersonalQual.setText(personalQualString.substring(0,personalQualString.length()-1));


        List<String> schoolNames = new ArrayList<>();
        for(School school : career.getSchools()){
            schoolNames.add(school.getName());
        }
        StringBuilder csvBuilder = new StringBuilder();

        for(String names : schoolNames){
            csvBuilder.append(names);
            csvBuilder.append(",");
        }

        String stringSchoolNames = csvBuilder.toString();
        careerCategory.setText(stringSchoolNames.substring(0,stringSchoolNames.length()-1));

        List<String> eduRequirements = new ArrayList<>();
        for(EducationalRequirement educationalRequirement : career.getEducationalRequirements()){
            eduRequirements.add(educationalRequirement.getName());
        }
        StringBuilder eduReqBuilder = new StringBuilder();
        for(String edu : eduRequirements){
            eduReqBuilder.append(edu);
            eduReqBuilder.append(",");
        }
        String eduReqString = eduReqBuilder.toString();
        careerEduReq.setText(eduReqString.substring(0,eduReqString.length()-1));
        careerEmpOpp.setText(career.getEmployment());



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        popularCoursesRecyclerView = findViewById(R.id.popular_courses_recyclerView);
        careerOptionsRecyclerView = findViewById(R.id.college_recycler_view);
        institutesRecyclerView = findViewById(R.id.institutions_recyclerview);
        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        topLayout = findViewById(R.id.appbar);
        Double height=displayMetrics.heightPixels/(3.2);
        topLayout.getLayoutParams().height=height.intValue();
        topLayout.getLayoutParams().width=displayMetrics.widthPixels;

        setCareerOptionsRecyclerView(career.getRelatedCareerOptions());

        if(career.getCourses().size()>3)
            setRelatedCoursesRecyclerView(career.getCourses().subList(0,2));
        else
            setRelatedCoursesRecyclerView(career.getCourses());
        setInstitutesRecyclerView(career.getInstitutions());
    }

    public void setCareerOptionsRecyclerView(List<CareerOption> careerOptions) {



        careerOptionsRecyclerView.setHasFixedSize(true);
        careerOptionsRecyclerView.removeAllViews();
        careerOptionsRecyclerView.setNestedScrollingEnabled(false);
        careerOptionsRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        subjectsAdapter =
                new CareerOptionsAdapter(this,careerOptions,displayMetrics);
        careerOptionsRecyclerView.setAdapter(subjectsAdapter);
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        subjectsAdapter.notifyDataSetChanged();

    }
    private void setInstitutesRecyclerView(List<Institution> institutions) {




        institutesRecyclerView.hasFixedSize();
        institutesRecyclerView.removeAllViews();
        institutesRecyclerView.setNestedScrollingEnabled(false);
        institutesRecyclerView.removeAllViewsInLayout();
        institutesAdapter=new InstitutesAdapter(this,institutions);
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        institutesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        institutesRecyclerView.setHasFixedSize(true);
        institutesRecyclerView.setAdapter(institutesAdapter);

    }
    public void setRelatedCoursesRecyclerView(List<RelatedCourse> courses)
    {
//
        popularCoursesRecyclerView.hasFixedSize();
        popularCoursesRecyclerView.removeAllViews();
        popularCoursesRecyclerView.setNestedScrollingEnabled(false);
        popularCoursesRecyclerView.removeAllViewsInLayout();
//
        topCoursesItemAdapter=new RelatedCoursesAdapter(this,courses);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        popularCoursesRecyclerView.setLayoutManager(layoutManager);
        popularCoursesRecyclerView.setAdapter(topCoursesItemAdapter);
        topCoursesItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void onUserReceived(User user) {
        if(userName!=null){
            userName.setText("Hi "+user.getFirstName()+",");
        }
    }

    @Override
    public void initUi(Career career, Boolean hide) {

    }
}
