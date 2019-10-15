package com.example.selectmenu;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selectmenu.data.model.Dish;
import com.example.selectmenu.data.model.DishMenu;
import com.example.selectmenu.data.model.Dishes;
import com.example.selectmenu.dummy.DummyContent;
import com.example.selectmenu.dummy.DummyContent.DummyItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFragment extends Fragment {

    // TODO: Customize parameter argument names

    public static final String TAG = "MyFragment";

    // TODO: Customize parameters
    private static ListView dishlist;
    private int itemID;
    private Dish dish = new Dish();
    private Dishes dishes = new Dishes();

    private ArrayList<ArrayList<Object>> DishItem = new ArrayList<>();
    private SimpleAdapter adapter;
    static List<Map<String,Object>> mfoodinfo = new ArrayList<>();

    //
    MyApplication mApp;
    private List<Map<String,Object>> dishlistmenu = new ArrayList<>();
    private SimpleAdapter orderadapter;
    private String uid;
    DishMenuDBManager dhelper;
    DishMenu[] uDishMenus;
    String dishName,dishnum,dishprice;
    DishMenu dMenu = new DishMenu();
    private Button btncancel;
    public ListView mlistview;
    public TextView mtvTotalPrice;
    //
    private float adishprice;
    private String adishid;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listitemright, container,false);
        dishlist = (ListView)view.findViewById(R.id.dishdetail);

        DishItem = dishes.getReCaiDishes();
        mfoodinfo = dish.getFoodData(DishItem);

        adapter = new SimpleAdapter(getActivity(),mfoodinfo,R.layout.listview_item,
                                    new String[ ] {"dishid" , "image", "title" ,"price"},
                                    new int[] {R.id.dishid,R.id.img,R.id.title,R.id.price});

        adapter.notifyDataSetChanged();
        dishlist.setAdapter(adapter);

        itemID=getArguments().getInt("MyFragment");


        switch (itemID){
            case 0:
                DishItem = dishes.getReCaiDishes();
                break;
            case 1:
                DishItem = dishes.getLiangCaiDishes();
                break;
            case 2:
                DishItem = dishes.getZhuShiDishes();
                break;
            case 3:
                DishItem = dishes.getTangDishes();
                break;
            case 4:
                DishItem = dishes.getYinLiaoDishes();
                break;
                default:
                    break;
        }
        
        mfoodinfo = dish.getFoodData(DishItem);
        adapter = new SimpleAdapter(getActivity(),mfoodinfo,
                R.layout.listview_item,
                new String[] {"dishid", "image","title","price"},
                new int[] {R.id.dishid,R.id.img,R.id.title,R.id.price});
        
        adapter.notifyDataSetChanged();
        dishlist.setAdapter(adapter);

        mApp=(MyApplication)getActivity().getApplication();
        uid = mApp.getUid();

        dhelper = new DishMenuDBManager(getActivity());
        dhelper.open();
        uDishMenus = dhelper.querdishByUser(uid);
        initData(dishlist);



        return view;
    }


    private void initData(final ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String dmenu = listView.getItemAtPosition(i)+"";

                try {
                    JSONObject dishdata = new JSONObject(dmenu);
                    dishName = dishdata.getString("title");
                    adishprice = (float) dishdata.getDouble("price");
                    adishid = dishdata.getString("dishid");
                }catch (Exception e){
                    e.printStackTrace();
                }
                final OrderOneDialog orderDlg = new OrderOneDialog(getActivity(),"0");
                orderDlg.setTitle(dishName);
                orderDlg.show();

                orderDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(orderDlg.mBtnClicked == OrderOneDialog.ButtonID.BUTTON_OK){
                            dMenu.uid = uid;

                            uDishMenus = dhelper.querdishByUser(uid);
                            //dMenu.dishid = dhelper.querydishIDbyName(uid,dishName);
                            dMenu.dishname = dishName;
                            dMenu.dishnum = orderDlg.mNum;
                            dMenu.dishprice = adishprice;
                            dMenu.dishid = adishid;

                            DishMenu dd = dhelper.queryonedish(uid,dMenu.dishid);
                            if(dd != null){
                                if(orderDlg.mNum == 0){
                                    dhelper.deleteonedish(dMenu,uid);
                                }else {
                                    dhelper.insert(dMenu);
                                }
                            }else{
                                dhelper.insert(dMenu);
                            }
                        }
                    }
                });
            }
        });
    }




}
