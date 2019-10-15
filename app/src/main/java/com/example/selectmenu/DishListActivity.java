package com.example.selectmenu;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.example.selectmenu.data.model.Dishes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.FragmentTransaction;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DishListActivity extends Activity implements AdapterView.OnItemClickListener {

    private String[] strs = {"热菜","凉菜","主食","汤","饮料"};
    private ListView listView;
    private MyAdapter adapter;
    private MyFragment myFragment;

    public static int mPosition;
    public static Dishes dishes = new Dishes();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_dish_list);

        setTitle("菜单列表");
        ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        initView();


    }

    private void initView(){
        listView = (ListView)findViewById(R.id.categorylistleft);

        adapter = new MyAdapter(this, strs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        myFragment = new MyFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myFragment);

        mPosition=0;

        Bundle bundle = new Bundle();
        bundle.putInt("MyFragment",mPosition);
        myFragment.setArguments(bundle);
        fragmentTransaction.commit();

    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position ,long id){
        mPosition = position;
        adapter.notifyDataSetChanged();

        MyFragment dishFragment = new MyFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,dishFragment);

        Bundle bundle = new Bundle();
        bundle.putInt("MyFragment",mPosition);
        dishFragment.setArguments(bundle);//划重点！！！不要写错
        fragmentTransaction.commit();
    }

}
