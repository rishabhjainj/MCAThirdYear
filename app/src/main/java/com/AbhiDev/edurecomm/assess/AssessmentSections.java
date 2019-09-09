package com.AbhiDev.edurecomm.assess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class AssessmentSections extends BaseActivity {

    SectionAdapter adapter;
    ExamPresenter presenter;
    ImageView backBtn;
    Intent i ;
    TagAdapter tagAdapter;
    TextView subjectText;
    String category;
    Assessment assessment;
    RecyclerView tagRecyclerView;
    List<Section> sections;
    RecyclerView careerOptionsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);
        i = new Intent();
        i = getIntent();
        subjectText = findViewById(R.id.subject_text);
        tagRecyclerView = findViewById(R.id.tag_recyclerview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTagsRecyclerView();
        careerOptionsRecyclerView = findViewById(R.id.career_recyclerview);
        assessment =  (Assessment)i.getSerializableExtra("LIST");
        sections = assessment.getSections();
        category = i.getStringExtra("section");
        subjectText.setText(category);
        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setSectionsRecyclerView(sections);

    }
    public void setTagsRecyclerView(){
        tagRecyclerView.setHasFixedSize(true);
        tagRecyclerView.removeAllViews();
        tagRecyclerView.setNestedScrollingEnabled(false);
        tagRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        tagRecyclerView.setLayoutManager(layoutManager);
        List<String> tags = new ArrayList<>();
        //List<Tag> tagList = assessment.getTags();

        tags.add("Logical Reasoning");
        tags.add("Aptitude");
        tags.add("Data Interpretation");
        tags.add("English & Vocabulary");
        tags.add("Quantitative Aptitude");

        tagAdapter =
                new TagAdapter(tags,this);
        tagRecyclerView.setAdapter(tagAdapter);
        tagRecyclerView.setLayoutManager(layoutManager);

        tagAdapter.notifyDataSetChanged();
    }
    public void setSectionsRecyclerView(List<Section> sections){
        careerOptionsRecyclerView.setHasFixedSize(true);
        careerOptionsRecyclerView.removeAllViews();
        careerOptionsRecyclerView.setNestedScrollingEnabled(false);
        careerOptionsRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new SectionAdapter(this,sections);
        careerOptionsRecyclerView.setAdapter(adapter);
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
    }
}
