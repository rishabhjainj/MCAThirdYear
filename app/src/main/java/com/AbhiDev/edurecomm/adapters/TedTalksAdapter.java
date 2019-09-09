package com.AbhiDev.edurecomm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.wireout.R;
import com.wireout.models.TedYouTube;
import com.wireout.videos.Config;

import java.util.List;

public class TedTalksAdapter extends RecyclerView.Adapter<TedTalksAdapter.MyViewHolder> {

    Context context;
    List<TedYouTube> tedVideos;
    boolean isLoading = false, isMoreDataAvailable = true;
    private TedTalksAdapter.OnLoadMoreListener loadMoreListener;

    public TedTalksAdapter(Context context, List<TedYouTube> tedVideos) {
        this.context = context;
        this.tedVideos = tedVideos;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ted_item_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final TedYouTube video = tedVideos.get(position);
        holder.successStory.setText(video.getTitle());
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
               // Toast.makeText(context,"error loading thumbnail"+errorReason,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                //holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };
        holder.youTubeThumbnailView.initialize(Config.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                //             if(urls.get(position)!=null) {
                youTubeThumbnailLoader.setVideo(video.getYoutubeId());

                Log.d("urlfound",video.getYoutubeId());
                //           }
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //Toast.makeText(ctx,"failed",Toast.LENGTH_SHORT).show();
            }
        });
        if (position >= getItemCount() - 15 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            isLoading = true;
            loadMoreListener.onLoadMore();
            Log.d("careeroptionadapter", "loadMore called");
        }
    }

    @Override
    public int getItemCount() {
        return tedVideos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        YouTubeThumbnailView youTubeThumbnailView;
        TextView successStory;
        View parent;

        public MyViewHolder(View itemView) {
            super(itemView);

            youTubeThumbnailView=itemView.findViewById(R.id.youtube_thumbnail);
            youTubeThumbnailView.setOnClickListener(this);
            successStory=itemView.findViewById(R.id.tvSuccess);
            parent=this.itemView;
        }
        @Override
        public void onClick(View view) {
            Intent intent= YouTubeStandalonePlayer.createVideoIntent((Activity) context,Config.DEVELOPER_KEY,tedVideos.get(getLayoutPosition()).getYoutubeId());
            context.startActivity(intent);
        }

    }
    public interface OnLoadMoreListener{
        void onLoadMore();
    }
    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }
    public void setLoadMoreListener(TedTalksAdapter.OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }


}
