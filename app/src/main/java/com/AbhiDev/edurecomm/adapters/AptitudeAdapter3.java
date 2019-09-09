package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.wireout.Activities.SectionInfoActivity;
import com.wireout.common.MyApplication;
import com.wireout.R;
import com.wireout.models.Library;
import com.wireout.viewactions.TrendingCoursesViewAction;

import java.util.List;

/**
 * Created by rahul on 19/3/18.
 */

public class AptitudeAdapter3 extends RecyclerView.Adapter<AptitudeAdapter3.MyViewHolder> {

    Context context;
    List<Library> libraries;
    TrendingCoursesViewAction viewAction;

    public AptitudeAdapter3(Context context, TrendingCoursesViewAction viewAction, List<Library> libraries) {
        this.context = context;
        this.libraries = libraries;
        this.viewAction = viewAction;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.aptitude_adapter3_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {

       // Drawable drawable= ContextCompat.getDrawable(context,R.drawable.)

        Library library=libraries.get(position);


        myViewHolder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showOverView(position);
            }
        });
        myViewHolder.knowHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOverView(position);
            }
        });
        myViewHolder.topic.setText(library.getTopic());
//        myViewHolder.exp.collapse();
//        myViewHolder.cardView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (myViewHolder.exp.isExpanded())
//                {
//                    myViewHolder.imageView.setImageResource(R.drawable.ic_action_down2);
//                }
//                else
//                {
//                    myViewHolder.imageView.setImageResource(R.drawable.ic_action_up2);
//                }
//
//                myViewHolder.exp.toggle();
//                myViewHolder.exp.expand();
//                myViewHolder.exp.moveChild(0);
//                myViewHolder.exp.move(100);
//                myViewHolder.exp.setClosePosition(0);
//            }
//        });
//        myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewAction.startVideos();
//            }
//        });

    }

    public void showOverView(int position){
            Intent i = new Intent(context, SectionInfoActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            switch (position) {
                case 0:
                    i.putExtra("id", "0");
                    context.startActivity(i);
                    break;
                case 1:
                    i.putExtra("id", "1");
                    context.startActivity(i);
                    break;
                case 2:
                    i.putExtra("id", "2");
                    context.startActivity(i);
                    break;
                case 3:
                    i.putExtra("id", "3");
                    context.startActivity(i);
                    break;
                case 4:
                    i.putExtra("id", "4");
                    context.startActivity(i);
                    break;
                case 5:
                    i.putExtra("id", "5");
                    context.startActivity(i);
                    break;
                case 6:
                    i.putExtra("id", "6");
                    context.startActivity(i);
                    break;
                case 7:
                    i.putExtra("id", "7");
                    context.startActivity(i);
                    break;
                case 8:
                    i.putExtra("id", "8");
                    context.startActivity(i);
                    break;
                case 9:
                    i.putExtra("id", "9");
                    context.startActivity(i);
                    break;
                case 10:
                    i.putExtra("id", "10");
                    context.startActivity(i);
                    break;
                case 11:
                    i.putExtra("id", "11");
                    context.startActivity(i);
                    break;
                case 12:
                    i.putExtra("id", "12");
                    context.startActivity(i);
                    break;


            }

    }
    @Override
    public int getItemCount() {
        return libraries.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView1, cardView2, cardView3;
        TextView topic, percent;
        TextView knowHow;
        View parent;
        ImageView info;
        ExpandableRelativeLayout exp;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.info);
            knowHow = itemView.findViewById(R.id.tvWatchAptAdapter3);
           // imageView=itemView.findViewById(R.id.imgDownAptAdapter3);
           // exp=itemView.findViewById(R.id.expLayoutAptAdapter3);
            cardView1=itemView.findViewById(R.id.card1AptAdapter3);
           // cardView2=itemView.findViewById(R.id.card2AptAdapter3);
            //cardView3=itemView.findViewById(R.id.card3AptAdapter3);
            parent=this.itemView;

            topic=itemView.findViewById(R.id.tvAptAdapter3);
            percent=itemView.findViewById(R.id.tvPercentAptAdapter);

        }
    }

}
