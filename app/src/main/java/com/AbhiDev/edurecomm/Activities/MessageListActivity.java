package com.AbhiDev.edurecomm.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.wireout.R;
import com.wireout.adapters.DialogFlowOptionButtons;
import com.wireout.adapters.MessageListAdapter;
import com.wireout.models.Message;
import com.wireout.yantra_bot.CareerSearchHandler;
import com.wireout.yantra_bot.InvalidIntentException;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ai.api.AIListener;
import ai.api.RequestExtras;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;
import ai.api.model.ResponseMessage;
import ai.api.model.Result;
import ai.api.ui.AIButton;

public class MessageListActivity extends AppCompatActivity implements AIListener,TextToSpeech.OnInitListener {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Message> messageList;
    DialogFlowOptionButtons botButtonsAdapter;
    RecyclerView botButtonsRecyclerView;
    EditText userInputEditText;
    ImageView sendButton;
    ArrayList<String> botButtonList = new ArrayList<>();
    AIButton aiButton;
    AIService aiService;
    Intent j;
    String currentDateTimeString;
    Message m1;
    private TextToSpeech myTTS;
    private int MY_DATA_CHECK_CODE = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        userInputEditText = findViewById(R.id.edittext_chatbox);
        sendButton = findViewById(R.id.button_chatbox_send);

        setBotButtonsRecyclerView();
        messageList = new ArrayList<>();
        m1 = Message.createBotGreetingMessage();
        messageList.add(m1);

        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageRecycler.hasFixedSize();
        mMessageRecycler.removeAllViews();
        mMessageRecycler.setNestedScrollingEnabled(false);
        mMessageRecycler.removeAllViewsInLayout();
        mMessageAdapter = new MessageListAdapter(this, messageList,mMessageRecycler);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
        mMessageAdapter.notifyDataSetChanged();


        Intent checkTTSIntent = new Intent();

        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest();
        } else
            initAiClient();
    }

    protected void initAiClient(){
        final AIConfiguration config = new AIConfiguration("fe658cb71e33443ca71aa23839ad7c54",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiService = AIService.getService(this, config);
        aiService.setListener(this);
        aiButton = findViewById(R.id.micButton);
        aiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aiService.startListening();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userInputEditText.getText().toString().equals(""))
                    return;
                String query = userInputEditText.getText().toString().trim();
                Message message = new Message();
                message.setSender("user");
                message.setText(query);
                String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                message.setCreatedAt(currentDateTimeString);
                mMessageAdapter.addMessage(message);
                userInputEditText.setText("");
                new TextRequestTask().execute(query);

            }
        });
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                101);
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
                    initAiClient();
                }
                return;
            }
        }
    }

    @Override
    public void onInit(int i) {
        Log.d("YantraBot", "Initial message speak");
        myTTS.speak(m1.getText(), TextToSpeech.QUEUE_FLUSH, null);
//        myTTS.stop();
    }

    public void setBotButtonsRecyclerView() {

        botButtonList.add("engineering & technology");
        botButtonList.add("data science");
        botButtonList.add("finance");
        botButtonList.add("lifestyle");


        botButtonsRecyclerView = (RecyclerView) findViewById(R.id.options_recycler);
        botButtonsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        botButtonsRecyclerView.setLayoutManager(linearLayoutManager);
        botButtonsAdapter = new DialogFlowOptionButtons(MessageListActivity.this, botButtonList){
            @Override
            public void selectOption(String botButtonText) {
                createMessage(botButtonText,getTime(),"user");
                setBotButtonsDisplayed(false);
                new TextRequestTask().execute(botButtonText);
            }
        };
        botButtonsRecyclerView.setAdapter(botButtonsAdapter);
        botButtonsAdapter.notifyDataSetChanged();
    }

    private void updateBotButtons(List<String> buttonTextList){
        botButtonList.clear();
        botButtonList.addAll(buttonTextList);
        Log.d("YantraBot", buttonTextList.size() + " ");
        botButtonsAdapter.notifyDataSetChanged();
    }

    private void setBotButtonsDisplayed(boolean displayed){
//        botButtonsRecyclerView.setVisibility(displayed ? View.VISIBLE : View.GONE);
//        botButtonsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResult(final AIResponse result) {

        if(result.getResult().getResolvedQuery() != null) {
            Message message = new Message();
            message.setSender("user");
            message.setText(result.getResult().getResolvedQuery());
            currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
            message.setCreatedAt(currentDateTimeString);
            mMessageAdapter.addMessage(message);
            onResultUtil(result);
        }
    }

    void onResultUtil(final AIResponse result){

        final Result results = result.getResult();
        final String speech = results.getFulfillment().getSpeech();
        final HashMap<String,JsonElement> parameter = result.getResult().getParameters();
        final Fulfillment fulfillment = result.getResult().getFulfillment();
        Log.d("Fulfillment", fulfillment.getSource() + " " + fulfillment.getData() + " " + fulfillment.getDisplayText() + fulfillment.getMessages());
        for(ResponseMessage msg : fulfillment.getMessages())
            Log.d("Fulfillment", msg.toString());
        Log.d("DFALL",result.getResult().getResolvedQuery()+","+result.getResult().getAction()+":"+parameter.get("Course")+","+parameter.get("University"));


        if(result.getResult().getResolvedQuery()!=null) {

            /********************Show Chat Message on screen*************/

            createMessage("..."," ","YantraBot");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Message resultMessage = new Message();
                    resultMessage.setSender("Poker Bot");
                    resultMessage.setText(speech);
                    currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                    resultMessage.setCreatedAt(currentDateTimeString);
                    mMessageAdapter.removeAndAddMessage(resultMessage);
                    myTTS.speak(speech.toString(), TextToSpeech.QUEUE_FLUSH, null);
                }

            }, 1000);

            /*********** Handle Bot Intent *************************/

            if(!result.getResult().isActionIncomplete()) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        j = new Intent(MessageListActivity.this, DialogFlowResultActivity.class);
                        if (result.getResult().getAction().equals("show_course")) {
                            j.putExtra("type", 2 + "");
                            j.putExtra("search", parameter.get("Course") + "");
                            j.putExtra("university", parameter.get("University") + "");
                            startActivity(j);
                        } else if (result.getResult().getAction().equals("show_university")) {
                            j.putExtra("type", 1 + "");
                            j.putExtra("search", parameter.get("University") + "");
                            j.putExtra("university", parameter.get("University") + "");
                            startActivity(j);
                        } else if (result.getResult().getAction().equals("career_search")) {
                            try {
                                CareerSearchHandler handler = new CareerSearchHandler(result);
                                setBotButtonsDisplayed(false);
                                j.putExtra("type", 2 + "");
                                j.putExtra("search", parameter.get("Course") + "");
                                j.putExtra("university", parameter.get("University") + "");
                                j.putExtra("career_search", handler.getBundledParameters());
                                startActivity(j);

                            } catch (InvalidIntentException e) {
                                Log.d("YantraBot", e.getMessage());
                                e.printStackTrace();
                            }

                        }
                    }
                }, 2500);

            } else {    //show bot buttons
                if (result.getResult().getAction().equals(CareerSearchHandler.ACTION)){
                    setBotButtonsDisplayed(true);
                    try {
                        CareerSearchHandler handler = new CareerSearchHandler(result);
                        updateBotButtons(handler.getQuickResponseList());

                    } catch (InvalidIntentException e) {
                        Log.d("YantraBot", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
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
    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

        Toast.makeText(this,"listening",Toast.LENGTH_SHORT).show();
        aiButton.setImageDrawable(getResources().getDrawable(R.drawable.headphones));
    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

        aiButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_chat_mic));
    }
    public void createMessage(String text,String createtime,String sender){
        Message dotmessage = new Message();
        dotmessage.setSender(sender);
        dotmessage.setText(text);
        dotmessage.setCreatedAt(createtime);
        mMessageAdapter.addMessage(dotmessage);
    }
    public String getTime(){
        return currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = new TextToSpeech(this, this);
                //speak initial message
            }
            else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    class TextRequestTask extends AsyncTask<String, Void, AIResponse> {
        private Exception exception = null;
        protected AIResponse doInBackground(String... text) {
            AIResponse resp = null;
            try {
                resp = aiService.textRequest(text[0], new RequestExtras());
            } catch (Exception e) {
                this.exception = e;
            }
            return resp;
        }
        protected void onPostExecute(AIResponse response) {
            if (this.exception == null) {
                onResultUtil(response);
            } else {
                Log.d("TextRequestTask", exception.getMessage());
            }
        }
    }


}
