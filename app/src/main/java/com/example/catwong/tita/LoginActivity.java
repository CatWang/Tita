package com.example.catwong.tita;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.util.PreferencesManager;

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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = true;
                String name = CommonKey.NULL;

                // HTTP here

                if (success) {
                    preferenceManager.putString(CommonKey.EMAIL, String.valueOf(textEmail.getText()));
                    preferenceManager.putString(CommonKey.NAME, name);
                    startActivity(new Intent(LoginActivity.this, CalendarActivity.class));
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
