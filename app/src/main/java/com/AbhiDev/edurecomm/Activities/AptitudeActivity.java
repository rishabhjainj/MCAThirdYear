package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.wireout.Activities.Analysis.DetailMenu;
import com.wireout.R;
import com.wireout.adapters.AptitudeAdapter3;
import com.wireout.adapters.RecommendedCourseAdapter;
import com.wireout.adapters.ShortTermAdapter;
import com.wireout.common.MyApplication;
import com.wireout.common.PrefManager;
import com.wireout.listeners.OnUserReceivedListener;
import com.wireout.models.CourseList;
import com.wireout.models.Library;
import com.wireout.models.ShortTermCourse;
import com.wireout.models.User;
import com.wireout.presenters.CurrentUserPresenter;
import com.wireout.presenters.ShortTermCoursesPresesnter;
import com.wireout.presenters.TrendingCoursesPresenter;
import com.wireout.repositories.ShortTermCoursesRepository;
import com.wireout.repositories.TrendingCoursesRepository;
import com.wireout.viewactions.ShortTermCoursesViewAction;
import com.wireout.viewactions.TrendingCoursesViewAction;

import java.util.ArrayList;
import java.util.List;

public class AptitudeActivity extends BaseActivity implements TrendingCoursesViewAction, ShortTermCoursesViewAction,OnUserReceivedListener {

    RecyclerView recyclerView, recyclerView2, recyclerView3;

    ArrayList<String> texts;
    TrendingCoursesPresenter presenter;
    ShortTermCoursesPresesnter presesnter2;
    Switch aSwitch;
    CurrentUserPresenter userPresenter;
    CardView framework;
    TextView personalized, library;
    TextView start;
    TextView viewMoreSt, viewMoreTrending;
    CardView careerProcess;
    RecyclerView recyclerViewSt, recyclerViewTrending;
    RelativeLayout relative3,topLayout,startlayout;
    CardView cardView1,relative2;
    LinearLayout relative1;
    ShortTermAdapter adapter;
    DisplayMetrics displayMetrics;
    TextView user;
    RecommendedCourseAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude);
        framework = findViewById(R.id.framework);
        careerProcess = findViewById(R.id.career_process);
        framework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AptitudeActivity.this,FrameworkIntroScreenActivity.class));
            }
        });

        careerProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AptitudeActivity.this,CareerProcessActivity.class));
            }
        });
//        recyclerView=findViewById(R.id.recyclerviewApt1);
//        recyclerView2=findViewById(R.id.recyclerviewApt2);
        aSwitch=findViewById(R.id.switchApt);
        startlayout = findViewById(R.id.startLayout);
        topLayout = findViewById(R.id.top_layout);
        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        topLayout.getLayoutParams().height = displayMetrics.heightPixels/3;
        topLayout.getLayoutParams().width = displayMetrics.widthPixels;

        startlayout.getLayoutParams().height = displayMetrics.heightPixels/8;
        startlayout.getLayoutParams().width = displayMetrics.widthPixels;

        user = findViewById(R.id.user);
        recyclerView3=findViewById(R.id.recyclerviewApt3);
        start = (TextView)findViewById(R.id.tvStartApt);
        personalized=findViewById(R.id.tvPersonlized);
        library=findViewById(R.id.tvLibrary);
        recyclerViewSt=findViewById(R.id.stRecyclerViewApt);
        recyclerViewTrending=findViewById(R.id.coursesRecyclerViewApt);
        cardView1=findViewById(R.id.cardApt1);
        viewMoreSt=findViewById(R.id.tvViewMoreCorsesApt);
        viewMoreTrending=findViewById(R.id.tvViewMoreTrendingCoursesApt);

        relative1=findViewById(R.id.linearLayout);
        relative2=findViewById(R.id.career_process);

        userPresenter = new CurrentUserPresenter(this,this);
        userPresenter.getCurrentUser();
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Notification");

        presenter=new TrendingCoursesPresenter(new TrendingCoursesRepository(),getApplicationContext(),this);
        presesnter2=new ShortTermCoursesPresesnter(this,getApplicationContext(),new ShortTermCoursesRepository());

//        cardView1.setVisibility(View.GONE);
////        cardView2.setVisibility(View.GONE);
////        cardView3.setVisibility(View.GONE);
        relative1.setVisibility(View.VISIBLE);


//        viewMoreSt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ShortTermCoursesActivity.class));
//            }
//        });
//
//        viewMoreTrending.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), TrendingCoursesActivity.class));
//            }
//        });

        recyclerView3.setVisibility(View.GONE);

//        presenter.getCourses();
//        presesnter2.getShortTermCourses();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn())
                startActivity(new Intent(AptitudeActivity.this, CareerAssesmentStartActivity.class));//change here
                   //startActivity(new Intent(AptitudeActivity.this, DetailMenu.class));//change here

                else{
                    showMessage("You need to login first to begin with Career Assessment.");
                    startActivity(new Intent(AptitudeActivity.this, LoginActivity.class));
                }
//                Intent i = new Intent(AptitudeActivity.this, AnalysisActivity.class);
//                i.putExtra("state","0");
//                startActivity(i);
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Toast.makeText(getApplicationContext(),"Switch state: "+b,Toast.LENGTH_LONG).show();
                if (b==true)
                {
                    library.setEnabled(true);
                    personalized.setEnabled(false);
//                    cardView1.setVisibility(View.GONE);
                    relative1.setVisibility(View.GONE);
                    recyclerView3.setVisibility(View.VISIBLE);
                    List<Library> libraries=new ArrayList<>();
                    Library library1=new Library();
                    library1.setTopic("History & Goals");
                    Library library2=new Library();
                    library2.setTopic("Me Or Not Me");

                    Library library3=new Library();
                    library3.setTopic("Ability");

//                    Library library4=new Library();
//                    library4.setTopic("Inequalities, Modulus and Logarithms");

                    Library library5=new Library();
                    library5.setTopic("Interest");

                    Library library6=new Library();
                    library6.setTopic("Your Egogram");

                    Library library7=new Library();
                    library7.setTopic("Life Choices");

                    Library library8 =new Library();
                    library8.setTopic("Verbal Ability");

                    Library library9 =new Library();
                    library9.setTopic("Brain Teaser");

                    Library library10 =new Library();
                    library10.setTopic("Emotional Intelligence");

                    Library library11 =new Library();
                    library11.setTopic("Flexibility Game");

                    Library library12 =new Library();
                    library12.setTopic("Quick Maths");

                    Library library13 =new Library();
                    library13.setTopic("Motivational Quotient");

                    Library library14 =new Library();
                    library14.setTopic("Handwriting Analysis");


                    libraries.add(library1);
                    libraries.add(library2);
                    libraries.add(library3);
                    libraries.add(library5);
                    libraries.add(library6);
                    libraries.add(library7);
                    libraries.add(library8);
                    libraries.add(library9);
                    libraries.add(library10);
                    libraries.add(library11);
                    libraries.add(library12);
                    libraries.add(library13);
                    libraries.add(library14);


                    AptitudeAdapter3 aptitudeAdapter3=new AptitudeAdapter3(getApplicationContext(),AptitudeActivity.this,libraries);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
                    recyclerView3.setLayoutManager(linearLayoutManager);
                    recyclerView3.setAdapter(aptitudeAdapter3);


                }
                else if (b==false)
                {
                    library.setEnabled(false);
                    personalized.setEnabled(true);
                    //aSwitch.setBackgroundColor(Color.WHITE);
                    recyclerView3.setVisibility(View.GONE);
//                    cardView1.setVisibility(View.VISIBLE);
                    relative1.setVisibility(View.VISIBLE);


//                    presenter.getCourses();
//                    presesnter2.getShortTermCourses();

                }

            }
        });

    }

    @Override
    public void onUserReceived(User user) {
        this.user.setText(user.getFirstName());
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
    public void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void setTrendingCoursesRecyclerView(List<CourseList> courseList) {
        recyclerViewTrending.setHasFixedSize(true);
        recyclerViewTrending.removeAllViews();
        recyclerViewTrending.setNestedScrollingEnabled(false);
        recyclerViewTrending.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        recyclerViewTrending.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter2=new RecommendedCourseAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),courseList)
        {
            @Override
            public void like(int id) {
                presenter.likeCourse(id);
            }
        };
        recyclerViewTrending.setAdapter(adapter2);
        recyclerViewTrending.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter2.notifyDataSetChanged();
    }


    @Override
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourseList) {
        recyclerViewSt.setHasFixedSize(true);
        recyclerViewSt.removeAllViews();
        recyclerViewSt.setNestedScrollingEnabled(false);
        recyclerViewSt.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        recyclerViewSt.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter=new ShortTermAdapter(new PrefManager(getApplicationContext()),getApplicationContext(),shortTermCourseList)
        {
            @Override
            public void like(int id) {
                presesnter2.likeShortTermCourse(id);
            }
        };

        recyclerViewSt.setAdapter(adapter);
        recyclerViewSt.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void startVideos() {
        startActivity(new Intent(this, VideosActivity.class));
    }
}
