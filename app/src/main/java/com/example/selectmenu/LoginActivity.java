package com.example.selectmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private Button regbtn;
    private EditText uedit;
    private EditText keyedit;
    private String username,ukey;
    private MyApplication app;
    private CheckBox remuser;
    SharedPreferences usermessage;

    private final String ufile = "ufile";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app=(MyApplication)getApplication();

        uedit = (EditText)findViewById(R.id.editText);
        keyedit=(EditText)findViewById(R.id.editText2);

        remuser=(CheckBox)findViewById(R.id.rember_me);

        usermessage=LoadUserPreference();
        username=((SharedPreferences) usermessage).getString("uname","");
        ukey=((SharedPreferences) usermessage).getString("ukey","");
        int ifrem = ((SharedPreferences) usermessage).getInt("ifrem",0);

        if(ifrem == 1 ){
            uedit.setText(username);
            keyedit.setText(ukey);
            Toast.makeText(LoginActivity.this,"恢复用户状态成功！",Toast.LENGTH_SHORT).show();
        }

        login=(Button) findViewById(R.id.submit);
        regbtn=(Button)findViewById(R.id.signIn);

        btnlistener btnlis = new btnlistener();
        login.setOnClickListener(btnlis);
        regbtn.setOnClickListener(btnlis);

        Button buttonChangeToRelative = (Button)findViewById(R.id.changeToRlative);
        buttonChangeToRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this,SlectMenuRelative.class);//SlectMenuRelative 问题
                Toast.makeText(LoginActivity.this,"启动相对布局界面",Toast.LENGTH_SHORT).show();
                startActivity(intent2);
            }
        });

        Button switch1 = (Button)findViewById(R.id.changel);
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,Main2Activity.class);
                Toast.makeText(LoginActivity.this,"切换表格布局",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });



        Button switch3 = (Button)findViewById(R.id.maintable);
        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(LoginActivity.this,MainTable.class);
                Toast.makeText(LoginActivity.this,"maintable",Toast.LENGTH_SHORT).show();
                startActivity(intent3);
            }
        });

        /*Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usermessage = LoadUserPreference();
                String uname = usermessage.getString("uname","");
                Intent intent3 = new Intent(MainActivity.this,MainTable.class);
                Toast.makeText(MainActivity.this,"maintable",Toast.LENGTH_SHORT).show();
                startActivity(intent3);
            }
        });*/






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.conmenu,menu);
        MenuItem sItem = menu.findItem(R.id.action_search);

        sItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        SearchView searchView = (SearchView)sItem.getActionView();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public  boolean onMenuOpened(int featureID, Menu menu){
        if(featureID == Window.FEATURE_ACTION_BAR && menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu,true);
                }catch (Exception e){

                }

            }
        }
        return super.onMenuOpened(featureID,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.about:
                Intent intent = new Intent(LoginActivity.this,Help.class);
                Toast.makeText(LoginActivity.this,"about",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

            case R.id.exit:
                AlertDialog.Builder exitdialog = new AlertDialog.Builder(LoginActivity.this);
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
                break;

            default: break;
        }
        return super.onOptionsItemSelected(item);


    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode ==  1){
            if(resultCode == RESULT_OK){
                Bundle udata = data.getExtras();
                username = udata.getString("uname");
                ukey = udata.getString("ukey");
                uedit.setText(username);
                keyedit.setText(ukey);
            }
        }
    }

    private SharedPreferences LoadUserPreference(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences usetting = getSharedPreferences(ufile,mode);
        return usetting;
    }

    private void WriteUserPreference(String name,String key,int ifrem){
        int mode = Activity.MODE_PRIVATE;

        SharedPreferences uSetting = getSharedPreferences(ufile,mode);

        SharedPreferences.Editor editor = uSetting.edit();
        editor.putString("uname",name);
        editor.putString("ukey",key);
        editor.putInt("ifrem",ifrem);
        editor.commit();
    }

    class btnlistener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.submit:
                    username = uedit.getText().toString().trim();
                    ukey = keyedit.getText().toString().trim();
                    if(username.isEmpty()||ukey.isEmpty()){
                        Toast.makeText(LoginActivity.this,"NULL!!!",Toast.LENGTH_SHORT).show();
                        uedit.setFocusable(true);
                        uedit.requestFocus();
                        return;
                    }
                    else{
                        usermessage=LoadUserPreference();
                        String uname = usermessage.getString("uname","");
                        String uukey = usermessage.getString("ukey","");

                        if(!username.equalsIgnoreCase(uname)||!ukey.equalsIgnoreCase(uukey)){
                            Toast.makeText(LoginActivity.this,"username or password wrong",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(remuser.isChecked()){
                            WriteUserPreference(username,ukey,1);
                        }else{
                            WriteUserPreference(username,ukey,0);
                        }
                        app.setLoginstate(true);

                    }
                    Intent intentt = new Intent(LoginActivity.this,MainTable.class);
                    setResult(RESULT_OK,intentt);
                    finish();
                    break;
                case R.id.signIn:
                    app.setLoginstate(false);
                    Intent intent2 = new Intent(LoginActivity.this,SignIn.class);
                    Toast.makeText(LoginActivity.this,"注册",Toast.LENGTH_SHORT).show();
                    startActivityForResult(intent2,1);
                    break;
                default:
                    break;

            }
        }
    }


}

