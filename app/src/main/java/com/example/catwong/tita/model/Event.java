package com.example.catwong.tita.model;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class Event {
    private String mtitle;
    private String mStartTime;
    private String mEndTime;
    private String mLocation;
    private String mGps;
    private String mDescription;
    private String mImageUrl;
    private String mDocLink;
    private String mHomepageLink;
    private String mType;

    public Event() {

    }

    public Event(String title, String startTime, String endTime, String location, String gps, String description, String imageURL, String docLink, String homepageLink, String type) {
        mtitle = title;
        mStartTime = startTime;
        mEndTime = endTime;
        mLocation = location;
        mGps = gps;
        mDescription = description;
        mImageUrl = imageURL;
        mDocLink = docLink;
        mHomepageLink = homepageLink;
        mType = type;

    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmGps() {
        return mGps;
    }

    public void setmGps(String mGps) {
        this.mGps = mGps;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmDocLink() {
        return mDocLink;
    }

    public void setmDocLink(String mDocLink) {
        this.mDocLink = mDocLink;
    }

    public String getmHomepageLink() {
        return mHomepageLink;
    }

    public void setmHomepageLink(String mHomepageLink) {
        this.mHomepageLink = mHomepageLink;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
}
