package com.example.catwong.tita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.util.Common;

import java.util.ArrayList;

/**
 * Created by CatWong on 9/23/17.
 */

public class EventDetailActivity extends AppCompatActivity {

    private TextView txtTitle, txtMore, txtStartTime, txtEndTime;
    private TextView txtLocation, txtLink, txtDescription;

    private ImageView imgAdd, imgLike, imgShare, imgEmail, imgFb;
    private ImageView imgTw, imgTrash;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragement_event_detail);

        txtTitle = (TextView) findViewById(R.id.detail_event_title);
        txtMore = (TextView) findViewById(R.id.detail_txt_more);
        txtStartTime = (TextView) findViewById(R.id.detail_start_time);
        txtEndTime = (TextView) findViewById(R.id.detail_end_time);
        txtLocation = (TextView) findViewById(R.id.detail_location);
        txtLink = (TextView) findViewById(R.id.detail_link);
        txtDescription = (TextView) findViewById(R.id.detail_description);

        imgAdd = (ImageView) findViewById(R.id.detail_icon_add);
        imgLike = (ImageView) findViewById(R.id.detail_icon_like);
        imgShare = (ImageView) findViewById(R.id.detail_share);
        imgEmail = (ImageView) findViewById(R.id.detail_email);
        imgFb = (ImageView) findViewById(R.id.detail_fb);
        imgTw = (ImageView) findViewById(R.id.detail_tw);
        imgTrash = (ImageView) findViewById(R.id.detail_trash);

        Intent i = getIntent();
        Event event = (Event) i.getSerializableExtra(CommonKey.EVENT);


        if (event != null) {
            System.out.print("Debug-info" + event.toString());
            txtTitle.setText(event.getTitle());

            txtMore.setText(event.getKeywordString());
            txtStartTime.setText(Common.dateFormat.
                    getDateTimeStringFromDate(event.getStartTime()));
            txtEndTime.setText(Common.dateFormat.
                    getDateTimeStringFromDate(event.getEndTime()));
            txtLocation.setText(event.getLocation());

            txtDescription.setText(event.getDescription());
            txtLocation.setText(event.getHomepageLink());

            if (event.getType() == CommonKey.TYPE_PRIVATE) {
                imgLike.setVisibility(View.INVISIBLE);
            }

            // check if liked
            // code here

            if (event.isAdded()) {
                imgAdd.setVisibility(View.INVISIBLE);
            } else {
                imgTrash.setVisibility(View.GONE);
            }

            // delete function
            // code here

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // share here
                }
            });
        } else {
            System.out.println("Debug-info" + " true");
        }




    }
}
