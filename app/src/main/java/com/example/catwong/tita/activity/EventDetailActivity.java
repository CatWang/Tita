package com.example.catwong.tita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.util.Common;

/**
 * Created by CatWong on 9/23/17.
 */

public class EventDetailActivity extends AppCompatActivity {

    private TextView txtTitle, txtMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragement_event_detail);

        Intent i = getIntent();
        Event event = (Event) i.getParcelableExtra(CommonKey.EVENT);


    }
}
