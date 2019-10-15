package com.example.selectmenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
/*
        Button switch1 = (Button)findViewById(R.id.change);
        Intent intent2 = new Intent(Main2Activity.this,MainActivity.class);
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Main2Activity.this,MainActivity.class);
                Toast.makeText(Main2Activity.this,"切换",Toast.LENGTH_SHORT).show();
                startActivity(intent2);
            }
        });*/
        Button submit = (Button)findViewById(R.id.btnToLogin);
        submit.setOnClickListener(new userToSubmit(this));
        //显式独立类写监听事件

    }

    public class userToSubmit implements View.OnClickListener{
        private Context context;
        public userToSubmit(Context ct){
            this.context=ct;
        }
        @Override
        public void onClick(View v){
            Toast.makeText(context,"不存在，尝试注册！",Toast.LENGTH_SHORT).show();
        }

    }


}

