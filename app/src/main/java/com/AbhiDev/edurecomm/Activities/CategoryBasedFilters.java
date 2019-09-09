package com.AbhiDev.edurecomm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.AbhiDev.edurecomm.R;
import com.AbhiDev.edurecomm.adapters.CategoryFiltersAdapter;
import com.AbhiDev.edurecomm.apiservices.Repository;
import com.AbhiDev.edurecomm.listeners.OnEntitiesReceivedListener;
import com.AbhiDev.edurecomm.models.Category;
import com.AbhiDev.edurecomm.models.CategoryList;
import com.AbhiDev.edurecomm.presenters.CategoryPresenter;
import com.AbhiDev.edurecomm.viewactions.CategoryViewAction;

import java.util.ArrayList;
import java.util.List;

public class CategoryBasedFilters extends BaseActivity implements CategoryViewAction {
    OnEntitiesReceivedListener<CategoryList> listener;
    CategoryPresenter presenter;
    RecyclerView categoryRecyclerView;
    public ArrayList<String> categoryIds;
    ImageView resetButton;
    CategoryFiltersAdapter adapter;

    public class PrefKeys{
        public static final String categoryIds = "filters_categoryIds";
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryIds= new ArrayList<>();
        setContentView(R.layout.category_filters_layout);
        presenter = new CategoryPresenter(this,new Repository());
        listener = new OnEntitiesReceivedListener<CategoryList>(this) {
            @Override
            public void onReceived(List<CategoryList> entities) {
                setCategoryFilters(entities);
            }
        };
        presenter.getCategoriesList(listener);
        retrieveFromPreferences();
       initUi(new Category());


    }
    private void filter()
    {
        sendResult();
    }
    void retrieveFromPreferences(){
        String categoryIds = prefManager.getString(PrefKeys.categoryIds);
        Log.d("FiltersActivity", "Retrieved "  + categoryIds );
        this.categoryIds.clear();
        if(categoryIds != null && !categoryIds.equals(""))
            for(String id : categoryIds.split(","))
                this.categoryIds.add(id);

    }

    protected void sendResult()
    {

        //this is where the filters are applied and intent result passed back to the calling activity
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putStringArrayList("categoryIds", categoryIds);
        intent.putExtra("filterData", bundle);
        setResult(RESULT_OK, intent);
        showMessage("Filtering your results...");
        saveToPreferences();
        finish();
    }
    public void resetFilters(){
        //clear filter preferences
        prefManager.saveString(PrefKeys.categoryIds, null);
        finish();
        startActivity(getIntent());
    }
    void saveToPreferences(){
        prefManager.saveString(PrefKeys.categoryIds, null);
        Log.d("Filtersactivity","saving:"+TextUtils.join(",",categoryIds));
        prefManager.saveString(PrefKeys.categoryIds, TextUtils.join(",", categoryIds));

    }
    public void setCategoryFilters(List<CategoryList> categories){
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setNestedScrollingEnabled(false);
        categoryRecyclerView.removeAllViews();
        categoryRecyclerView.removeAllViewsInLayout();
        adapter=new CategoryFiltersAdapter(CategoryBasedFilters.this,categories);
        GridLayoutManager layoutManager=new GridLayoutManager(CategoryBasedFilters.this,1);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initUi(Category category) {
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFilters();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);
        Button fab=(Button)findViewById(R.id.fab) ;
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        categoryRecyclerView = findViewById(R.id.category_recycler);

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
}
