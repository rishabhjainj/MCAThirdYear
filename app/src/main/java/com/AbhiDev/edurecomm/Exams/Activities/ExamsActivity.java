package com.AbhiDev.edurecomm.Exams.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.wireout.Activities.BaseActivity;
import com.wireout.Activities.MainActivity;
import com.wireout.Exams.CategoryModel;
import com.wireout.Exams.ExamOptionModel;
import com.wireout.Exams.ExamPresenter;
import com.wireout.Exams.ExamViewAction;
import com.wireout.R;
import com.wireout.adapters.CareerOptionsAdapter;
import com.wireout.adapters.ExamsAdapter;
import com.wireout.apiservices.Repository;
import com.wireout.listeners.OnEntitiesReceivedListener;

import java.util.List;

public class ExamsActivity extends BaseActivity implements ExamViewAction {

    List<ExamOptionModel> examList;
    ExamsAdapter adapter;
    ExamPresenter presenter;
    ImageView backBtn;
    RecyclerView careerOptionsRecyclerView;
    List<CategoryModel> categories;
    OnEntitiesReceivedListener<CategoryModel> listener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        listener  = new OnEntitiesReceivedListener<CategoryModel>(this) {
            @Override
            public void onReceived(List<CategoryModel> entities) {
                categories = entities;
                setCategoriesRecyclerview(entities);
            }
        };
        presenter= new ExamPresenter(this, new Repository());
        presenter.getExamCategories(listener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExamsActivity.this,MainActivity.class));
                finish();
            }
        });



    }
    public void setCategoriesRecyclerview(List<CategoryModel> categories){
        careerOptionsRecyclerView = findViewById(R.id.career_recyclerview);
        careerOptionsRecyclerView.setHasFixedSize(true);
        careerOptionsRecyclerView.removeAllViews();
        careerOptionsRecyclerView.setNestedScrollingEnabled(false);
        careerOptionsRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new ExamsAdapter(this,categories);
        careerOptionsRecyclerView.setAdapter(adapter);
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                startActivity(new Intent(ExamsActivity.this,MainActivity.class));
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ExamsActivity.this,MainActivity.class));
        finish();

    }
}
