package com.example.catwong.tita.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.catwong.tita.R;
import com.example.catwong.tita.util.PreferencesManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PreferencesManager preferenceManager = PreferencesManager.getInstance(this);

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
//                    if (preferenceManager.getBoolean(CommonKey.PREF_IS_FIRST_LAUNCH, true)) {
//                        preferenceManager.setBoolean(CommonKey.PREF_IS_FIRST_LAUNCH, false);
//                        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
//                    } else if (!preferenceManager.getBoolean(CommonKey.LOGGEDIN, false)) {
//                        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
//                    } else if (preferenceManager.getBoolean(CommonKey.LOGGEDIN, false)) {
//                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
//                    }


                    finish();
                }
            }
        };

        loadingThread.start();

    }
}

