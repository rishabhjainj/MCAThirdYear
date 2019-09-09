package com.AbhiDev.edurecomm.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.AbhiDev.edurecomm.presenters.PasswordPresenter;
import com.AbhiDev.edurecomm.viewactions.BaseViewAction;
import com.wireout.R;
import com.wireout.presenters.PasswordPresenter;
import com.wireout.viewactions.BaseViewAction;

public class ChangePasswordActivity extends BaseActivity implements BaseViewAction {

    EditText oldPassword;
    EditText newPassword;
    PasswordPresenter presenter;
    Button generatePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPassword = findViewById(R.id.old_pass);
        newPassword = findViewById(R.id.new_pass);
        generatePass = findViewById(R.id.btn_pass_gen);
        presenter = new PasswordPresenter(this);

        generatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(oldPassword.getText().equals("")||newPassword.getText().equals("")){
                    showMessage("Both fields are required");
                }
                else{
                    presenter.resetPassword(oldPassword.getText().toString(),newPassword.getText().toString());
                    showMessage("Please wait..");
                    finish();

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
