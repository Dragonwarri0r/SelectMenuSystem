package com.example.selectmenu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetUserActivity extends AppCompatActivity {

    private Button confirm;
    private Button cancel;
    private EditText uedit;
    private EditText keyeditOld;
    private EditText keyeditNew;
    private EditText keyeditNewConfirm;
    private String username,uokey,unkey,ucnkey;
    SharedPreferences usermessage;
    private final String ufile = "ufile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user);

        uedit = (EditText)findViewById(R.id.wsetname);
        keyeditOld=(EditText)findViewById(R.id.osetpassword);
        keyeditNew=(EditText)findViewById(R.id.nsetpassword);
        keyeditNewConfirm=(EditText)findViewById(R.id.nsetpassword2);


        confirm = (Button)findViewById(R.id.setconfirm);
        cancel = (Button)findViewById(R.id.setcancel);

        btnlistener btnlis = new btnlistener();
        cancel.setOnClickListener(btnlis);
        confirm.setOnClickListener(btnlis);



    }

    class btnlistener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.setconfirm:
                    username = uedit.getText().toString().trim();
                    uokey = keyeditOld.getText().toString().trim();
                    unkey = keyeditNew.getText().toString().trim();
                    ucnkey = keyeditNewConfirm.getText().toString().trim();

                    if(username.isEmpty()||uokey.isEmpty()){
                        Toast.makeText(SetUserActivity.this,"NULL!!!",Toast.LENGTH_SHORT).show();
                        uedit.setFocusable(true);
                        uedit.requestFocus();
                        return;
                    }
                    else{

                        usermessage=LoadUserPreference();
                        String uname = usermessage.getString("uname","");
                        String uukey = usermessage.getString("ukey","");
                        if(!username.equalsIgnoreCase(uname)||!uokey.equalsIgnoreCase(uukey)){
                            Toast.makeText(SetUserActivity.this,"username or password wrong",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(uukey.equals(ucnkey)){
                            int mode = Activity.MODE_PRIVATE;//使用sharespreferences存储信息
                            SharedPreferences uSetting = getSharedPreferences(ufile,mode);
                            SharedPreferences.Editor editor = uSetting.edit();
                            editor.putString("uname",uname);
                            editor.putString("ukey",unkey);
                            editor.commit();
                            Toast.makeText(SetUserActivity.this,"Done!",Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        }
                    }
                case R.id.setcancel:
                    finish();
                    break;
                default:
                    break;

            }
        }
    }

    private SharedPreferences LoadUserPreference(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences usetting = getSharedPreferences(ufile,mode);
        return usetting;
    }

}
