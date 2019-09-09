package com.AbhiDev.edurecomm.adapters;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.R;
import com.wireout.models.Articles;


import java.util.List;

public class WikipediaAdapter extends RecyclerView.Adapter<WikipediaAdapter.MyRecyclerViewHolder> implements  PopupMenu.OnMenuItemClickListener{
    private Context context;
    List<String> titles;
    int positionSelected;
    String imageUrl;
    private LayoutInflater inflater;
    List<Articles> articles;
    boolean isLoading = false, isMoreDataAvailable = true;
    private WikipediaAdapter.OnLoadMoreListener loadMoreListener;
    public WikipediaAdapter(Context context, List<Articles> articles){
        this.context = context;
        this.articles = articles;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.wikipedia_item_layout,viewGroup,false);
        MyRecyclerViewHolder recyclerViewHolder=new MyRecyclerViewHolder(view);
        return recyclerViewHolder;
    }


    @Override
    public void onBindViewHolder(WikipediaAdapter.MyRecyclerViewHolder holder, final int position) {
        final Articles article = articles.get(position);
        holder.textview.setText(article.getDescription());
        holder.title.setText(article.getTitle());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(article.getUrl());
            }
        });
        Picasso.with(context)
                .load(article.getImage())
                .fit()
                .into(holder.image);
        holder.popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUrl=article.getUrl();
                showPopup(view);
                positionSelected = position;

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
        return articles.size();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, imageUrl+"");
                sendIntent.setType("text/plain");
                context.startActivity(Intent.createChooser(sendIntent,"Choose an application"));
                return true;
            case R.id.not_interested:
                removeAt(positionSelected);
            default:
                return false;
        }
    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(context, v);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(this);
        inflater.inflate(R.menu.video_menu_popup, popup.getMenu());
        popup.show();
    }
    public void removeAt(int position){
        articles.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, articles.size());
    }
    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder{


        //        TextView discount;
//        TextView discountText;
        View parent;
        ImageButton popup ;
        //        TextView streaks;
        // ImageView soldout;
        TextView title, textview;
        ImageView image;

        public MyRecyclerViewHolder(View view){
            super(view);

            popup = (ImageButton)itemView.findViewById(R.id.popupmenu);
            title=(TextView)view.findViewById(R.id.wiki_title);
            textview=(TextView)view.findViewById(R.id.wiki_text);
            image = view.findViewById(R.id.wiki_image);
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
    public void setLoadMoreListener(WikipediaAdapter.OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }

}

