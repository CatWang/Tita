package com.example.catwong.tita.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.catwong.tita.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread loadingThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(1000);
                } catch (Exception e) {

                } finally {
                    Intent i = new Intent(MainActivity.this,
                            HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };

        loadingThread.start();
    }
}
