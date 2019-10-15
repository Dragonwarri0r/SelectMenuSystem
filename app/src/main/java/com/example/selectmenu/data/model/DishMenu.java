package com.example.selectmenu.data.model;

public class DishMenu {

    public String uid;
    public String dishid;
    public String dishname;
    public int dishnum;
    public double dishprice;
    public int dishmenuid;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDishid() {
        return dishid;
    }

    public void setDishid(String dishid) {
        this.dishid = dishid;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public int getDishnum() {
        return dishnum;
    }

    public void setDishnum(int dishnum) {
        this.dishnum = dishnum;
    }

    public double getDishprice() {
        return dishprice;
    }

    public void setDishprice(double dishprice) {
        this.dishprice = dishprice;
    }

    public int getDishmenuid() {
        return dishmenuid;
    }

    public void setDishmenuid(int dishmenuid) {
        this.dishmenuid = dishmenuid;
    }
}
