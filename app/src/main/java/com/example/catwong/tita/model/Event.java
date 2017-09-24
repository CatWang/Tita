package com.example.catwong.tita.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class Event implements Serializable{
    private long eventID;
    private String mtitle;
    private Date mStartTime;
    private Date mEndTime;
    private String mLocation;
    private String mGps;
    private String mDescription;
    private String mImageUrl;
    private String mDocLink;
    private String mHomepageLink;
    private int mType;
    private ArrayList<String> keywords;
    private boolean added;


    public Event() {

    }

    public Event(long id, String title, Date startTime,
                 Date endTime, String location,
                 int type) {
        eventID = id;
        mtitle = title;
        mStartTime = startTime;
        mEndTime = endTime;
        mLocation = location;
        mType = type;
        keywords = new ArrayList<>();
        mGps = "";
        mDescription = "";
        mImageUrl = "";
        mDocLink = "";
        mHomepageLink = "";
        added = false;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public void setKeywords(ArrayList<String> keywords) {
        for (String keyword: keywords) {
            this.keywords.add(keyword);
        }
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public String getKeywordString() {
        String res = "";
        for (String word : keywords) {
            res += word;
            res += ", ";
        }

        return res.substring(0, res.length() - 2);
    }

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }


    public String getTitle() {
        return mtitle;
    }

    public void setTitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Date mStartTime) {
        this.mStartTime = mStartTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Date mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getGps() {
        return mGps;
    }

    public void setGps(String mGps) {
        this.mGps = mGps;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getDocLink() {
        return mDocLink;
    }

    public void setDocLink(String mDocLink) {
        this.mDocLink = mDocLink;
    }

    public String getHomepageLink() {
        return mHomepageLink;
    }

    public void setHomepageLink(String mHomepageLink) {
        this.mHomepageLink = mHomepageLink;
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public String toString() {
        return getTitle() + ", " + getStartTime() + ", " + getLocation();
    }
}
