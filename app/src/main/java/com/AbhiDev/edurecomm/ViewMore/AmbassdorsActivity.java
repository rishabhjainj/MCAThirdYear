package com.AbhiDev.edurecomm.ViewMore;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.wireout.Activities.BaseActivity;
import com.wireout.adapters.CareerBlogAdapter;
import com.wireout.R;
import com.wireout.adapters.ViewMoreAmbassadorAdapter;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Mentor;
import com.wireout.presenters.CampaignersPresenters;
import com.wireout.repositories.CampaignersRepository;
import com.wireout.viewactions.AmbasdorsViewAction;

import java.util.ArrayList;
import java.util.List;

public class AmbassdorsActivity extends BaseActivity implements AmbasdorsViewAction{

    RecyclerView recyclerView;
    ViewMoreAmbassadorAdapter adapter;
    ArrayList<Mentor> mentorArrayList;
    int index =1;
    CampaignersPresenters presenters;
    OnEntitiesReceivedListener<Mentor> listener;
    OnEntitiesReceivedListener<Mentor> moreMentorsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more__ambassador);
        mentorArrayList = new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_viewMore_ambass);
        presenters=new CampaignersPresenters(this,getApplicationContext(),new CampaignersRepository());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        listener = new OnEntitiesReceivedListener<Mentor>(this) {
            @Override
            public void onReceived(List<Mentor> mentors) {
                setAmbassdorRecyclerView(mentors);
            }
        };
        moreMentorsListener = new OnEntitiesReceivedListener<Mentor>(this) {
            @Override
            public void onReceived(List<Mentor> entities) {
                addMoreMentors(entities);
            }
        };
        presenters.getCampaigners(listener);

    }

    @Override
    public void addMoreMentors(List<Mentor> mentors) {
        if(adapter!=null) {
            mentorArrayList.addAll(mentors);
            //showMessage("added"+tedYouTubeArrayList.size());
            adapter.notifyDataChanged();
            if (mentors.size() == 20) {
                //showMessage("setmoredata");
                adapter.setMoreDataAvailable(true);
            } else {
                //showMessage("not av");
                adapter.setMoreDataAvailable(false);
            }

            adapter.setLoadMoreListener(new CareerBlogAdapter.OnLoadMoreListener() {

                @Override
                public void onLoadMore() {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            // showMessage("addmore loadmore");
                            index++;
                            presenters.loadMoreMentors(index, moreMentorsListener);// a method which requests remote data
                        }
                    });

                }
            });
        }
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
    public void setAmbassdorRecyclerView(List<Mentor> ambassdorsDataList) {
        mentorArrayList.clear();
        mentorArrayList.addAll(ambassdorsDataList);
        adapter=new ViewMoreAmbassadorAdapter(getApplicationContext(),mentorArrayList){
            @Override
            public void OnLiked(int id) {
                presenters.likeCampaigners(id);
            }
        };
        StaggeredGridLayoutManager linearLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
