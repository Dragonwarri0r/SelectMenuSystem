package com.example.selectmenu;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends BaseAdapter {
//自定义适配器，用于输出左面选择栏
    private Context context;
    private String[] strings;
    public static int mPosition;

    public MyAdapter(Context context,String[] strings) {
        this.context=context;
        this.strings=strings;
    }

    @Override
    public int getCount(){
        return strings.length;
    }

    @Override
    public Object getItem(int position){
        return strings[position];
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position ,View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(context).inflate(R.layout.leftlistviewitem,null);


        TextView tv = (TextView) convertView.findViewById(R.id.categorydishkind);
        mPosition = position;
        tv.setText(strings[position]);


        if(position == DishListActivity.mPosition){
            convertView.setBackgroundResource(R.drawable.ic_launcher_foreground);
        }else{
            convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
        }
        return convertView;
    }
}
