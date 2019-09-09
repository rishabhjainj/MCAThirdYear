package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wireout.R;

import java.util.List;

public class DialogFlowOptionButtons extends RecyclerView.Adapter<DialogFlowOptionButtons.MyViewHolder>  {
    Context context;
    List<String> optionsList;

    private int previousPosition=0;
    private LayoutInflater inflater;

    public DialogFlowOptionButtons(Context context, List<String> optionsList)
    {
        this.optionsList = optionsList;
        this.context = context;

        inflater= LayoutInflater.from(context);
    }

    public void OnClicked(String option){
        selectOption(option);
    }
    public void selectOption(String option){

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.yantra_bot_quick_response_button,parent,false);
        MyViewHolder recyclerViewHolder=new MyViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        String tag=optionsList.get(position);
        //String tag = tags.get(position);
        holder.textView.setText(tag);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClicked(optionsList.get(position));
            }
        });

    }
    @Override
    public int getItemCount() {
        return optionsList.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{

        public TextView textView;
        public CardView cardView;
        public View parent;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.tagsCard);
            textView = (TextView) itemView.findViewById(R.id.optionsText);
            parent = itemView;
        }
    }



}
