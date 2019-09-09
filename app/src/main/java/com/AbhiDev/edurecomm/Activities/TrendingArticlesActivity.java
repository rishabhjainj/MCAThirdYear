package com.AbhiDev.edurecomm.Activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.wireout.adapters.TedTalksAdapter;
import com.wireout.R;
import com.wireout.adapters.CareerBlogAdapter;
import com.wireout.adapters.WikipediaAdapter;
import com.wireout.apiservices.Repository;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Articles;
import com.wireout.presenters.TrendingArticlesPresenter;
import com.wireout.presenters.TrendingArticlesViewAction;

import java.util.ArrayList;
import java.util.List;

public class TrendingArticlesActivity extends BaseActivity implements TrendingArticlesViewAction {

    RecyclerView trendingRecyclerView;
    WikipediaAdapter wikipediaAdapter;
    int index = 1;
    CareerBlogAdapter careerBlogAdapter;
    ArrayList<Articles> articlesArrayList;
    ArrayList<String> imageUrls,titles,wikiLinks,text;
    TrendingArticlesPresenter presenter;
    String choice;
    List<Articles> blogsList;
    OnEntitiesReceivedListener<Articles> moreCareerBlogListener;
    OnEntitiesReceivedListener<Articles> moreArticlesListener;
    OnEntitiesReceivedListener<Articles> listener;
    TextView toolTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articlesArrayList = new ArrayList<>();
        setContentView(R.layout.activity_trending);
        toolTitle = findViewById(R.id.tooltilte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        trendingRecyclerView = findViewById(R.id.trending_recyclerview);
        choice = getIntent().getExtras().get("landing")+"";
        switch (choice){
            case "yes":
                toolTitle.setText("Career Blogs");
                listener = new OnEntitiesReceivedListener<Articles>(this) {
                    @Override
                    public void onReceived(List<Articles> articles) {
                        setBlogsRecyclerView(articles);
                    }
                };
                presenter = new TrendingArticlesPresenter(new Repository());
                presenter.getArticles(listener);
                break;
            case "no":
                listener = new OnEntitiesReceivedListener<Articles>(this) {
                    @Override
                    public void onReceived(List<Articles> articles) {
                        setTrendingRecyclerView(articles);
                    }
                };
                presenter = new TrendingArticlesPresenter(new Repository());
                presenter.getArticles(listener);
                break;
        }

        moreArticlesListener = new OnEntitiesReceivedListener<Articles>(this) {
            @Override
            public void onReceived(List<Articles> entities) {
                addMoreTrendingArticles(entities);
            }
        };
        moreCareerBlogListener = new OnEntitiesReceivedListener<Articles>(this) {
            @Override
            public void onReceived(List<Articles> entities) {
                addMoreCareerBlogs(entities);
            }
        };
    }

    @Override
    public void addMoreCareerBlogs(List<Articles> articles) {
        if(careerBlogAdapter!=null) {
            articlesArrayList.addAll(articles);
            //showMessage("added"+tedYouTubeArrayList.size());
            careerBlogAdapter.notifyDataChanged();
            if (articles.size() == 20) {
                //showMessage("setmoredata");
                careerBlogAdapter.setMoreDataAvailable(true);
            } else {
                //showMessage("not av");
                careerBlogAdapter.setMoreDataAvailable(false);
            }

            careerBlogAdapter.setLoadMoreListener(new CareerBlogAdapter.OnLoadMoreListener() {

                @Override
                public void onLoadMore() {
                    trendingRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            // showMessage("addmore loadmore");
                            index++;
                            presenter.loadMoreArticles(index, moreArticlesListener);// a method which requests remote data
                        }
                    });

                }
            });
        }
    }

    @Override
    public void addMoreTrendingArticles(List<Articles> articles) {
        if(wikipediaAdapter!=null){
            articlesArrayList.addAll(articles);
            //showMessage("added"+tedYouTubeArrayList.size());
            wikipediaAdapter.notifyDataChanged();
            if(articles.size()==20){
                //showMessage("setmoredata");
                wikipediaAdapter.setMoreDataAvailable(true);
            }
            else{
                //showMessage("not av");
                wikipediaAdapter.setMoreDataAvailable(false);
            }

            wikipediaAdapter.setLoadMoreListener(new WikipediaAdapter.OnLoadMoreListener(){

                @Override
                public void onLoadMore() {
                    trendingRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            // showMessage("addmore loadmore");
                            if(articlesArrayList.size()==20) {
                                index++;
                                presenter.loadMoreArticles(index, moreArticlesListener);// a method which requests remote data
                            }
                        }
                    });

                }
            });

        }
    }

    public void setBlogsRecyclerView(List<Articles> articles) {
        articlesArrayList.clear();
        articlesArrayList.addAll(articles);
        trendingRecyclerView.setHasFixedSize(true);
        trendingRecyclerView.removeAllViews();
        trendingRecyclerView.setNestedScrollingEnabled(false);
        trendingRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        trendingRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        careerBlogAdapter =
                new CareerBlogAdapter(this,articlesArrayList);
        careerBlogAdapter.setLoadMoreListener(new CareerBlogAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                trendingRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        if(articlesArrayList.size()==20) {
                            index++;
                            //showMessage("called here"+index);
                            presenter.loadMoreArticles(index, moreArticlesListener);
                        }// a method which requests remote data
                    }
                });
            }
        });
        trendingRecyclerView.setAdapter(careerBlogAdapter);
        trendingRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        careerBlogAdapter.notifyDataSetChanged();

    }
    public void setTrendingRecyclerView(List<Articles> articles) {
        trendingRecyclerView.setHasFixedSize(true);
        articlesArrayList.clear();
        articlesArrayList.addAll(articles);
        trendingRecyclerView.removeAllViews();
        trendingRecyclerView.setNestedScrollingEnabled(false);
        trendingRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        trendingRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        wikipediaAdapter =
                new WikipediaAdapter(this,articlesArrayList);
        wikipediaAdapter.setLoadMoreListener(new WikipediaAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                trendingRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        if(articlesArrayList.size()==20) {
                            index++;
                            //showMessage("called here"+index);
                            presenter.loadMoreArticles(index, moreArticlesListener);
                        }// a method which requests remote data
                    }
                });
            }
        });
        trendingRecyclerView.setAdapter(wikipediaAdapter);
        trendingRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        wikipediaAdapter.notifyDataSetChanged();

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
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }
}
