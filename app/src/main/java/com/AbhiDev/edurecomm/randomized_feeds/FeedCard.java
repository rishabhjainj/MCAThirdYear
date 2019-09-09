package com.AbhiDev.edurecomm.randomized_feeds;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.squareup.picasso.Picasso;
import com.wireout.Activities.RandomizedFeedsActivity;
import com.wireout.models.RandomizedFeed;
import com.wireout.R;

/**
 * Author : Paras Jain
 * Created on : 6/18/2018
 *
 * FeedCard UI binding class for SwipePlaceHolderView
 * Binds various elements of randomized_feed_card layout and
 * sets callbacks for swipe actions using annotations provided
 * by the library.
 */

@Layout(R.layout.randomized_feed_card)
public class FeedCard {


    @View(R.id.imageview)
    public ImageView imageView;

    @View(R.id.url)
    public TextView url;

    @View(R.id.heading)
    public TextView heading;

    @View(R.id.text)
    public TextView text;

    public RandomizedFeed feed;
    String wikiUrl;
    public Context context;
    public SwipePlaceHolderView swipeView;
    RandomizedFeedsActivity obj;
    public FeedCard(Context context, RandomizedFeed feed, SwipePlaceHolderView swipeView, RandomizedFeedsActivity obj){
        this.context = context;
        this.feed = feed;
        this.swipeView = swipeView;
        this.obj = obj;
    }

    /** Called when the card is inflated onto the view**/
    @Resolve
    public void onResolved(){
        Log.d("feedImage",feed.getImage());
        Picasso.with(context)
                .load(feed.getImage())
                .error(R.drawable.emotional_intelligence)
                .into(imageView);
        heading.setText(feed.getHeadline());
        url.setText(feed.getUrl());
        text.setText(feed.getText());

    }
    
    @SwipeOut
    public void onSwipedOut(){
        Log.d("FeedCardSwipe", "onSwipedOut");
    }

    @SwipeCancelState
    public void onSwipeCancelState(){
        Log.d("FeedCardSwipe", "onSwipeCancelState");
    }

    @SwipeIn
    public void onSwipeIn(){
        Log.d("FeedCardSwipe", "onSwipedIn");
    }

    @SwipeInState
    public void onSwipeInState(){
        Log.d("FeedCardSwipe", "onSwipeInState");
    }

    @SwipeOutState
    public void onSwipeOutState(){
        Log.d("FeedCardSwipe", "onSwipeOutState");
    }

   @Click(R.id.imageview)
    public void click(){
       Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(feed.getUrl()));
       obj.startActivity(browserIntent);
   }
   public void share(){
       Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
       sharingIntent.setType("text/plain");
       sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
       sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, feed.getUrl());

       try {
           if (sharingIntent.resolveActivity(context.getPackageManager()) != null)
               obj.startActivity(Intent.createChooser(sharingIntent, "Share Using"));
           else {
               Toast.makeText(context, "No app found on your phone which can perform this action", Toast.LENGTH_SHORT).show();
           }
       }
       catch (Exception e){

       }

   }


}
