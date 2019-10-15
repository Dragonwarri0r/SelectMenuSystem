package com.example.selectmenu;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Help extends AppCompatActivity {

    EditText txtFlag;
    Button btnflagSaveButton;
    Button btnflagCancelButton;
    Button aboutexitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_help);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        aboutexitbtn=(Button)findViewById(R.id.aboutexit);
        aboutexitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebView webView=(WebView)findViewById(R.id.webView1);
        StringBuilder sb= new StringBuilder();
        sb.append("<div>《点餐系统》使用指南</div>");
        sb.append("<ul>");
        sb.append("<li>音乐开关，通过选择顶部菜单的设置来设置</li>");
        sb.append("<li>选择点餐可以点餐</li>");
        sb.append("<li>选择菜单可以看到自己的菜单</li>");
        sb.append("<li>点击个人中心，可以修改用户名密码配送地址</li>");
        sb.append("<li>选择退出登录可以随时退出张好的登录状态</li>");
        sb.append("<li>顶部菜单就是点出来我啦～</li>");
        sb.append("<ul>");
        webView.loadDataWithBaseURL(null,sb.toString(),"text/html","utf-8",null);


    }

}
