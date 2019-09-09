package com.AbhiDev.edurecomm.Activities;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Paras Jain
 * Created on : 6/18/2018
 *
 * Randomized Feeds Activity
 * Displays feeds in a Swipeable card stack
 */

public class RandomizedFeedsActivity extends AppCompatActivity {

    @BindView(R.id.swipeView)
    SwipePlaceHolderView swipeView;
    @BindView(R.id.previousButton)
    FloatingActionButton previousButton;
    @BindView(R.id.likeButton)
    FloatingActionButton likeButton;
    @BindView(R.id.shareButton)
    FloatingActionButton shareButton;
    String wikiUrl;
    List<RandomizedFeed> feedRandom;

    RandomizedFeedsPresenter randomizedFeedsPresenter;

    /**listener for presenter callback **/
    OnEntitiesReceivedListener<RandomizedFeed> listener;
    FeedCard feedCard;

    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lottie);
        /** callback listener for feeds API. Adds feeds to swipe view on reception **/
        final Context appContext = getApplicationContext();
        listener = new OnEntitiesReceivedListener<RandomizedFeed>(this) {
            @Override
            public void onReceived(List<RandomizedFeed> entities) {
                //animationView.cancelAnimation();
                animationView = null;
                createRealView();
                feedRandom = entities;
                Collections.shuffle(entities);
                for(RandomizedFeed feed : entities){
                    feedCard = new FeedCard(appContext, feed, swipeView,RandomizedFeedsActivity.this);
                    swipeView.addView(feedCard);
                }



            }
        };
        animationView = findViewById(R.id.animation_view);
        randomizedFeedsPresenter = new RandomizedFeedsPresenter(new Repository());
        /** load feeds from API **/
        randomizedFeedsPresenter.getRandomizedFeeds(listener);






    }

    public void createRealView(){
        setContentView(R.layout.activity_randomized_feeds);

        ButterKnife.bind(this);


        initUi();

    }
    private void initUi(){
        /** dynamically resize height of swipe view **/
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Double height = displayMetrics.heightPixels * 0.7;
        swipeView.getLayoutParams().height = height.intValue();
        swipeView.getLayoutParams().width = displayMetrics.widthPixels;

        /** swipe view customizations **/
        swipeView.getBuilder()
                .setDisplayViewCount(5)    //number of cards on the stack
                .setIsUndoEnabled(true);    //enable undo feature

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeView.undoLastSwipe();
            }
        });

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeView.doSwipe(true);
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedCard.share();
            }
        });


        /** Listener fired when item removed on swipe from the view
         * Loads more feeds from API
         * ToDo : Pass page number to the API
         */
        swipeView.addItemRemoveListener(new ItemRemovedListener(){

            @Override
            public void onItemRemoved(int i) {
                if(swipeView.getAllResolvers().size() == 3)
                    randomizedFeedsPresenter.getRandomizedFeeds(listener);
            }
        });


    }
}
