package com.AbhiDev.edurecomm.Internships;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.wireout.Activities.BaseActivity;
import com.wireout.Activities.MainActivity;
import com.wireout.Activities.WishlistActivity;
import com.wireout.Exams.Activities.ExamSubCategoryListActivity;
import com.wireout.Exams.Activities.ExamsActivity;
import com.wireout.Exams.ExamPresenter;
import com.wireout.Exams.ExamViewAction;
import com.wireout.Exams.TagAdapter;
import com.wireout.R;
import com.wireout.adapters.ExamListAdapter;
import com.wireout.apiservices.Repository;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.CareerList;
import com.wireout.models.CategoryList;
import com.wireout.models.CourseList;
import com.wireout.models.Institution;
import com.wireout.models.Internships;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.presenters.SearchPresenter;
import com.wireout.viewactions.BaseViewAction;
import com.wireout.viewactions.SearchViewAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InternshipListActivity extends BaseActivity implements InternshipViewAction, SearchView.OnQueryTextListener {
    InternshipsAdapter adapter;
    TagAdapter tagAdapter;
    OnEntitiesReceivedListener<Internships> listener;
    InternshipPresenter presenter;
    ImageView backBtn;
    String mQueryString;
    Handler handler;
    ImageView wishlist;
    TextView subjectText;
    Map<String,String> queryMap;
    SearchView searchView;
    String category;
    RecyclerView tagRecyclerView;
    RecyclerView careerOptionsRecyclerView;
    ExamSubCategory exam;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        category = getIntent().getStringExtra("category");
        prefManager.saveInt("filterState",3);

        presenter = new InternshipPresenter(this,new Repository());
        listener = new OnEntitiesReceivedListener<Internships>(this) {
            @Override
            public void onReceived(List<Internships> entities) {

                //showMessage("recieved");
                initUi(entities);

            }
        };
        Map<String,String> query = new HashMap<>();
        presenter.getInternships(listener);


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        mQueryString=query;
        handler.removeCallbacksAndMessages(null);
       // progressBar.setVisibility(View.GONE);
        if(TextUtils.isEmpty(query)){
            presenter.getInternships(listener);
        }
        else{
            queryMap.put("search",query);
            presenter.searchInternships(queryMap);
        }

        return true;
    }

    @Override
    public void clearRecyclerView() {

        careerOptionsRecyclerView.removeAllViewsInLayout();
        careerOptionsRecyclerView.removeAllViews();
        if (adapter!=null)
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onQueryTextChange(final String newText) {
        mQueryString = newText;
        handler.removeCallbacksAndMessages(null);
        //progressBar.setVisibility(View.VISIBLE);
//
//       // presenter.applySearch(mQueryString);
        if(TextUtils.isEmpty(newText)){
           // addGenStaticData();
           // progressBar.setVisibility(View.GONE);
            clearRecyclerView();
            presenter.getInternships(listener);
        }
        else {

            if(!searchView.toString().equals(""))

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(TextUtils.getTrimmedLength(mQueryString)>0){
                            queryMap.put("search",newText);
                            Log.d("searchintent",newText);
                            presenter.searchInternships(queryMap);

                        }

                        else{

                        }

                    }
                }, 600);
        }
        return true;
    }


    @Override
    public void initUi(Internships internships) {

    }
    public void initUi(List<Internships> internships){
        setContentView(R.layout.rewards_layout);
        wishlist = findViewById(R.id.wishlist);
        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Search by role or interest");
        searchView.onActionViewExpanded();
        searchView.setIconified(true);
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.saveInt("filterState",3);
                startActivity(new Intent(InternshipListActivity.this,WishlistActivity.class));
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                searchView.clearFocus();
            }
        }, 30);
        handler = new android.os.Handler();
        assert searchView != null;
        searchView.setOnQueryTextListener(this);
        //tagRecyclerView = findViewById(R.id.tag_recyclerview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        queryMap = new HashMap<>();
        presenter = new InternshipPresenter(this,new Repository());
        careerOptionsRecyclerView = findViewById(R.id.internship_recyclerView);

        setInternshipRecyclerView(internships);

//        tagRecyclerView.setHasFixedSize(true);
//        tagRecyclerView.removeAllViews();
//        tagRecyclerView.setNestedScrollingEnabled(false);
//        tagRecyclerView.removeAllViewsInLayout();
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
//        tagRecyclerView.setLayoutManager(layoutManager);
//        List<String> tags = new ArrayList<>();
//        tags.add("Logical Reasoning");
//        tags.add("Aptitude");
//        tags.add("Data Interpretation");
//        tags.add("English & Vocabulary");
//        tags.add("Quantitative Aptitude");
//
//        tagAdapter =
//                new TagAdapter(tags,this);
//        tagRecyclerView.setAdapter(tagAdapter);
//        tagRecyclerView.setLayoutManager(layoutManager);
//
//        tagAdapter.notifyDataSetChanged();
        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InternshipListActivity.this,MainActivity.class));
                finish();
            }
        });
    }



    @Override
    public void setInternshipRecyclerView(List<Internships> internships){
        careerOptionsRecyclerView.setHasFixedSize(true);
        careerOptionsRecyclerView.removeAllViews();
        careerOptionsRecyclerView.setNestedScrollingEnabled(false);
        careerOptionsRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new InternshipsAdapter(this,internships){
                    @Override
                    public void OnLiked(int id) {
                        presenter.likeInternship(id);
                    }
                };
        careerOptionsRecyclerView.setAdapter(adapter);
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                startActivity(new Intent(InternshipListActivity.this,MainActivity.class));
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InternshipListActivity.this,MainActivity.class));
        finish();

    }

}
