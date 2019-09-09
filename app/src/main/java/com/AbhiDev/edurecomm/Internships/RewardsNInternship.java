package com.AbhiDev.edurecomm.Internships;

import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.wireout.Activities.BaseActivity;
import com.wireout.R;
import com.wireout.apiservices.Repository;
import com.wireout.common.MyApplication;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Internships;
import com.wireout.viewactions.BaseViewAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RewardsNInternship extends BaseActivity implements InternshipViewAction {

    List<String> skills;
    ImageView backbtn;
    RecyclerView skillRecyclerView;
    MaterialFavoriteButton favorite;
    ImageView image;
    TextView role,organization,company,location,paid,startDate,applyBy,aboutInternship,responsibilities,perks,numPos,score,abtCompany;
    InternshipPresenter presenter;
    InternshipSkillAdapter adapter;
    SimilarInternshipAdapter similarInternshipAdapter;
    RecyclerView similarRecyclerView;
    OnEntitiesReceivedListener<Internships> listener;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        id = getIntent().getExtras().getString("id");
        //Log.d("id",id);
//        listener = new OnEntitiesReceivedListener<Internships>(this) {
//            @Override
//            public void onReceived(List<Internships> entities) {
//
//            }
//        };
        presenter = new InternshipPresenter(this,new Repository());
        presenter.getInternshipById(id);

    }

    @Override
    public void clearRecyclerView() {

    }

    @Override
    public void initUi(final Internships internship) {
        setContentView(R.layout.iternship_layout);
        image = findViewById(R.id.image);
        organization=findViewById(R.id.organization);
        company=findViewById(R.id.company);
        location = findViewById(R.id.location);
        paid = findViewById(R.id.paid);
        startDate = findViewById(R.id.startDate);
        applyBy = findViewById(R.id.applyDate);
        aboutInternship = findViewById(R.id.abtInternship);
        responsibilities = findViewById(R.id.responsibilities);
        perks = findViewById(R.id.perks);
        role=findViewById(R.id.role);
        numPos=findViewById(R.id.numPos);
        score=findViewById(R.id.score);
        abtCompany= findViewById(R.id.abtCompany);
        Picasso.with(this).load(internship.getImage()).into(image);
        skills = Arrays.asList(internship.getSkills().split("\\s*,\\s*"));
        List<String> sk = new ArrayList<>();
        for(String s: skills){
            sk.add(s.toUpperCase());
        }

        List<String> respons = Arrays.asList(internship.getResponsibilities().split("\\s*,\\s*"));
        List<String> perk = Arrays.asList(internship.getPerks().split("\\s*,\\s*"));
        String r="";
        int i=1;
        for(String s: respons){
            r+= (i++)+". "+s+"\n";
        }
        i=1;
        String p="";
        for(String s: perk){
            p+= (i++)+". "+s+"\n";
        }
        responsibilities.setText(r);
        perks.setText(p);
        organization.setText(internship.getOrganization());
        role.setText(internship.getRole());
        if(internship.getPaid()){
            paid.setText(internship.getType()+" | paid");
        }
        else{
            paid.setText(internship.getType()+" | unpaid");
        }
        startDate.setText(internship.getStartDate());
        applyBy.setText(internship.getApplyBy());
        abtCompany.setText(internship.getAboutOrganization());
        aboutInternship.setText(internship.getAbout());
        numPos.setText(internship.getNumPositions());
        score.setText(internship.getScore());
        backbtn= findViewById(R.id.backbtn);
        skillRecyclerView = findViewById(R.id.skillrecycler);
        similarRecyclerView = findViewById(R.id.similar_intern_recyclerview);
        setSimilarRecyclerView(sk);
        setSkillRecyclerView(sk);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        favorite = findViewById(R.id.likeButton);
        if(internship.getUserLiked()){
            favorite.setFavorite(true);
            favorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        else{
            favorite.setFavorite(false);
            favorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.mettalic_grey), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn()) {
                    if (favorite.isFavorite()) {
                        favorite.setFavorite(false);
                        favorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.mettalic_grey), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }
                    else {
                        favorite.setFavorite(true);
                        favorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }

                    presenter.likeInternship(internship.getId());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void setSkillRecyclerView(List<String> skills){
        skillRecyclerView.setHasFixedSize(true);
        skillRecyclerView.removeAllViews();
        skillRecyclerView.setNestedScrollingEnabled(false);
        skillRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        skillRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new InternshipSkillAdapter(this,skills);
        skillRecyclerView.setAdapter(adapter);
        skillRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setInternshipRecyclerView(List<Internships> internships) {

    }

    public void setSimilarRecyclerView(List<String> skills){
        similarRecyclerView.setHasFixedSize(true);
        similarRecyclerView.removeAllViews();
        similarRecyclerView.setNestedScrollingEnabled(false);
        similarRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        similarRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        similarInternshipAdapter =
                new SimilarInternshipAdapter(this,skills);
        similarRecyclerView.setAdapter(similarInternshipAdapter);
        similarRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        similarInternshipAdapter.notifyDataSetChanged();
    }

}
