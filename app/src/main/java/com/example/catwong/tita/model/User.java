package com.example.catwong.tita.model;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class User {

    private String mEmail;
    private String mPassword;
    private String mUserName;

    public User(){

    }

    public User(String email, String password, String userName) {
        mEmail = email;
        mPassword = password;
        mUserName = userName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

}
