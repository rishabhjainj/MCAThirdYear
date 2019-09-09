package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.R;
import com.wireout.models.Articles;

import java.util.List;

public class CareerBlogAdapter extends RecyclerView.Adapter<CareerBlogAdapter.MyRecyclerViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Articles> articles;
    boolean isLoading = false, isMoreDataAvailable = true;
    private CareerBlogAdapter.OnLoadMoreListener loadMoreListener;
    public CareerBlogAdapter(Context context, List<Articles> articles){
        this.context = context;
        this.articles = articles;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public CareerBlogAdapter.MyRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.career_blog_item_view,viewGroup,false);
        CareerBlogAdapter.MyRecyclerViewHolder recyclerViewHolder=new CareerBlogAdapter.MyRecyclerViewHolder(view);
        return recyclerViewHolder;
    }
    @Override
    public void onBindViewHolder(CareerBlogAdapter.MyRecyclerViewHolder holder, final int position) {
        final Articles article = articles.get(position);
        holder.textview.setText(article.getDescription());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(article.getUrl());
            }
        });
        Picasso.with(context)
                .load(article.getImage())
                .into(holder.image);
        Log.d("wiki_image",article.getImage());
        if (position >= getItemCount() - 15 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            isLoading = true;
            loadMoreListener.onLoadMore();
            Log.d("careeroptionadapter", "loadMore called");
        }
    }
    @Override
    public int getItemCount() {
        return articles.size();
    }
    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder{

        View parent;
        TextView textview;
        ImageView image;

        public MyRecyclerViewHolder(View view){
            super(view);
            textview=(TextView)view.findViewById(R.id.career_blog_text);
            image = view.findViewById(R.id.career_blog_image);
            this.parent = view;

        }
    }
    public void openBrowser(String url)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public interface OnLoadMoreListener{
        void onLoadMore();
    }
    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }
    public void setLoadMoreListener(CareerBlogAdapter.OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }
}
