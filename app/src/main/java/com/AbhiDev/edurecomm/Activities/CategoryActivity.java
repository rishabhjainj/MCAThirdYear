package com.AbhiDev.edurecomm.Activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.AbhiDev.edurecomm.R;
import com.AbhiDev.edurecomm.adapters.SchoolsAdapter;
import com.AbhiDev.edurecomm.apiservices.Repository;
import com.AbhiDev.edurecomm.listeners.OnEntitiesReceivedListener;
import com.AbhiDev.edurecomm.models.CategoryList;
import com.AbhiDev.edurecomm.presenters.CategoryPresenter;
import com.AbhiDev.edurecomm.viewactions.CategoryViewAction;
import com.wireout.R;
import com.wireout.adapters.SchoolsAdapter;
import com.wireout.apiservices.Repository;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Category;
import com.wireout.models.CategoryList;
import com.wireout.presenters.CategoryPresenter;
import com.wireout.viewactions.CategoryViewAction;

import java.util.List;

public class CategoryActivity extends BaseActivity implements CategoryViewAction {

    RecyclerView categoryRecyclerView;
    SchoolsAdapter schoolsAdapter;
    CategoryPresenter presenter;
    OnEntitiesReceivedListener<CategoryList> listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        presenter = new CategoryPresenter(this,new Repository());
        listener = new OnEntitiesReceivedListener<CategoryList>(this) {
            @Override
            public void onReceived(List<CategoryList> entities) {
                initActivity();
                setCategories(entities);
                Log.d("categoryList","setCategories");
            }
        };
        presenter.getCategoriesList(listener);




    }
    public void initActivity(){
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        categoryRecyclerView = findViewById(R.id.categories_recyclerView);
    }


    @Override
    public void initUi(Category category) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNetworkError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void setCategories(List<CategoryList> categoryList) {
        schoolsAdapter=new SchoolsAdapter(CategoryActivity.this,categoryList);
        GridLayoutManager layoutManager=new GridLayoutManager(CategoryActivity.this,2);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setAdapter(schoolsAdapter);

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

