package com.example.selectmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.selectmenu.data.model.DishMenu;

public class DishMenuDBManager {
    public static final int DBVERSION = 1;
    public static final String DB_NAME = "DishMenu.db";
    public static final String TABLE = "usermenu";
    public static final String DISH_UID="uid";
    public static final String DISH_MENUID = "dishmenuid";
    public static final String DISH_ID = "dishid";
    public static final String DISH_NAME = "dishname";
    public static final String DISH_NUM = "dishnum";
    public static final String DISH_PRICE = "dishprice";

    private SQLiteDatabase db;
    private final Context context;
    private DishDBHelper dishhelper;

    public DishMenuDBManager(Context context){
        this.context=context;
    }
    //添加菜单

    public long insert (DishMenu dishMenu){
        ContentValues contentValues= new ContentValues();
        contentValues.put(DISH_UID,dishMenu.uid);
        contentValues.put(DISH_ID,dishMenu.dishid);
        contentValues.put(DISH_NAME,dishMenu.dishname);
        contentValues.put(DISH_NUM,dishMenu.dishnum);
        contentValues.put(DISH_PRICE,dishMenu.dishprice);
        return db.insert(TABLE,null,contentValues);
    }

    public void deleteonedish(DishMenu dishMenu,String uid){
        db.delete(TABLE,DISH_ID+"='"+dishMenu.dishid+"' and"
        +DISH_UID+"='"+uid+"'",null);
    }

    public void  deletedishByUid(String uid){
        String sql = "delete from "+TABLE;
        System.out.println("db="+sql);
        db.execSQL(sql);
    }

    public void open(){
        dishhelper = new DishDBHelper(context,DB_NAME,null,DBVERSION);
        try {
            db=dishhelper.getWritableDatabase();
        }catch (Exception e){
            db=dishhelper.getReadableDatabase();
        }
    }

    public void close(){
        if(db != null){
            db.close();
            db=null;
        }
    }

    public DishMenu queryonedish(String uid,String id){
        DishMenu adDishMenu = new DishMenu();
        Cursor cursor = db.query(TABLE,
                new String[]{DISH_UID,DISH_MENUID,DISH_ID,DISH_NAME,DISH_NUM,DISH_PRICE},
                DISH_UID+"='"+uid+"' and "+DISH_ID+"='"
        +id+"'",null,null,null,null,null);
        int count = cursor.getCount();
        if(count == 0 || !cursor.moveToFirst()){
            System.out.println("no data");
            return null;
        }else{
            adDishMenu.uid = uid;
            adDishMenu.dishid = cursor.getString(cursor.getColumnIndex(DISH_ID));
            adDishMenu.dishmenuid = cursor.getInt(cursor.getColumnIndex(DISH_MENUID));
            adDishMenu.dishname = cursor.getString(cursor.getColumnIndex(DISH_NAME));
            adDishMenu.dishnum = cursor.getInt(cursor.getColumnIndex(DISH_NUM));
            adDishMenu.dishprice = cursor.getDouble(cursor.getColumnIndex(DISH_PRICE));
            return adDishMenu;
        }
    }

    public String querydishIDbyName(String uid,String dishname){ //查一个菜单
        Cursor cursor = db.query(TABLE,
                new String[]{DISH_UID,DISH_MENUID,DISH_ID,
                DISH_NAME,DISH_NUM,DISH_PRICE},
                DISH_UID+"='"+uid+"'and "+DISH_NAME+"='"+dishname+"'",
                null,null,null,null,null);
        int count = cursor.getCount();
        if(count == 0){
            System.out.println("NO DATA");
            return null;
        }else{
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex(DISH_ID));
        }
    }

    public DishMenu[] querdishByUser(String uid){
        Cursor cursor = db.query(TABLE,
                new String[]{DISH_UID,DISH_MENUID,DISH_ID,
                        DISH_NAME,DISH_NUM,DISH_PRICE},
                DISH_UID+"='"+uid+"'",
                null,null,null,null,null);
        int count = cursor.getCount();
        if(count == 0|| !cursor.moveToFirst()){
            return null;
        }else{
            DishMenu[] dishlist = new DishMenu[count];
            for(int i = 0 ;i<count;i++){
                dishlist[i] = new DishMenu();
                dishlist[i].uid = uid;
                dishlist[i].dishid = cursor.getString(cursor.getColumnIndex(DISH_ID));
                dishlist[i].dishmenuid = cursor.getInt(cursor.getColumnIndex(DISH_MENUID));
                dishlist[i].dishname = cursor.getString(cursor.getColumnIndex(DISH_NAME));
                dishlist[i].dishnum = cursor.getInt(cursor.getColumnIndex(DISH_NUM));
                dishlist[i].dishprice = cursor.getDouble(cursor.getColumnIndex(DISH_PRICE));
                cursor.moveToNext();

            }
            return dishlist;
        }
    }

    public int updateNummenu(String suid,String sid,int sdishnum){
        ContentValues cv = new ContentValues();
        cv.put(DISH_NUM,sdishnum);
        return db.update(TABLE,cv,DISH_UID+"='"+suid+"' and "+DISH_ID+"='"+sid+"'",null);
        
    }

}
