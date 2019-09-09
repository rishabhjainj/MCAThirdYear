package com.AbhiDev.edurecomm.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;

import com.basgeekball.awesomevalidation.ValidationStyle;
import com.wireout.R;

import java.util.HashMap;
import java.util.Map;

public class PaymentsActivity extends BaseActivity {

    String Key;
    ImageView CartIcon;
    Map<String,String> map= new HashMap<>();
    TextView CODText;
    EditText Address;
    EditText promoCode;
    Dialog baseDialog;
    EditText Pincode;
    Button applyPromo;
    RelativeLayout promoAppliedLayout;
    ImageView backBtn;
    EditText Landmark;
    TextView promoAppliedText;
    Float totalPrice;
    Boolean valid = false;
    Intent intent;
    String price;
    TextView priceTextView;
    Button pay;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        Intent i = getIntent();
        price = i.getExtras().getString("price");
        priceTextView = findViewById(R.id.price);
        priceTextView.setText(price+"");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        promoAppliedText = findViewById(R.id.appliedText);
        pay = findViewById(R.id.payfor);
        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        promoCode = findViewById(R.id.couponTextView);
        applyPromo = findViewById(R.id.applyPromo);
        promoAppliedLayout = findViewById(R.id.promoAppliedLayout);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.name,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.emailerror);
        awesomeValidation.addValidation(this,R.id.input_address, "^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$",R.string.addresserror);
        awesomeValidation.addValidation(this,R.id.pincode, "^[1-9][0-9]{5}$",R.string.pincodeerror);
        awesomeValidation.addValidation(this,R.id.phone, "[0-9]+",R.string.mobileerror);




        applyPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("promocode",promoCode.getText().toString());
                if (promoCode.getText() != null) {
                    String code = promoCode.getText().toString();
                    int payment = Integer.parseInt(price);
                    if (code.equals("yantra5")) {
                        price = getDisountedPrice(payment, 0.05);
                    } else if (code.equals("yantra10")) {
                        price = getDisountedPrice(payment, 0.1);
                    } else if (code.equals("yantra20")) {
                        price = getDisountedPrice(payment, 0.2);
                    } else if (code.equals("yantra100")) {
                        price = getDisountedPrice(payment, 1);
                    }
                    else {
                        showMessage("Invalid promo code!");
                    }

                }
                else {
                    showMessage("Enter a valid coupon code.");
                }
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://www.instamojo.com/@wireoutteam/"));
                    startActivity(i);
                }
            }
        });






    }

    public String getDisountedPrice(int payment,double discount){
        payment = (int)(payment - payment*(discount));
        price = payment+"";
        priceTextView.setText(price);
        promoAppliedText.setText((discount*100)+"% discount applied successfully");
        promoAppliedLayout.setVisibility(View.VISIBLE);
        return price;
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
