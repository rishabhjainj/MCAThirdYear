package com.AbhiDev.edurecomm.ViewMore;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;


import com.wireout.Activities.BaseActivity;
import com.wireout.adapters.ViewMoreCareerAdapter;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.R;
import com.wireout.adapters.InstitutesAdapter;
import com.wireout.adapters.RecommendedUniAdapter;
import com.wireout.apiservices.Repository;
import com.wireout.models.Institution;
import com.wireout.models.University;
import com.wireout.presenters.TrendingUniversitryPresenter;
import com.wireout.viewactions.TrendingUniversitiesViewAction;

import java.util.ArrayList;
import java.util.List;

public class TrendingUniversitiesActivity extends BaseActivity implements TrendingUniversitiesViewAction{

    RecyclerView universityRecyclerView;
    RecommendedUniAdapter adapter;
    TrendingUniversitryPresenter presenter;
    DisplayMetrics displayMetrics;
    String choice;
    InstitutesAdapter institutesAdapter;
     int index = 1;
    University universities;
    ArrayList<University> universityArrayList;
    List<Institution> universityList;
    OnEntitiesReceivedListener<University> moreUniversities;
    OnEntitiesReceivedListener<University> listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);

        universityArrayList = new ArrayList<>();
        universityRecyclerView=(RecyclerView)findViewById(R.id.recyclerView_viewMore);

        presenter=new TrendingUniversitryPresenter(this,getApplicationContext(),new Repository());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        choice = getIntent().getExtras().get("landing")+"";
        switch (choice){
            case "yes":
                universityList = (List<Institution>) getIntent().getSerializableExtra("LIST");
                setInstitutesRecyclerView(universityList);
                break;
            case "no":
                listener = new OnEntitiesReceivedListener<University>(this) {
                    @Override
                    public void onReceived(List<University> entities) {
                        universityArrayList.addAll(entities);
                        setTrendingUniversitiesRecyclerView(entities);
                    }
                };
                presenter.getUniversities(listener);
                break;
        }

        moreUniversities = new OnEntitiesReceivedListener<University>(this) {
            @Override
            public void onReceived(List<University> entities) {
                addToRecyclerView(entities);
            }
        };
    }


    @Override
    public void addToRecyclerView(List<University> universities) {
        if(adapter!=null){
            universityArrayList.addAll(universities);
            adapter.notifyDataChanged();
            if(universities.size()==20){
//                showMessage("setmoredata");
                adapter.setMoreDataAvailable(true);
            }
            else{
                showMessage("not av");
                adapter.setMoreDataAvailable(false);
            }

            adapter.setLoadMoreListener(new ViewMoreCareerAdapter.OnLoadMoreListener(){

                @Override
                public void onLoadMore() {
                    universityRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            index++;
                            presenter.loadMoreUniversities(index,moreUniversities);// a method which requests remote data
                        }
                    });

                }
            });

        }
    }

    private void setInstitutesRecyclerView(List<Institution> institutions) {

        universityRecyclerView.hasFixedSize();
        universityRecyclerView.removeAllViews();
        universityRecyclerView.setNestedScrollingEnabled(false);
        universityRecyclerView.removeAllViewsInLayout();

        institutesAdapter=new InstitutesAdapter(this,institutions);
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        universityRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        universityRecyclerView.setHasFixedSize(true);
        universityRecyclerView.setAdapter(institutesAdapter);

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
    public void setTrendingUniversitiesRecyclerView(final List<University> universityDataList) {
        universityArrayList.clear();
        universityArrayList.addAll(universityDataList);
        universityRecyclerView.setHasFixedSize(true);
        universityRecyclerView.removeAllViews();
        universityRecyclerView.setNestedScrollingEnabled(false);
        universityRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        universityRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new RecommendedUniAdapter(this,universityArrayList,displayMetrics){
                    @Override
                    public void like(int id) {
                        presenter.likeUniversity(id);
                    }
                };
        adapter.setLoadMoreListener(new ViewMoreCareerAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                universityRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        if(universityDataList.size()==20) {
                            index++;
                            presenter.loadMoreUniversities(index, moreUniversities);
                        }// a method which requests remote data
                    }
                });
            }
        });
        universityRecyclerView.setAdapter(adapter);
        universityRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
    }
}
