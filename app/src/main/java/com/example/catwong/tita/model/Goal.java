package com.example.catwong.tita.model;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class Goal {


    private String mDescription;

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    private String mLocation;
    private String mRepeatDay;
    private String mStarttime;

    public String getStarttime() {
        return mStarttime;
    }

    public void setStarttime(String mStarttime) {
        this.mStarttime = mStarttime;
    }

    public String getEndtime() {
        return mEndtime;
    }

    public void setEndtime(String mEndtime) {
        this.mEndtime = mEndtime;
    }

    private String mEndtime;
    private int mUserID;

    public Goal(){

    }

    public Goal(String title, String location, String description, String repeatDay, String starttime, String endtime, int userID) {
        mTitle = title;
        mLocation = location;
        mDescription = description;
        mRepeatDay = repeatDay;
        mStarttime = starttime;
        mEndtime = endtime;
        mUserID = userID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    private String mTitle;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getRepeatDay() {
        return mRepeatDay;
    }

    public void setRepeatDay(String mRepeatDay) {
        this.mRepeatDay = mRepeatDay;
    }


    public int getUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }
}
