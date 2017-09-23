package com.example.catwong.tita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.catwong.tita.R;
import com.example.catwong.tita.fragment.CalendarFragment;

/**
 * Created by CatWong on 9/23/17.
 */

public class RegisterActivity extends AppCompatActivity{

    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (Button) findViewById(R.id.register_button);
        final Intent i = new Intent(RegisterActivity.this, CalendarFragment.class);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }
}