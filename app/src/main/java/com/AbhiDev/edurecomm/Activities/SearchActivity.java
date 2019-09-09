package com.AbhiDev.edurecomm.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener, SearchViewAction {

    Context context;
    public int searchChoice = 2;
    SearchPresenter presenter;
    String message;
    Map<String, String> queryMap;
    ProgressBar progressBar;
    ViewMoreCareerAdapter ambassdorsRecyclerViewAdapter;
    TopCoursesItemAdapter courseAdapter;
    SchoolsAdapter shortTermAdapter;
    RecyclerView recyclerView;
    public SearchView searchView;
    String mQueryString;
    InstitutesAdapter adapter;

    Intent intent;
    android.os.Handler handler;
    DisplayMetrics displayMetrics;
    TextView searchFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        searchFilter = findViewById(R.id.filter_search);
        context = getApplicationContext();
        searchView = (SearchView) findViewById(R.id.searchView);
        intent = getIntent();
        queryMap = new HashMap<>();
        presenter = new SearchPresenter(this,this,new Repository());
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        handler = new android.os.Handler();

        searchView = (SearchView) findViewById(R.id.searchView);
        assert searchView != null;
        searchView.setOnQueryTextListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        if(intent.getExtras().get("searchQuery")!=null){
            if(intent.getExtras().get("searchQuery").equals(""))
                addGenStaticData();
            else{
                searchView.setQuery(intent.getExtras().get("searchQuery").toString(),true);
            }
        }


        searchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),FiltersActivity.class);
                intent.putExtra("searchquery",searchView.getQuery().toString());
                intent.putExtra("filterState",prefManager.getInt("filterState"));
                startActivityForResult(intent,1);
            }
        });
    }
//    public void addStaticData(){
//
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
//        ArrayList<String> products= new ArrayList<>();
//
//        products.add(" ");
//        products.add(" ");
//
//        products.add("Popular Searches");
//
//        products.add("Sharda University");
//        products.add("Cambridge University");
//        products.add("Delhi University");
//        products.add("Banasthali Vidyapeeth");
//        products.add("Oxford University");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.removeAllViews();
//        recyclerView.removeAllViewsInLayout();
//        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(this); // (int spanCount, int orientation)
//        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
//        SearchIniActivityAdapter adapter = new SearchIniActivityAdapter(getApplicationContext(),products,recyclerView,this);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged() ;
//
//    }
    public void addStaticSTCourseData(){

//category static data
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
        ArrayList<String> products= new ArrayList<>();

        products.add(" ");
        products.add(" ");

        products.add("Popular Searches");

        products.add("Lifestyle");
        products.add("Data Science");
        products.add("Finance");
        products.add("Engineering & Technology");
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(this); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        SearchIniActivityAdapter adapter = new SearchIniActivityAdapter(getApplicationContext(),products,recyclerView,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged() ;

    }
    public void addStaticAmbassadorsData(){

//career static data
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
        ArrayList<String> products= new ArrayList<>();

        products.add(" ");
        products.add(" ");

        products.add("Popular Searches");

        products.add("Data Scientist");
        products.add("Financial Analyst");
        products.add("Actuary");
        products.add("Startup Founder");
        products.add("Architect");
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(this); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        SearchIniActivityAdapter adapter = new SearchIniActivityAdapter(getApplicationContext(),products,recyclerView,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged() ;

    }
    public void addStaticCourseData(){


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
        ArrayList<String> products= new ArrayList<>();

        products.add(" ");
        products.add(" ");

        products.add("Popular Searches");

        products.add("A Complete Facebook Ads Masterclass");
        products.add("Online Japanese N4 Courses");
        products.add("MBA in Banking and Finance");
        products.add("Career Success Specialization");
        products.add("Android Development");
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(this); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        SearchIniActivityAdapter adapter = new SearchIniActivityAdapter(getApplicationContext(),products,recyclerView,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged() ;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mQueryString=query;
        handler.removeCallbacksAndMessages(null);
        progressBar.setVisibility(View.GONE);
        if(TextUtils.isEmpty(query)){
            addGenStaticData();
        }
        else{
            queryMap.put("search",query);
            switch (searchChoice){
                case 1:presenter.searchUniversity(queryMap);
                    break;
                case 2:presenter.searchCourses(queryMap);
                    break;
                case 3:presenter.searchShortTermCourses(queryMap);
                    break;
                case 4:presenter.searchAmbassdors(queryMap);
                    break;
                default:showMessage("Error");
            }
        }

        return true;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        mQueryString = newText;
        handler.removeCallbacksAndMessages(null);
        progressBar.setVisibility(View.VISIBLE);
//
//       // presenter.applySearch(mQueryString);
        if(TextUtils.isEmpty(newText)){
            addGenStaticData();
            progressBar.setVisibility(View.GONE);
            clearRecyclerView();
        }
        else {

            if(!searchView.toString().equals(""))

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(TextUtils.getTrimmedLength(mQueryString)>0){
                            queryMap.put("search",newText);
                            Log.d("searchintent",newText);
                            switch (searchChoice){
                                case 1:presenter.searchUniversity(queryMap);
                                    break;
                                case 2:presenter.searchCourses(queryMap);
                                    break;
                                case 3:presenter.searchShortTermCourses(queryMap);
                                    break;
                                case 4:presenter.searchAmbassdors(queryMap);
                                    break;
                                default:showMessage("Error");
                            }
                        }

                        else{

                        }

                    }
                }, 600);
        }
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }

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
        GridLayoutManager layoutManager=new GridLayoutManager(SearchActivity.this,2);
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

    public void clearRecyclerView() {


        recyclerView.removeAllViewsInLayout();
        recyclerView.removeAllViews();
        if(recyclerView.getAdapter()!=null)
            recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void addGenStaticData() {
        switch (searchChoice){
            case 1:
               // addStaticData();
                break;
            case 2:
                addStaticCourseData();
                break;
            case 3:
                addStaticSTCourseData();
                break;
            case 4:
                addStaticAmbassadorsData();
                break;
            default:
                addStaticCourseData();
        }
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // handle arrow click here
//        if (item.getItemId() == android.R.id.home) {
//            finish(); // close this activity and return to preview activity (if there is any)
//        }
//        else if(item.getItemId()==R.id.univ){
//            showMessage("Universities Selected");
//            searchChoice = 1;
//            addStaticData();
//        }
//        else if(item.getItemId() == R.id.course){
//            showMessage("Course Selected");
//            searchChoice = 2;
//            addStaticData();
//        }
//        else if(item.getItemId()==R.id.short_term){
//            showMessage("Short Term Courses Selected");
//            searchChoice  = 3;
//            addStaticData();
//        }
//        else if(item.getItemId() == R.id.ambassdors){
//            showMessage("Ambassdors Selected");
//            searchChoice = 4;
//            addStaticData();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
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
    public void hideLoader() {
        progressBar.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode==1)
        {
            if (resultCode== RESULT_OK)
            {

                searchChoice=data.getIntExtra("filter",0);
                if(data.getExtras().get("searchQuery")!=null){
                    if(data.getExtras().get("searchQuery").equals(""))
                        addGenStaticData();
                    else{
                        searchView.setQuery(data.getExtras().get("searchQuery").toString(),true);
                    }
                }

                switch (searchChoice){
                    case 1:showMessage("Universities Selected");
                          // addStaticData();
                        break;
                    case 2:showMessage("Courses Selected");
                    searchView.setQueryHint("Search or Browse Courses");
                    addStaticCourseData();
                        break;
                    case 3:showMessage("Categories Selected");
                        searchView.setQueryHint("Search or Browse Categories");
                    addStaticSTCourseData();
                        break;
                    case 4:showMessage("Careers Selected");
                        searchView.setQueryHint("Search or Browse Careers");
                    addStaticAmbassadorsData();
                        break;
                    default:showMessage("Error");
                    addStaticCourseData();
                }
                Log.d("searchoice",searchChoice+"");
//                if (searchChoice==1)
//                {
//                    presenter.getUniversities();
//                }
//                else if (filter==2)
//                {
//                    presenter.getWishCourses();
//                }
//                else if (filter==3)
//                {
//                    presenter.getWishShortTermCourses();
//                }
//                else if (filter==4)
//                {
//                    presenter.getWishCampaigners();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"filter = "+filter,Toast.LENGTH_LONG).show();
//                }
            }
        }
    }
}
