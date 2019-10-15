package com.example.selectmenu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DishDBHelper extends SQLiteOpenHelper {
    public static final int DBVERSION = 1;
    public static final String DB_NAME = "DishMenu.db";
    public static final String TABLE = "usermenu";
    public static final String DISH_UID="uid";
    public static final String DISH_MENUID = "dishmenuid";
    public static final String DISH_ID = "dishid";
    public static final String DISH_NAME = "dishname";
    public static final String DISH_NUM = "dishnum";
    public static final String DISH_PRICE = "dishprice";

    public DishDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE +"(" + DISH_MENUID+" INTEGER PRIMARY KEY autoincrement,"
                + DISH_ID +" integer," + DISH_UID +" text,"+DISH_NAME+" text,"+DISH_NUM+" integer,"+DISH_PRICE
                + " float);";
        System.out.println("db="+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int i, int i1) {
        _db.execSQL( "DROP TABLE IF EXISTS " + TABLE);
        onCreate(_db);
    }
}
