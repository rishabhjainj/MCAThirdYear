package com.AbhiDev.edurecomm.assess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wireout.Activities.Analysis.AnalysisStatusActivity;
import com.wireout.Activities.BaseActivity;
import com.wireout.Activities.CareerAssesmentStartActivity;
import com.wireout.R;
import com.wireout.adapters.AnalysisStatusAdapter;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.Assessment;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.models.career_analysis.Report;
import com.wireout.presenters.BooleanQuestionPresenter;
import com.wireout.viewactions.BaseViewAction;

import java.util.List;

public class TestSeriesActivity extends BaseActivity implements BaseViewAction {
    TestStatusAdapter adapter;
    RecyclerView analysisSectionItemsRecyclerView;
    List<Integer> sectionImages;
    List<String> sectionNames;
    Intent intent;
    ImageView backBtn;
    Button viewReportBtn;
    List<String> sectionTime;
    ImageView exitSection;

    OnEntitiesReceivedListener<Assessment> listener;
    TestPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        listener = new OnEntitiesReceivedListener<Assessment>(this) {
            @Override
            public void onReceived(List<Assessment> entities) {
                initUi(entities);
            }
        };
        presenter = new TestPresenter();
        presenter.getAssessmentSections(listener);

    }
    public void initUi(List<Assessment> assessments){
        setContentView(R.layout.activity_analysis_status);
        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        analysisSectionItemsRecyclerView = findViewById(R.id.analysis_sections_recycler_view);
        setAnalysisSectionItemsRecyclerView(assessments);
    }
    public void setAnalysisSectionItemsRecyclerView(List<Assessment> assessments){

        analysisSectionItemsRecyclerView.hasFixedSize();
        analysisSectionItemsRecyclerView.removeAllViews();
        analysisSectionItemsRecyclerView.setNestedScrollingEnabled(false);
        analysisSectionItemsRecyclerView.removeAllViewsInLayout();
        adapter=new TestStatusAdapter(this,assessments);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        analysisSectionItemsRecyclerView.setLayoutManager(layoutManager);
        analysisSectionItemsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
