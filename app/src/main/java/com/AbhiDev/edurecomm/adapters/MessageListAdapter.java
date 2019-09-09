package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.wireout.R;
import com.wireout.models.Message;

import java.util.List;

//holds a list of messages to be displayed in recycler view

public class MessageListAdapter extends RecyclerView.Adapter  {
    private Context mContext;
    private List<Message> mMessageList;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    RecyclerView mMessageRecycler;

    public MessageListAdapter(Context context , List<Message> mMessageList,RecyclerView mMessageRecycler){
        this.mContext = context;
        this.mMessageList = mMessageList;
        Log.d("MyMessage",mMessageList.size()+"");
        this.mMessageRecycler = mMessageRecycler;
        //in this adapter we use two viewholders
    }
    @Override
    public int getItemCount() {

        return mMessageList.size();
    }
    public void addMessage(Message message){
        mMessageList.add(message);
        notifyDataSetChanged();
        mMessageRecycler.scrollToPosition(mMessageList.size()-1);
    }
    public void removeAndAddMessage(Message message){
        mMessageList.remove(mMessageList.size()-1);
        mMessageList.add(message);
        notifyDataSetChanged();
        mMessageRecycler.scrollToPosition(mMessageList.size()-1);
    }
    @Override
    public int getItemViewType(int position) {
        Message message = (Message) mMessageList.get(position);

        if (message.getSender().equals("user")) {
            // If the current user is the sender of the message
            Log.d("MyMessage","user");
            return VIEW_TYPE_MESSAGE_SENT;

        } else {
            // If some other user sent the message
            Log.d("MyMessage","some other");
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;


        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            Log.d("MyMessage","inflated sent");
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            Log.d("MessageList","inflated recieved");
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = (Message) mMessageList.get(position);


        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText, timeText, nameText;
        ImageView profileImage;
        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }
        void bind(Message message) {
            messageText.setText(message.getText());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.getCreatedAt()+"");
            nameText.setText(message.getSender()+"");

            // Insert the profile image from the URL into the ImageView.

        }
    }
    private class SentMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText, timeText;
        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }
        void bind(Message message) {
            messageText.setText(message.getText());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.getCreatedAt()+"");

            // Insert the profile image from the URL into the ImageView.

        }

    }
    //now do the following:

//    Obtain a message from MessageList
//    Determine whether it is a sent message or a received message
//    Inflate the appropriate layout for the ViewHolder
//    Pass the message to the ViewHolder for binding



}


