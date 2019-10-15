package com.example.selectmenu.data.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dish {
    public String id;
    public String title;
    public Integer imageId;
    public Double price;

    public String getTitle(){
        return title;
    }

    public Double getPrice(){
        return price;
    }

    public void setDishItems(ArrayList<Object> dishes){

        this.id=(String) dishes.get(0);
        this.title=(String) dishes.get(1);
        this.price=(Double) dishes.get(2);
        this.imageId=(Integer) dishes.get(3);
    }

    public  ArrayList<Map<String,Object>> getFoodData(ArrayList<ArrayList<Object>> dishes){
        ArrayList<Map<String,Object>> fooddata = new ArrayList<Map<String, Object>>();
        ArrayList<Object> dishkind;

        for(int i = 0 ;i<dishes.size();i++){
            dishkind=dishes.get(i);
            setDishItems(dishkind);//设置dish属性的值

            Map<String,Object> map =new HashMap<String, Object>();
            map.put("dishid", this.id);
            map.put("image", this.imageId);
            map.put("title", this.title);
            map.put("price", this.price);
            fooddata.add(map);



        }

        return fooddata;
    }
}
