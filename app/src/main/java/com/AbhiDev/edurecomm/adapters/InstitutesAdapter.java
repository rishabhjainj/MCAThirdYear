package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wireout.R;
import com.wireout.models.Institution;

import java.util.List;

public class InstitutesAdapter extends RecyclerView.Adapter<InstitutesAdapter.MyViewHolder> {
    Context context;
    List<Institution> institutions;

    public InstitutesAdapter(Context context, List<Institution> institutions) {
        this.context = context;
        this.institutions = institutions;

    }
    @NonNull
    @Override
    public InstitutesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.institution_item_layout,parent,false);

        return new InstitutesAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull InstitutesAdapter.MyViewHolder holder, final int position) {

        Institution institution = institutions.get(position);
        if(institution.getImages()!=null)
        Picasso.with(context)
                .load(institution.getFeatureImage())
                .into(holder.imageView);
        //Log.d("categoryImage",instituteImages.get(position)+"");
        holder.instituteNameTextView.setText(institution.getName());

    }

    @Override
    public int getItemCount() {
        return institutions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView instituteNameTextView;

        View parent;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.institution_image);
            instituteNameTextView=itemView.findViewById(R.id.institution_name);
            parent=this.itemView;
        }
    }

}
