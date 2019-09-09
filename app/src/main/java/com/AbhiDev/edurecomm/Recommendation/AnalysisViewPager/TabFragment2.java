package com.AbhiDev.edurecomm.Recommendation.AnalysisViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.wireout.Activities.LoginActivity;

import com.wireout.Activities.PaymentsActivity;
import com.wireout.apiservices.Repository;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Slot;
import com.wireout.models.SlotBook;
import com.wireout.models.University;
import com.wireout.presenters.TabFragment2Presenter;
import com.wireout.R;
import com.wireout.Recommendation.BaseFragment;
import com.wireout.Recommendation.TabFragmentViewAction;
import com.wireout.common.ChildAnimationExample;
import com.wireout.common.PrefManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class TabFragment2 extends BaseFragment implements TabFragmentViewAction, BaseSliderView.OnSliderClickListener,AdapterView.OnItemSelectedListener, ViewPagerEx.OnPageChangeListener {

    PrefManager prefManager;
    private ArrayList<Drawable> mData;
    private SwipeStack mSwipeStack;
    String contact;
    String selectedSlot;
    ArrayList<Slot> slotArrayList;
    TabFragment2Presenter presenter;
    OnEntitiesReceivedListener<SlotBook> slotBookListener;
    OnEntitiesReceivedListener<Slot> listener;
    private SwipeStackAdapter mAdapter;
    ImageView happy_parent;

    ImageView call,duo,skype;
    int id=1;
    int x=5;
    Button confirmBooking;
    TabFragmentViewAction tabFragmentViewAction;
    Spinner slotSpinner;
    CardView pay;
    ImageView b4;
    LinearLayout bookingComplete;
    Bitmap bitmap;
    CardView cardx;
    EditText idText;
    View view;
    TextView payMoney;
    CardView testimonials;

    private SliderLayout mDemoSlider;

    @Override
    public int getLayoutResId() {
        return R.layout.tab_fragment_2;
    }

    @Override
    public void setUniversitiesRecyclerView(List<University> universities) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      //  bookingComplete.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void inOnCreateView(View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prefManager = new PrefManager(getActivity());
        this.view = view;
        slotArrayList = new ArrayList<>();
        presenter = new TabFragment2Presenter(this,new Repository());
        mSwipeStack = (SwipeStack) view.findViewById(R.id.swipeStack);
        listener = new OnEntitiesReceivedListener<Slot>(getContext()) {
            @Override
            public void onReceived(List<Slot> entities) {
                slotArrayList.addAll( entities);
                setSlots(entities);
            }
        };
        presenter.getSlots(listener);
        b4 = (ImageView) view.findViewById(R.id.b4);
        b4.setColorFilter(getResources().getColor(R.color.dark_purple));
        bookingComplete = view.findViewById(R.id.slot_complete);
        confirmBooking= view.findViewById(R.id.confirm);
        idText = view.findViewById(R.id.idEditText);
        call = view.findViewById(R.id.call);
        duo = view.findViewById(R.id.duo);
        skype = view.findViewById(R.id.skype);

        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idText.getText().toString().length()>3){
                    contact = idText.getText().toString();
                    selectedSlot = slotSpinner.getSelectedItem().toString();
                    ArrayList<String> slotString = new ArrayList<>();
                    for(Slot slot:slotArrayList){
                        slotString.add(slot.getTitle());
                    }


                    int slotid =0;
                    slotid = slotString.indexOf(selectedSlot);
                    int slotIdInList = slotArrayList.get(slotid).getId();
                    String mode = "skype";
                    switch (id){
                        case 1: mode = "skype";
                        break;
                        case 2: mode = "duo";
                        break;
                        case 3: mode = "whatsapp";
                        break;
                    }
                    presenter.bookSlot(selectedSlot,mode,contact);
                }
                else{
                    showMessage("Enter valid contact details!");
                }

            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id= 3;
                idText.setHint("Your WhatsApp number");
            }
        });
        duo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=2;
                idText.setHint("Your Duo number");
            }
        });
        skype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=1;
                idText.setHint("Your Skype Id");
            }
        });

        slotSpinner = view.findViewById(R.id.spinner);

        pay=view.findViewById(R.id.payButton);

        testimonials=view.findViewById(R.id.testcard);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

//        Double height=displayMetrics.heightPixels/(4.0);
//
//        testimonials.getLayoutParams().height=height.intValue();
//        testimonials.getLayoutParams().width=displayMetrics.widthPixels;

        payMoney = view.findViewById(R.id.pay_money);
        payMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PaymentsActivity.class);
                intent.putExtra("price","2500");
                startActivity(intent);
            }
        });

//        pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMessage("clicked");
//                Intent intent = new Intent(getContext(), PaymentsActivity.class);
//                intent.putExtra("price","1250");
//                startActivity(intent);
//
//            }
//        });
        happy_parent = (ImageView) view.findViewById(R.id.hp);
        happy_parent.setColorFilter(getResources().getColor(R.color.white));

        final TextView textView=(TextView)view.findViewById(R.id.scrolly);
        mData = new ArrayList<>();
        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(new SwipeStack.SwipeStackListener() {

            @Override
            public void onViewSwipedToRight(int position) {
                //String swipedElement = mAdapter.getItem(position);
                // Toast.makeText(this, getString(R.string.view_swiped_right, swipedElement),
                //      Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewSwipedToLeft(int position) {
                //String swipedElement = mAdapter.getItem(position);
                // Toast.makeText(this, getString(R.string.view_swiped_left, swipedElement),
                //       Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStackEmpty() {
                //Toast.makeText(this, R.string.stack_empty, Toast.LENGTH_SHORT).show();
                textView.setVisibility(View.VISIBLE);

            }
        });

        fillWithTestData();

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        setUpTestimonialsSlider();

    }

    @Override
    public void confirmVisible() {
        bookingComplete.setVisibility(View.VISIBLE);
    }

    public void setSlots(List<Slot> slots){
        ArrayList<String> slotString = new ArrayList<>();
        for(Slot slot:slots){
            slotString.add(slot.getTitle());
        }
        if(getContext()!=null){
            slotSpinner.setOnItemSelectedListener(this);
            ArrayAdapter<String> modeArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, slotString);
            modeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            slotSpinner.setAdapter(modeArrayAdapter);
        }
    }
    public static TabFragment2 create() {

        return new TabFragment2();
    }


    public void startLoginActivity(){
        Toast.makeText(getActivity(),"Login To Add Products To Cart",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
    }
    private void fillWithTestData() {


        mData.add(getResources().getDrawable(R.drawable.r1));
        mData.add(getResources().getDrawable(R.drawable.r2));
        mData.add(getResources().getDrawable(R.drawable.r3));
        mData.add(getResources().getDrawable(R.drawable.r4));
        mData.add(getResources().getDrawable(R.drawable.r5));
        mData.add(getResources().getDrawable(R.drawable.r6));
        mData.add(getResources().getDrawable(R.drawable.r7));
        mData.add(getResources().getDrawable(R.drawable.r8));
        mData.add(getResources().getDrawable(R.drawable.r9));
        mData.add(getResources().getDrawable(R.drawable.r10));

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class SwipeStackAdapter extends BaseAdapter {

        private List<Drawable> mData;

        public SwipeStackAdapter(List<Drawable> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Drawable getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {

                LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
                convertView = mInflater.inflate(R.layout.sliding_card_item, parent, false);
            }

            ImageView textViewCard = (ImageView) convertView.findViewById(R.id.preview);
            textViewCard.setImageDrawable(mData.get(position));



            return convertView;
        }
    }





    @Override
    public void startAnalysisNotDone() {


    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }

    void setUpTestimonialsSlider(){
        mDemoSlider = view.findViewById(R.id.slider);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Kashish Garg (6 Years)",R.drawable.test_1);
        file_maps.put("Simran Arora (9 Years)" , R.drawable.test_2);
        file_maps.put("Medhavi Singh (13 Years)",R.drawable.test_3);
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView
                    .getBundle()
                    .putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        mDemoSlider.setPresetTransformer("ZoomOut");
        mDemoSlider.setCustomAnimation(new ChildAnimationExample());
    }
}