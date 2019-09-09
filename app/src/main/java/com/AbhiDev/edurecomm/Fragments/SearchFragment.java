package com.AbhiDev.edurecomm.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, SearchViewAction {

    Context context;
    public int searchChoice = 1;
    SearchPresenter presenter;
    Map<String, String> queryMap;
    ProgressBar progressBar;
    ViewMoreCareerAdapter ambassdorsRecyclerViewAdapter;
    TopCoursesItemAdapter courseAdapter;
    SchoolsAdapter shortTermAdapter;
    RecyclerView recyclerView;
    public SearchView searchView;
    String mQueryString;
    InstitutesAdapter adapter;

    android.os.Handler handler;

    RecyclerView recyclerView1;

    View view;
    DisplayMetrics displayMetrics;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search,container,false);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        addStaticData();
        queryMap = new HashMap<>();
        presenter = new SearchPresenter(this,getActivity(),new Repository());
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        handler = new android.os.Handler();

        searchView = (SearchView) view.findViewById(R.id.searchView);
        assert searchView != null;
        searchView.setOnQueryTextListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSearch);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        //recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerViewSearch);

        displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        return view;
    }
    public static SearchFragment newInstance(int index) {
        SearchFragment fragment = new SearchFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    public void addStaticData(){

        RecyclerView recyclerView=view.findViewById(R.id.recyclerViewSearch);
        ArrayList<String> products= new ArrayList<>();

        products.add(" ");
        products.add(" ");

        products.add("Popular Searches");

        products.add("Sharda University");
        products.add("Cambridge University");
        products.add("Delhi University");
        products.add("Banasthali Vidyapeeth");
        products.add("Oxford University");
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(getActivity()); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        SearchFragmmentAdapter adapter=new SearchFragmmentAdapter(getActivity(),products,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged() ;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mQueryString=query;
        handler.removeCallbacksAndMessages(null);
        progressBar.setVisibility(View.GONE);
        if(TextUtils.isEmpty(query)){
            addStaticData();
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

        return true;    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        mQueryString = newText;
        handler.removeCallbacksAndMessages(null);
        progressBar.setVisibility(View.VISIBLE);
//
//       // presenter.applySearch(mQueryString);
        if(TextUtils.isEmpty(newText)){
            addStaticData();
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

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void setUniversityRecyclerView(List<Institution> universityDataList) {
        recyclerView.hasFixedSize();
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();

        adapter=new InstitutesAdapter(getContext(),universityDataList);
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
        courseAdapter=new TopCoursesItemAdapter(getContext(),courses);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
    }

    @Override
    public void setShortTermCoursesRecyclerView(List<CategoryList> shortTermCourses) {
        shortTermAdapter=new SchoolsAdapter(getContext(),shortTermCourses);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(shortTermAdapter);
    }

    @Override
    public void setCampaignersRecyclerView(List<CareerList> campaigners) {
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        ambassdorsRecyclerViewAdapter =
                new ViewMoreCareerAdapter(getContext(),campaigners,displayMetrics);
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
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void addGenStaticData() {

    }
}

