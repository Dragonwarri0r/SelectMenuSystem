package com.example.selectmenu.data.model;

import android.widget.Toast;

import com.example.selectmenu.R;

import java.util.ArrayList;

public class Dishes {
    private ArrayList<Object> dishitem;
    private static ArrayList<ArrayList<Object>>
    DishesArray = new ArrayList<ArrayList<Object>>();

    public ArrayList<ArrayList<Object>> getReCaiDishes(){
        DishesArray.clear();
        dishitem=new ArrayList<Object>();
        dishitem.add("1001");
        dishitem.add("宫爆鸡丁");
        dishitem.add(20.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("1002");
        dishitem.add("椒盐玉米");
        dishitem.add(24.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("1003");
        dishitem.add("清蒸武昌鱼");
        dishitem.add(48.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("1004");
        dishitem.add("鱼香肉丝");
        dishitem.add(27.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);

        return DishesArray;
    }

    public ArrayList<ArrayList<Object>> getLiangCaiDishes(){
        DishesArray.clear();
        dishitem=new ArrayList<Object>();
        dishitem.add("2001");
        dishitem.add("凉拌敛财");
        dishitem.add(88.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("2002");
        dishitem.add("白菜肉沫");
        dishitem.add(166.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("2003");
        dishitem.add("群英荟萃");
        dishitem.add(188.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("2004");
        dishitem.add("宫廷玉液酒");
        dishitem.add(180.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);

        return DishesArray;
    }

    public ArrayList<ArrayList<Object>> getZhuShiDishes(){
        DishesArray.clear();
        dishitem=new ArrayList<Object>();
        dishitem.add("3001");
        dishitem.add("馒头");
        dishitem.add(88.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("3002");
        dishitem.add("花卷");
        dishitem.add(166.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("3003");
        dishitem.add("玉米");
        dishitem.add(188.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("3004");
        dishitem.add("面条");
        dishitem.add(180.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);

        return DishesArray;
    }

    public ArrayList<ArrayList<Object>> getTangDishes(){
        DishesArray.clear();
        dishitem=new ArrayList<Object>();
        dishitem.add("2001");
        dishitem.add("菠菜");
        dishitem.add(88.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("2002");
        dishitem.add("丸子");
        dishitem.add(166.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("2003");
        dishitem.add("馄炖");
        dishitem.add(188.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("2004");
        dishitem.add("馄饨");
        dishitem.add(180.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);

        return DishesArray;
    }

    public ArrayList<ArrayList<Object>> getYinLiaoDishes(){
        DishesArray.clear();
        dishitem=new ArrayList<Object>();
        dishitem.add("4001");
        dishitem.add("可乐");
        dishitem.add(88.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("4002");
        dishitem.add("雪碧");
        dishitem.add(166.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("4003");
        dishitem.add("果汁");
        dishitem.add(188.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);
        dishitem=new ArrayList<Object>();
        dishitem.add("4004");
        dishitem.add("气泡水");
        dishitem.add(180.0);
        dishitem.add(R.mipmap.ic_launcher);
        DishesArray.add(dishitem);

        return DishesArray;
    }



}
