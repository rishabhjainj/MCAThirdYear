package com.AbhiDev.edurecomm.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.wireout.adapters.EventsAdapter;
import com.wireout.adapters.RelatedCoursesAdapter;
import com.wireout.R;
import com.wireout.apiservices.Repository;
import com.wireout.events.EventsPresenter;
import com.wireout.events.EventsViewAction;
import com.wireout.events.Tab4_Events_Adapter;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class EventsActivity extends BaseActivity implements EventsViewAction , BaseSliderView.OnSliderClickListener{

    EventsPresenter presenter;
    OnEntitiesReceivedListener<Event> listener;
    FloatingActionMenu menuRed;
    ImageView[] dots;
    ArrayList<String> months;
    EventsAdapter eventsAdapter;
    //Tab4_RecyclerViewAdapter recyclerViewAdapter;
    Tab4_Events_Adapter events_adapter;
    RecyclerView recyclerView, recyclerView2;
    OnEntitiesReceivedListener<Event> eventListener;

    ArrayList<String> titles,dates,timeAM,timePM,labels,orgNames,addrs,distances;
    ArrayList<Integer> images;
    ArrayList<String> imageUrls;
    private SliderLayout imageSlider;
    HorizontalCalendar horizontalCalendar;
    RelativeLayout relativeLayout;
    TextView noEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();

        presenter = new EventsPresenter(new Repository());
        eventListener= new OnEntitiesReceivedListener<Event>(this) {
            @Override
            public void onReceived(List<Event> entities) {
                setSliderImages(entities.subList(0,5));
            }
        };
        presenter.getEvents(new HashMap<String, String>(),eventListener);
        listener = new OnEntitiesReceivedListener<Event>(this) {

            @Override
            public void onReceived(List<Event> events) {
                if(events.size()==0){
                    noEvents.setVisibility(View.VISIBLE);
                }
                else{
                    noEvents.setVisibility(View.INVISIBLE);
                }
                setEventsRecyclerView(events);
            }
        };
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        String finalDate = date+"T00:00:00.000000Z";
        Map<String,String> queryMap = new HashMap<>();
        Log.d("finaldate",date+"");
        queryMap.put("startTime__gte",finalDate);
        String cal_date = date.toString().substring(8,10);
        int endDate = Integer.parseInt(cal_date);
        endDate++;
        String finalendDate = date.toString().substring(0,7).concat("-"+endDate+"");
        queryMap.put("startTime__lte",finalendDate);
        Log.d("finaldate",finalendDate+"");
        presenter.getEvents(queryMap,listener);


    }
    public void setSliderImages(List<Event> eventList){
        HashMap<String, String> url_maps = new HashMap<String, String>();
        for(Event event: eventList){
            url_maps.put(event.getName(),event.getImagePath());
            Log.d("sliderImages",event.getImagePath()+"");
        }
        for (String name : url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            imageSlider.addSlider(textSliderView);

        }
    }
    public void initUi(){
        setContentView(R.layout.tab4_fragment);
        noEvents = findViewById(R.id.noEvents);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imageSlider = findViewById(R.id.slider);
        relativeLayout=(RelativeLayout)findViewById(R.id.rel_events_recyclerview);
        recyclerView2=(RecyclerView)findViewById(R.id.recyclerview_tab4_events);
        menuRed = (FloatingActionMenu)findViewById(R.id.menu_red);

        // for floating action button
        //menuRed.setBackground(new ColorDrawable(getResources().getColor(R.color.filter_blue)));
        // menuRed.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        menuRed.setMenuButtonColorNormal(getResources().getColor(R.color.lightpink));
        menuRed.setMenuButtonColorPressed(getResources().getColor(R.color.lightpink));

        final FloatingActionButton programFab1 = new FloatingActionButton(this);
        programFab1.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab1.setLabelText("Filters");
        programFab1.setImageResource(R.drawable.ic_action_edit2);
        menuRed.addMenuButton(programFab1);
        programFab1.setColorNormal(getResources().getColor(R.color.lightpink));
        programFab1.setColorPressed(getResources().getColor(R.color.lightpink));
        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab1.setLabelColors(ContextCompat.getColor(EventsActivity.this, R.color.grey),
                        ContextCompat.getColor(EventsActivity.this, R.color.light_grey),
                        ContextCompat.getColor(EventsActivity.this, R.color.white_transparent));
                programFab1.setLabelTextColor(ContextCompat.getColor(EventsActivity.this, R.color.black));

                startActivity(new Intent(EventsActivity.this, FiltersActivity.class));

                menuRed.close(true);

            }
        });
        final FloatingActionButton programFab2 = new FloatingActionButton(this);
        programFab2.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab2.setLabelText("Wishlist");
        programFab2.setImageResource(R.drawable.ic_action_heart2);
        menuRed.addMenuButton(programFab2);
        programFab2.setColorNormal(getResources().getColor(R.color.lightpink));
        programFab2.setColorPressed(getResources().getColor(R.color.lightpink));
        programFab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab2.setLabelColors(ContextCompat.getColor(EventsActivity.this, R.color.grey),
                        ContextCompat.getColor(EventsActivity.this, R.color.light_grey),
                        ContextCompat.getColor(EventsActivity.this, R.color.white_transparent));
                programFab2.setLabelTextColor(ContextCompat.getColor(EventsActivity.this, R.color.black));

                startActivity(new Intent(EventsActivity.this, WishlistActivity.class));

                menuRed.close(true);

            }
        });

//        months=new ArrayList<>();
//        titles=new ArrayList<>();
//        dates=new ArrayList<>();
//        timeAM=new ArrayList<>();
//        timePM=new ArrayList<>();
//        labels=new ArrayList<>();
//        orgNames=new ArrayList<>();
//        addrs=new ArrayList<>();
//        distances=new ArrayList<>();
//        images=new ArrayList<>();
//
//        images.add(R.drawable.im1);
//        images.add(R.drawable.im2);
//        images.add(R.drawable.im3);
//        images.add(R.drawable.im4);
//        images.add(R.drawable.im5);
//        images.add(R.drawable.im6);
//
//        imageUrls=new ArrayList<>();

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                relativeLayout.setVisibility(View.VISIBLE);
                String month="";
                Log.d("time",date.getTime().toString());
                String year = date.getTime().toString().substring(date.getTime().toString().length()-4,date.getTime().toString().length());
                Log.d("position_date",position+"");
                String mon=date.getTime().toString().substring(4,7);
                String cal_date=date.getTime().toString().substring(8,10);
                Log.d("mon",mon);
                Log.d("cal_date",cal_date);
                switch(mon){
                    case "Jan" :
                        month = "01";
                        break;
                    case "Feb" :
                        month= "02";
                        break;
                    case "Mar" :
                        month= "03";
                        break;
                    case "Apr" :
                        month= "04";
                        break;
                    case "May" :
                        month= "05";
                        break;
                    case "Jun" :
                        month= "06";
                        break;
                    case "Jul" :
                        month= "07";
                        break;
                    case "Aug" :
                        month= "08";
                        break;
                    case "Sep" :
                        month= "09";
                        break;
                    case "Oct" :
                        month= "10";
                        break;
                    case "Nov" :
                        month= "11";
                        break;
                    case "Dec" :
                        month= "12";
                        break;

                }

                Log.d("finaldate",year+"-"+month+"-"+cal_date+"T00:00:00.000000Z");
                String finalDate = year+"-"+month+"-"+cal_date+"T00:00:00.000000Z";
                Map<String,String> queryMap = new HashMap<>();
                queryMap.put("startTime__gte",finalDate);
                int endDate = Integer.parseInt(cal_date);
                endDate++;
                String ending = year+"-"+month+"-"+endDate+"T00:00:00.000000Z";
                queryMap.put("startTime__lte",ending);
                Log.d("finaldate",endDate+"");
                presenter.getEvents(queryMap,listener);
            }
        });
    }
    public void setEventsCardViewsAdapter(ArrayList<String> titles1,ArrayList<String> dates1,ArrayList<String> timeAM1,ArrayList<String> timePM1,ArrayList<String> labels1,ArrayList<String> orgNames1,ArrayList<String> addrs1,ArrayList<String> distances1,ArrayList<String> imageUrls1,ArrayList<String> months)
    {
        events_adapter=new Tab4_Events_Adapter(this,titles1,dates1,timeAM1,timePM1,labels1,orgNames1,addrs1,distances1,imageUrls1,months);
    }
    public void setEventsCardViews()
    {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(events_adapter);
        recyclerView2.hasFixedSize();
    }

    public void showDialog(String text)
    {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setMessage(text)
                .setTitle("Note")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        horizontalCalendar.refresh();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        relativeLayout.setVisibility(View.GONE);
    }
    public void clear()
    {
        titles.clear();
        dates.clear();
        months.clear();
        timeAM.clear();
        timePM.clear();
        labels.clear();
        addrs.clear();
        distances.clear();
        imageUrls.clear();
        orgNames.clear();
        //    recyclerView2.getAdapter().notifyDataSetChanged();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this)
        {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(events_adapter);
        recyclerView2.hasFixedSize();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void setEventsRecyclerView(List<Event> events) {
        eventsAdapter=new EventsAdapter(this,events);
        recyclerView2.hasFixedSize();
        recyclerView2.removeAllViews();
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.removeAllViewsInLayout();
        eventsAdapter=new EventsAdapter(this,events);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(eventsAdapter);
        eventsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

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
}
