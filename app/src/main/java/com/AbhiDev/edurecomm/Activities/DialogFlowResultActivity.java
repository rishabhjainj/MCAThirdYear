package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DialogFlowResultActivity extends SearchActivity {

    Intent i;
    RecyclerView recyclerView;
    Integer type;
    TextView title;
    String searchQuery;
    SearchPresenter presenter;
    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_flow);
        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.tooltilte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        i = getIntent();
        displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        presenter = new SearchPresenter(this, this, new Repository());
        type = Integer.parseInt(i.getExtras().getString("type"));
        searchQuery = i.getExtras().getString("search");
        String university = i.getExtras().getString("university");

        searchQuery = searchQuery.replaceAll("^\"|\"$", "");
        Log.d("searchintents",type+searchQuery);
        if(!searchQuery.equals("null")){
            queryMap.put("search", searchQuery);
            Log.d("searchintents",searchQuery+"");
        }
        else{
            searchQuery = "";
            queryMap.put("search"," ");
        }
        if(!university.equals("null")){
            university = university.replaceAll("^\"|\"$", "");
            queryMap.put("universities__name", university);
            if(!searchQuery.equals(""))
                searchQuery += " at " + university;
            else searchQuery = " at " + university;
        }

        Log.d("querymap", queryMap.toString());

        switch (type) {
            case 1:
                presenter.searchUniversity(queryMap);
               //title.setText("Here are some universities based on " +searchQuery);
                break;
            case 2:
                presenter.searchCourses(queryMap);
                String titleStr = "Here are some courses";
                if(!searchQuery.equals(""))
                    titleStr += "'"+searchQuery+"'";
                //title.setText(titleStr);
                break;
            case 3:
                presenter.searchShortTermCourses(queryMap);
                //title.setText("Here are some short term courses based on " + searchQuery);
                break;
            case 4:
                presenter.searchAmbassdors(queryMap);
                //title.setText("Here are some ambassdors based on "+searchQuery);
                break;
            default:
                showMessage("Error");
        }
    }

    @Override
    public void setUniversityRecyclerView(List<Institution> universityDataList) {
        recyclerView.hasFixedSize();
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();

        adapter=new InstitutesAdapter(this,universityDataList);
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public void setCoursesRecyclerView(List<CourseList> courses) {
        recyclerView.hasFixedSize();
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
//
        courseAdapter=new TopCoursesItemAdapter(this,courses);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
    }
    @Override
    public void setShortTermCoursesRecyclerView(List<CategoryList> shortTermCourses) {
        shortTermAdapter=new SchoolsAdapter(this,shortTermCourses);
        GridLayoutManager layoutManager=new GridLayoutManager(DialogFlowResultActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(shortTermAdapter);
    }
    @Override
    public void setCampaignersRecyclerView(List<CareerList> campaigners){
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        ambassdorsRecyclerViewAdapter =
                new ViewMoreCareerAdapter(this,campaigners,displayMetrics);
        recyclerView.setAdapter(ambassdorsRecyclerViewAdapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        ambassdorsRecyclerViewAdapter.notifyDataSetChanged();
    }
    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
