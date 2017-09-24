package com.example.catwong.tita.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.util.HttpHelper;
import com.example.catwong.tita.util.PreferencesManager;
import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * Created by CatWong on 9/23/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText textEmail;
    private EditText textPwd;
    private Button btnLogin;
    private TextView txtToRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = (EditText) findViewById(R.id.login_email_input);
        textPwd = (EditText) findViewById(R.id.login_pwd_input);
        btnLogin = (Button) findViewById(R.id.login_button);
        txtToRegister = (TextView) findViewById(R.id.to_register_button);
        final PreferencesManager preferenceManager = PreferencesManager.getInstance(this);

        final String name = CommonKey.NULL;

        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == HttpHelper.MSG_SUCCESS) {
                    JsonObject jsonObject = (JsonObject) msg.obj;
                    HttpHelper.setToken(jsonObject.get("token").getAsString());

                    preferenceManager.putString(CommonKey.EMAIL, String.valueOf(textEmail.getText()));
                    preferenceManager.putString(CommonKey.NAME, name);
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                    alertDialog.setTitle("ERROR!");
                    alertDialog.setMessage("Invalid Username or Password!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                return true;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body = "email=" + textEmail.getText() + "&password=" + textPwd.getText();
                HttpHelper.post(handler, HttpHelper.LOG_IN_URL, body, false);
            }
        });

        txtToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

}
