package com.example.catwong.tita.activity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.RadioButton;

import com.example.catwong.tita.R;
import com.example.catwong.tita.adapter.MyPagerAdapter;
import com.example.catwong.tita.util.CustomViewPager;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,
        CustomViewPager.OnPageChangeListener{

    private CustomViewPager viewPager;

    private RadioButton radio_calendar;
    private RadioButton radio_finding;
    private RadioButton radio_goals;
    private RadioButton radio_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // not title bar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_home);

        initTabs();
    }

    private void initTabs() {
        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        // radio group
        radio_calendar = (RadioButton) findViewById(R.id.radio_calendar);
        radio_finding = (RadioButton) findViewById(R.id.radio_finding);
        radio_goals = (RadioButton) findViewById(R.id.radio_goals);
        radio_user = (RadioButton) findViewById(R.id.radio_user);

        radio_calendar.setOnClickListener(this);
        radio_finding.setOnClickListener(this);
        radio_goals.setOnClickListener(this);
        radio_user.setOnClickListener(this);

//        // fragment

        FragmentPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), this);
        try{
            viewPager.setAdapter(pagerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(this);
    }

    // handle button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_calendar:
                viewPager.setCurrentItem(0);

                break;
            case R.id.radio_finding:
                viewPager.setCurrentItem(1);

                break;
            case R.id.radio_goals:
                viewPager.setCurrentItem(2);
                break;
            case R.id.radio_user:
                viewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        switch(position){
            case 0:
                radio_calendar.setChecked(true);

                break;
            case 1:
                radio_finding.setChecked(true);

                break;
            case 2:
                radio_goals.setChecked(true);
                break;
            case 3:
                radio_user.setChecked(true);
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }


    @Override
    public void onPageScrollStateChanged(int state) { }



}

