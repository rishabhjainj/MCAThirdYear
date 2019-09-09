package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.AbhiDev.edurecomm.R;
import com.AbhiDev.edurecomm.adapters.CareerOptionsAdapter;
import com.AbhiDev.edurecomm.adapters.ViewMoreCareerAdapter;
import com.AbhiDev.edurecomm.apiservices.Repository;
import com.AbhiDev.edurecomm.listeners.OnEntitiesReceivedListener;
import com.AbhiDev.edurecomm.models.Career;
import com.AbhiDev.edurecomm.models.CareerList;
import com.AbhiDev.edurecomm.models.CareerOption;
import com.AbhiDev.edurecomm.presenters.CareerPresenter;
import com.AbhiDev.edurecomm.viewactions.CareerViewAction;

import java.util.ArrayList;
import java.util.List;

public class CareerActivity extends BaseActivity implements CareerViewAction {

    RecyclerView careerOptionsRecyclerView;
    ViewMoreCareerAdapter careerOptionsAdapter;
    DisplayMetrics displayMetrics;
    ArrayList<String> categoryIds= new ArrayList<>();
    ImageView filters;
    Intent intent;
    ArrayList<CareerList> careers;
    List<Career> careerList;
    int index =1 ;

    static final int FILTERS_REQUEST_CODE = 1;
    OnEntitiesReceivedListener<CareerList> moreCareers;
    List<CareerOption> careerOptions;
    List<CareerList> careerReportList;
    CareerOptionsAdapter careerAdapter;
    String choice;
    CareerPresenter presenter;
    OnEntitiesReceivedListener<CareerList> careerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        careers = new ArrayList<>();
        presenter = new CareerPresenter(this, new Repository());
      setContentView(R.layout.activity_lottie);
      careerList = new ArrayList<>();
      choice = getIntent().getExtras().get("landing")+"";
      switch (choice){
          case "yes":
              initUi(new Career(),false);
              careerOptions = (List<CareerOption>) getIntent().getSerializableExtra("LIST");
              setRelatedCareerRecyclerView(careerOptions);
              break;
          case "ex":
              initUi(new Career(),true);
              careerReportList = (List<CareerList>) getIntent().getSerializableExtra("LIST");
              if(careerReportList!=null)
                  setStaticRecyclerView(careerReportList);
              break;

          case "no":
              careerListener = new OnEntitiesReceivedListener<CareerList>(this) {
                  @Override
                  public void onReceived(List<CareerList> entities) {
                      careers.addAll(entities);
                      initUi(new Career(),false);
                      setCareerOptionsRecyclerView(entities);
                  }
              };

              presenter.getCareer(careerListener);
              break;
      }
      moreCareers = new OnEntitiesReceivedListener<CareerList>(this) {
          @Override
          public void onReceived(List<CareerList> entities) {
              addToRecyclerView(entities);
          }
      };
//      presenter = new CareerPresenter(this, new Repository());
//      careerListener = new OnEntitiesReceivedListener<Career>(this) {
//          @Override
//          public void onReceived(List<Career> entities) {
//              initUi(new Career());
//              setCareerOptionsRecyclerView(entities);
//          }
//      };
//
//        presenter.getCareer(careerListener);

      //initUi(new Career());
//        subjects = new ArrayList<>();
//        subjects.add("Become a Data Scientist");
//        subjects.add("Become a Financial Analyst");
//        subjects.add("Become a Lawyer");
//        subjects.add("Become a Web Developer");
//        subjects.add("Become an Android Developer");
//        careerListener = new OnEntitiesReceivedListener<DailyGoals>(this) {
//            @Override
//            public void onReceived(List<DailyGoals> careerTags) {
//                setCareerOptionsRecyclerView(careerTags);
//            }
//        };
//        presenter = new CareerPresenter(this,new Repository());
//        presenter.getCareerTags(careerListener);

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
                //getSupportActionBar().setTitle("Toys");
                categoryIds = (ArrayList<String>) filterData.get("categoryIds");
               // showMessage("got filter request"+categoryIds.get(0).toString());
                // recyclerView.removeAllViewsInLayout();
                 presenter.applyFilters(categoryIds,index,careerListener);
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this,"You did not apply the filters!",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void addToRecyclerView(final List<CareerList> courseLists) {
        if(careerOptionsAdapter!=null){
            careers.addAll(courseLists);
            careerOptionsAdapter.notifyDataChanged();
            if(courseLists.size()==20){
                careerOptionsAdapter.setMoreDataAvailable(true);
            }
            else{
               // showMessage("not av");
                careerOptionsAdapter.setMoreDataAvailable(false);
            }

            careerOptionsAdapter.setLoadMoreListener(new ViewMoreCareerAdapter.OnLoadMoreListener(){

                @Override
                public void onLoadMore() {
                    careerOptionsRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            if(courseLists.size()==20) {
                                //showMessage("load more called");
                                index++;
                                if(categoryIds.size()>0)
                                    presenter.applyFilters(categoryIds,index,moreCareers);
                                else
                                    presenter.loadMoreCareers(index, moreCareers);// a method which requests remote data
                            }
                        }
                    });

                }
            });

        }
    }

    public void setRelatedCareerRecyclerView(List<CareerOption> careerTags) {
        careerOptionsRecyclerView.setHasFixedSize(true);
        careerOptionsRecyclerView.removeAllViews();
        careerOptionsRecyclerView.setNestedScrollingEnabled(false);
        careerOptionsRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        careerAdapter =
                new CareerOptionsAdapter(this,careerTags,displayMetrics);
        careerOptionsRecyclerView.setAdapter(careerAdapter);
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        careerAdapter.notifyDataSetChanged();

    }
    public void setStaticRecyclerView(List<CareerList> careerTags) {
        careers.clear();
        careers.addAll(careerTags);
        presenter = new CareerPresenter(this,new Repository());
        careerOptionsRecyclerView.setHasFixedSize(true);
        careerOptionsRecyclerView.removeAllViews();
        careerOptionsRecyclerView.setNestedScrollingEnabled(false);
        careerOptionsRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        careerOptionsAdapter =
                new ViewMoreCareerAdapter(this,careers,displayMetrics);
        careerOptionsRecyclerView.setAdapter(careerOptionsAdapter);
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        careerOptionsAdapter.notifyDataSetChanged();

    }
    public void setCareerOptionsRecyclerView(final List<CareerList> careerTags) {
        careers.clear();
        careers.addAll(careerTags);
        presenter = new CareerPresenter(this,new Repository());
        careerOptionsRecyclerView.setHasFixedSize(true);
        careerOptionsRecyclerView.removeAllViews();
        careerOptionsRecyclerView.setNestedScrollingEnabled(false);
        careerOptionsRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        careerOptionsAdapter =
                new ViewMoreCareerAdapter(this,careers,displayMetrics);
        careerOptionsAdapter.setLoadMoreListener(new ViewMoreCareerAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                careerOptionsRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {


                        if(careerTags.size()==20) {
                            index++;
                            if (categoryIds.size() > 0) {

                                presenter.applyFilters(categoryIds, index, moreCareers);
                            }
                            else {

                                presenter.loadMoreCareers(index, moreCareers);
                            }// a method which requests remote data
                        }
                    }
                });
            }
        });
        careerOptionsRecyclerView.setAdapter(careerOptionsAdapter);
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        careerOptionsAdapter.notifyDataSetChanged();

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
    public void initUi(Career career) {


    }

    @Override
    public void initUi(Career career, Boolean hide) {
        setContentView(R.layout.activity_career);
        filters = findViewById(R.id.filter_search);
        if(hide){
            filters.setVisibility(View.GONE);
        }
        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareerActivity.this,CategoryBasedFilters.class);
                startActivityForResult(intent,FILTERS_REQUEST_CODE);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        careerOptionsRecyclerView =findViewById(R.id.career_recyclerview);

        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }
}

