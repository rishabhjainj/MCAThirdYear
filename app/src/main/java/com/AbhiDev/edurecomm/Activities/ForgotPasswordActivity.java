package com.AbhiDev.edurecomm.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wireout.R;
import com.wireout.apiservices.responses.ForgotPasswordResponse;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.presenters.PasswordPresenter;
import com.wireout.viewactions.BaseViewAction;

public class ForgotPasswordActivity extends AppCompatActivity implements BaseViewAction {

    EditText email;
    PasswordPresenter presenter;
    OnEntitiesReceivedListener<ForgotPasswordResponse> listener;
    Button genPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        presenter = new PasswordPresenter(this);
        email = findViewById(R.id.input_email);
        genPass = findViewById(R.id.btn_pass_gen);
        genPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText()!=null||!email.getText().equals("")){
                    presenter.forgotPassword(email.getText().toString());
                    finish();

                }
                else{
                   showMessage("Enter a valid Email!");
                }
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }
}
