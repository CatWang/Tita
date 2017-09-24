package com.example.catwong.tita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.util.Common;
import com.example.catwong.tita.util.HttpHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by CatWong on 9/23/17.
 */

public class EventDetailActivity extends AppCompatActivity {

    private TextView txtTitle, txtMore, txtStartTime, txtEndTime;
    private TextView txtLocation, txtLink, txtDescription;

    private ImageView imgAdd, imgLike, imgShare, imgEmail, imgFb;
    private ImageView imgTw, imgTrash;

    public boolean liked = false;

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == HttpHelper.MSG_SUCCESS) {
                JsonObject jsonObject = (JsonObject) msg.obj;

                JsonArray array = jsonObject.get("event").getAsJsonArray();
                JsonObject event = array.get(0).getAsJsonObject();

                txtTitle.setText(event.get("title").getAsString());
                txtLocation.setText(event.get("location").getAsString());
                txtStartTime.setText(event.get("start_time").getAsString());
                txtEndTime.setText(event.get("end_time").getAsString());
                txtLink.setText(event.get("homepage_link").getAsString());
                txtDescription.setText(event.get("description").getAsString());



                if (event.get("type").getAsString().equals("private")) {
                    imgLike.setVisibility(View.GONE);
                } else {
                    imgLike.setVisibility(View.VISIBLE);

                    imgLike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!liked) {
                                imgLike.setImageResource(R.drawable.ic_heart_red);
                                liked = true;
                            } else {
                                liked = false;
                                imgLike.setImageResource(R.drawable.ic_heart_pink);
                            }
                        }
                    });
                }

            }

            return true;
        }
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragement_event_detail);

        HttpHelper.get(handler, "event/" + getIntent().getStringExtra("id"), true);

        boolean added = getIntent().getBooleanExtra("added", false);


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

        if (added) {
            imgAdd.setVisibility(View.GONE);
            imgTrash.setVisibility(View.VISIBLE);
        } else {
            imgAdd.setVisibility(View.VISIBLE);
            imgTrash.setVisibility(View.GONE);
        }
    }
}
