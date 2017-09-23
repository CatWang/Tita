package com.example.catwong.tita.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.util.PreferencesManager;

/**
 * Created by CatWong on 9/23/17.
 */

public class RegisterActivity extends AppCompatActivity{

    private Button registerButton;
    private EditText inputEmail, inputName, inputPwd, inputPwdConf;
    private TextView textToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final PreferencesManager preferenceManager = PreferencesManager.getInstance(this);

        registerButton = (Button) findViewById(R.id.register_button);
        inputEmail = (EditText) findViewById(R.id.register_email_input);
        inputName = (EditText) findViewById(R.id.register_name_input);
        inputPwd = (EditText) findViewById(R.id.register_pwd_input);
        inputPwdConf = (EditText) findViewById(R.id.register_confirm_pwd_input);
        textToLogin = (TextView) findViewById(R.id.to_login_button);

        final LayoutInflater factory = LayoutInflater.from(this);

        final Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(String.valueOf(inputPwd.getText()).equals(String.valueOf(inputPwdConf.getText())))) {
                    AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
                    alertDialog.setTitle("Not Match!");
                    alertDialog.setMessage("Password & Confirmation Must Match!");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                else {
                    boolean success = true;

                    // HTTP Request here

                    if (success) {
                        preferenceManager.setBoolean(CommonKey.LOGGEDIN, true);
                        preferenceManager.putString(CommonKey.EMAIL, String.valueOf(inputEmail.getText()));
                        preferenceManager.putString(CommonKey.NAME, String.valueOf(inputName.getText()));
                        startActivity(i);
                        finish();
                    }
                }

            }
        });

        textToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}