package com.AbhiDev.edurecomm.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.AbhiDev.edurecomm.R;
import com.AbhiDev.edurecomm.common.MyApplication;
import com.AbhiDev.edurecomm.listeners.OnUserReceivedListener;
import com.AbhiDev.edurecomm.models.User;
import com.AbhiDev.edurecomm.presenters.CurrentUserPresenter;
import com.AbhiDev.edurecomm.viewactions.BaseViewAction;
import com.github.bassaer.chatmessageview.model.ChatUser;
import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.util.ChatBot;
import com.github.bassaer.chatmessageview.view.ChatView;
import com.google.gson.JsonElement;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.listeners.OnUserReceivedListener;
import com.wireout.models.User;
import com.wireout.presenters.CurrentUserPresenter;
import com.wireout.viewactions.BaseViewAction;


import java.util.HashMap;
import java.util.Random;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import ai.api.ui.AIButton;

public class ChatViewActivtity extends AppCompatActivity implements OnUserReceivedListener, BaseViewAction, AIListener,TextToSpeech.OnInitListener{
    private ChatView mChatView;
    String myName;
    int myId = 0;
    Bitmap myIcon;
    ChatUser me;
    AIButton aiButton;
    AIService aiService;
    Intent j;
    ChatUser you;
    private TextToSpeech myTTS;
    private int MY_DATA_CHECK_CODE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_message_view);
         myName = "";

        if(MyApplication.getInstance().prefManager.isLoggedIn()){
            new CurrentUserPresenter(this,this).getCurrentUser();
        }

        else{
            myName = "Student";
            me = new ChatUser(myId, myName, myIcon);
        }
        //User id

        //User icon
        myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_2);
        //User name


        int yourId = 1;
        Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ai_bot);
        String yourName = "Poker Bot";


        you = new ChatUser(yourId, yourName, yourIcon);

        mChatView = (ChatView)findViewById(R.id.chat_view);

        //Set UI parameters if you need
        mChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.green500));
        mChatView.setLeftBubbleColor(Color.WHITE);
        mChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.blueGray500));
        mChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.green));
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.WHITE);
        mChatView.setSendTimeTextColor(Color.WHITE);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setInputTextHint("new message...");
        mChatView.setMessageMarginTop(5);
        mChatView.setMessageMarginBottom(5);
        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new message
                Message message = new Message.Builder()
                        .setUser(me)
                        .setRight(true)
                        .setText(mChatView.getInputText())
                        .hideIcon(true)
                        .build();


                //Set to chat view
                mChatView.send(message);
                //Reset edit text
                mChatView.setInputText("");

                //Receive message
                final Message receivedMessage = new Message.Builder()
                        .setUser(you)
                        .setRight(false)
                        .setText(ChatBot.INSTANCE.talk(me.getName(), message.getText()))
                        .build();

                // This is a demo bot
                // Return within 3 seconds
                int sendDelay = (new Random().nextInt(4) + 1) * 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.receive(receivedMessage);
                    }
                }, sendDelay);
            }

        });
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            makeRequest();
        }
        final AIConfiguration config = new AIConfiguration("fe658cb71e33443ca71aa23839ad7c54",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        final AIService aiService = AIService.getService(this, config);
        aiService.setListener(this);
        aiButton = findViewById(R.id.micButton);
        aiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aiService.startListening();
            }
        });


    }
    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                101);
    }

    @Override
    public void onUserReceived(User user) {
        me = new ChatUser(myId, user.getFirstName(), myIcon);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {


                } else {

                }
                return;
            }
        }
    }

    @Override
    public void onInit(int i) {

    }


    @Override
    public void onResult(AIResponse result) {
//        final Result results = result.getResult();
//        final List<ResponseMessage> messages = results.getFulfillment().getMessages();
//        for (ResponseMessage message : messages) {
//           Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show();
//        }
//        final List<ResponseMessage> messages = results.getFulfillment().getMessages();
//        for (ResponseMessage message : messages) {
//           Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show();
//        }
        HashMap<String,JsonElement> parameter = result.getResult().getParameters();
        Log.d("DFALL",result.getResult().getResolvedQuery()+","+result.getResult().getAction()+":"+parameter.get("Course")+","+parameter.get("University"));


        if(result.getResult().getResolvedQuery()!=null) {
            Message message = new Message.Builder()
                    .setUser(me)
                    .setRight(true)
                    .setText(result.getResult().getResolvedQuery())
                    .hideIcon(true)
                    .build();
            mChatView.send(message);
            //Reset edit text
            final Result results = result.getResult();
            final String speech = results.getFulfillment().getSpeech();
            mChatView.setInputText("");
            final Message receivedMessage = new Message.Builder()
                    .setUser(you)
                    .setRight(false)
                    .setText(speech.toString())
                    .build();


                    mChatView.receive(receivedMessage);



        }


        if(result.getResult().getAction().equals("show_course")) {
            j = new Intent(ChatViewActivtity.this, DialogFlowResultActivity.class);
            j.putExtra("type", 2+"");
            j.putExtra("search", parameter.get("Course")+"");
            j.putExtra("university",parameter.get("University")+"");
            startActivity(j);
        }
        else if(result.getResult().getAction().equals("show_university")) {
            j = new Intent(ChatViewActivtity.this, DialogFlowResultActivity.class);
            j.putExtra("type", 1+"");
            j.putExtra("search", parameter.get("University")+"");
            j.putExtra("university",parameter.get("University")+"");
            startActivity(j);
        } else if(result.getResult().getAction().equals("career_search")) {
            j = new Intent(ChatViewActivtity.this, DialogFlowResultActivity.class);
            j.putExtra("type", 2+"");
            j.putExtra("search", parameter.get("Course")+"");
            j.putExtra("university",parameter.get("University")+"");
            startActivity(j);
        }



        final Result results = result.getResult();
        final String speech = results.getFulfillment().getSpeech();
        //Toast.makeText(this,speech.toString(),Toast.LENGTH_SHORT).show();
        myTTS.speak(speech.toString(), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onError(final AIError error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("ApiAi", "onError"+error.getMessage());
                // TODO process error here
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        aiService = null;
    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

        Toast.makeText(this,"listening",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = new TextToSpeech(this, this);
            }
            else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

}
