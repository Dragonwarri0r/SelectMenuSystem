package com.example.selectmenu;

import android.app.Application;

public class MyApplication extends Application {
    public int state;
    public Boolean loginstatus=false;
    public String uid="TEST";

    public void setState(int sstate){
        this.state=sstate;
    }

    public int getState(){
        return this.state;
    }

    public Boolean getLoginstate() {
        return this.loginstatus;
    }

    public void setLoginstate(Boolean loginstatus) {
        this.loginstatus = loginstatus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

