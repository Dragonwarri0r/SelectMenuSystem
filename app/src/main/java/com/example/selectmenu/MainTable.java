package com.example.selectmenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainTable extends AppCompatActivity {

    MyApplication app;
    Intent intent;

    ImageButton exitImgbtn;
    ImageButton showDishMenu;
    ImageButton showmymenu;
    ImageButton setUser;

    Boolean iflogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_table);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        app=(MyApplication)getApplication();
        app.setState(1);

        exitImgbtn=(ImageButton)findViewById(R.id.exit_imgbtn);
        showDishMenu=(ImageButton)findViewById(R.id.showdishmenu);
        showmymenu=(ImageButton)findViewById(R.id.showmymenu) ;
        setUser=(ImageButton)findViewById(R.id.setuser);

        btnlistener btnlis = new btnlistener();
        exitImgbtn.setOnClickListener(btnlis);
        showDishMenu.setOnClickListener(btnlis);
        showmymenu.setOnClickListener(btnlis);
        setUser.setOnClickListener(btnlis);


        //intent = new Intent(this,MusicPlayerService.class);
        //startService(intent);


    }



    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==0){
            if(resultCode==RESULT_OK){
                if(app.getLoginstate()){
                    exitImgbtn.setImageDrawable(getResources().getDrawable(R.drawable.logout));
                    exitImgbtn.setScaleType(ImageView.ScaleType.FIT_XY);
                    exitImgbtn.getBackground().setAlpha(0);
                }
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.conmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent=new Intent(MainTable.this,DishSetting.class);
                startActivity(intent);
                break;
            case R.id.about:
                Intent intenta = new Intent(MainTable.this,Help.class);
                Toast.makeText(MainTable.this,"about",Toast.LENGTH_SHORT).show();
                startActivity(intenta);
                break;
            case R.id.exit:
                AlertDialog.Builder exitdialog = new AlertDialog.Builder(MainTable.this);
                exitdialog.setTitle("note:").setMessage("Sure to exit?");
                exitdialog.setPositiveButton("sure",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                exitdialog.setNegativeButton("cancel",null);
                exitdialog.create();
                exitdialog.show();

            default: break;
        }
        return true;
    }

    @Override
    protected void onResume(){
        super.onResume();
        iflogin=app.getLoginstate();
        if(!iflogin){
            exitImgbtn.setImageDrawable(getResources().getDrawable(R.drawable.needlogin));
            exitImgbtn.getBackground().setAlpha(0);
        }else{
            exitImgbtn.setImageDrawable(getResources().getDrawable(R.drawable.logout));
            exitImgbtn.getBackground().setAlpha(0);
        }
    }

    class btnlistener implements View.OnClickListener{
        Intent intent;
        @Override
        public void onClick(View v){

            iflogin=app.getLoginstate();

            switch (v.getId()) {
                case R.id.exit_imgbtn:
                    if (!iflogin) {
                        intent = new Intent(MainTable.this, LoginActivity.class);
                        startActivityForResult(intent, 0);
                    } else {
                        exitImgbtn.setImageDrawable(getResources().getDrawable(R.drawable.needlogin));
                        exitImgbtn.getBackground().setAlpha(0);
                        app.setLoginstate(false);
                    }
                    break;
                case R.id.showdishmenu:
                    if (!iflogin) {
                        Toast.makeText(MainTable.this,"未登录，请先登录",Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainTable.this, LoginActivity.class);
                        startActivityForResult(intent, 0);
                    } else {
                        Intent showDishIntent = new Intent(MainTable.this,DishListActivity.class);
                        startActivity(showDishIntent);
                    }
                    break;
                case R.id.showmymenu:
                    if (!iflogin) {
                        Toast.makeText(MainTable.this,"未登录，请先登录",Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainTable.this, LoginActivity.class);
                        startActivityForResult(intent, 0);
                    } else {
                        Intent showDishIntent = new Intent(MainTable.this,OrderedActivity.class);
                        startActivity(showDishIntent);
                    }
                    break;
                case R.id.setuser:
                    if (!iflogin) {
                        Toast.makeText(MainTable.this,"未登录，请先登录",Toast.LENGTH_SHORT).show();
                        intent = new Intent(MainTable.this, LoginActivity.class);
                        startActivityForResult(intent, 0);
                    } else {
                        Intent setUserIntent = new Intent(MainTable.this,SetUserActivity.class);
                        startActivity(setUserIntent);
                    }



                default:
                    break;
            }
        }
    }




}
