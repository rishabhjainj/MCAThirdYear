package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.R;
import com.wireout.models.University;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rishabh on 2/18/2018.
 */

public class RecommendedUniAdapter extends RecyclerView.Adapter<RecommendedUniAdapter.RecyclerViewHolder> {

    private Context context;
    Random r;
    private LayoutInflater inflater;
    List<University> universityDatas = new ArrayList<>();
    DisplayMetrics displayMetrics;

    boolean isLoading = false, isMoreDataAvailable = true;
    private ViewMoreCareerAdapter.OnLoadMoreListener loadMoreListener;
    public RecommendedUniAdapter( Context context, List<University> universityDatas, DisplayMetrics displayMetrics){
        this.context = context;
        this.universityDatas = universityDatas;
        this.displayMetrics=displayMetrics;
        r = new Random();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){

    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.institution_item_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final University universityData = universityDatas.get(position);

        University institution = universityDatas.get(position);
        if(institution.getImages()!=null)
            Picasso.with(context)
                    .load(institution.getFeatureImage())
                    .into(holder.imageView);
        //Log.d("categoryImage",instituteImages.get(position)+"");
        holder.instituteNameTextView.setText(institution.getName());
        if (position >= getItemCount() - 15 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            isLoading = true;
            loadMoreListener.onLoadMore();
            Log.d("careeroptionadapter", "loadMore called");
        }

    }

    @Override
    public int getItemCount() {
        return universityDatas.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{


           View parent;
//        TextView collegeName;
//        TextView location;
//        ImageView icon;
//        ImageView collegeImage;
//        MaterialFavoriteButton favorite;
//        ImageView call;
//        ImageView share;
//        LinearLayout enroll, linearLayout;
        ImageView imageView;
        TextView instituteNameTextView;

        public RecyclerViewHolder(View view){
            super(view);


            imageView=itemView.findViewById(R.id.institution_image);
            instituteNameTextView=itemView.findViewById(R.id.institution_name);
            parent=this.itemView;

        }
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }
    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }
    public void setLoadMoreListener(ViewMoreCareerAdapter.OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }

}
