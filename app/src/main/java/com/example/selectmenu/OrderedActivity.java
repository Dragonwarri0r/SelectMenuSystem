package com.example.selectmenu;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.selectmenu.data.model.DishMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderedActivity extends AppCompatActivity {

    MyApplication mApp;
    private List<Map<String,Object>> dishlistmenu = new ArrayList<>();
    private SimpleAdapter orderadapter;
    private String uid;
    DishMenuDBManager dhelper;
    DishMenu[] uDishMenus;
    String dishName,dishnum,dishprice;
    DishMenu dMenu = new DishMenu();
    private Button btncancel;
    private Button btnclearall;
    public ListView mlistview;
    public TextView mtvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_ordered);

        mtvTotalPrice = (TextView)findViewById(R.id.ordertotalprice);
        mlistview = (ListView) findViewById(R.id.OrderedListview);
        btncancel = (Button)findViewById(R.id.submit_cancel);
        btnclearall = (Button)findViewById(R.id.clear_all);


        mApp=(MyApplication)getApplication();
        uid = mApp.getUid();

        dhelper = new DishMenuDBManager(this);
        dhelper.open();
        uDishMenus = dhelper.querdishByUser(uid);

        btnclearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dhelper.open();
                dhelper.deletedishByUid(uid);
                finish();
            }
        });

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if(msg.what == 110){
                    UpdateOrderList();
                }
            }
        };

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        this.mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
                final String dmenu = mlistview.getItemAtPosition(arg2)+"";
                try{
                    JSONObject dishdata = new JSONObject(dmenu);
                    dishName = dishdata.getString("title");
                    dishnum = dishdata.getString("num");
                    dishprice = dishdata.getString("price");
                }catch (JSONException e){
                    e.printStackTrace();
                }
                final OrderOneDialog orderDlg = new OrderOneDialog(OrderedActivity.this,dishnum);
                orderDlg.setTitle(dishName);
                orderDlg.show();

                orderDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(orderDlg.mBtnClicked == OrderOneDialog.ButtonID.BUTTON_OK){
                            dMenu.uid = uid;
                            uDishMenus = dhelper.querdishByUser(uid);
                            dMenu.dishid = dhelper.querydishIDbyName(uid,dishName);
                            dMenu.dishname = dishName;
                            dMenu.dishnum = orderDlg.mNum;
                            dMenu.dishprice = Double.valueOf(dishprice);
                            DishMenu dd = dhelper.queryonedish(uid,dMenu.dishid);
                            if(dd != null){
                                if(orderDlg.mNum == 0){
                                    dhelper.deleteonedish(dMenu,uid);
                                }else {
                                    dhelper.updateNummenu(dMenu.uid,dMenu.dishid,dMenu.dishnum);
                                }
                            }else{
                                dhelper.insert(dMenu);
                            }
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        Message msg = handler.obtainMessage();
                                        uDishMenus = dhelper.querdishByUser(uid);
                                        msg.what = 110;
                                        handler.sendMessage(msg);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
            }

        });



    }

    @Override
    protected void onResume(){
        super.onResume();
        UpdateOrderList();
    }

    private void UpdateOrderList(){
        Double totprice,usumprice;
        if(uDishMenus == null){
            dishlistmenu.clear();
            dishlistmenu.removeAll(dishlistmenu);
            mlistview.setAdapter(null);
            mtvTotalPrice.setText(Double.toString(0.0));
        }else{
            totprice = 0.0;
            dishlistmenu.clear();
            dishlistmenu.removeAll(dishlistmenu);
            mlistview.setAdapter(null);
            for(int i=0;i<uDishMenus.length;i++){
                Map<String,Object> bMap = new HashMap<String, Object>();
                bMap.put("title",uDishMenus[i].dishname);
                bMap.put("price",uDishMenus[i].dishprice);
                bMap.put("num",uDishMenus[i].dishnum);
                usumprice = uDishMenus[i].dishnum*uDishMenus[i].dishprice;
                totprice += usumprice;
                bMap.put("sumprice",usumprice);
                dishlistmenu.add(bMap);

            }
            orderadapter = new SimpleAdapter(OrderedActivity.this,dishlistmenu,R.layout.ordereditrm,
                    new String[]{"title","price","num","sumprice" },
                    new int[]{R.id.ordertitle,R.id.orderprice,R.id.ordernum,R.id.itemprice});
            mlistview.setAdapter(orderadapter);
            orderadapter.notifyDataSetChanged();

            mtvTotalPrice.setText(String.valueOf(totprice));


        }
    }



}
