package com.example.catwong.tita.model;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class Goal {


    private String mDescription;
    private String mRepeatDay;
    private int mDuration;
    private int mUserID;

    public Goal(){

    }

    public Goal(String title, String description, String repeatDay, int duration, int userID) {
        mTitle = title;
        mDescription = description;
        mRepeatDay = repeatDay;
        mDuration = duration;
        mUserID = userID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    private String mTitle;

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmRepeatDay() {
        return mRepeatDay;
    }

    public void setmRepeatDay(String mRepeatDay) {
        this.mRepeatDay = mRepeatDay;
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public int getmUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }
}
