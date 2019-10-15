package com.example.selectmenu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    private TextView tv_note;
    private EditText et_userName;
    private EditText et_password;
    private EditText et_confirm;
    private EditText et_trueName;
    private EditText et_age;
    private Button btn_register;
    private Button btn_back;
    private RadioButton rad_sex1,rad_sex2;
    private CheckBox intere1,intere2,intere3,intere4;
    private Spinner m_spiSpinnerAct = null;

    private  String userName="null";
    private  String password="null";
    private  String confirm;
    private String trueName;
    private  String age;
    private  String sex;
    private  String phone = "";
    private  String[] interesting = new String[4];
    private  String hobby = "";
    private  String country;
    private  String address = "";
    private Resources res;

    private final String ufile = "ufile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        res = getResources();
        tv_note=(TextView)findViewById(R.id.reg_note);
        et_userName=(EditText)findViewById(R.id.reg_username);
        et_password=(EditText)findViewById(R.id.reg_password);
        et_confirm=(EditText)findViewById(R.id.reg_confirm);
        et_trueName=(EditText)findViewById(R.id.reg_trueName);
        et_age=(EditText)findViewById(R.id.reg_age);
        btn_back=(Button)findViewById(R.id.reg_btn_login);
        //m_spiSpinnerAct=(Spinner)findViewById(R.id.reg_cou)
        btn_register=(Button)findViewById(R.id.reg_btn_register);
        rad_sex1=(RadioButton)findViewById(R.id.radbtnRadioButton1);
        rad_sex2=(RadioButton)findViewById(R.id.radbtnRadioButton2);

        intere1=(CheckBox)findViewById(R.id.gym);
        intere2=(CheckBox)findViewById(R.id.music);
        intere3=(CheckBox)findViewById(R.id.tour);
        intere4=(CheckBox)findViewById(R.id.read);

        btn_back.setOnClickListener(new RegisterListener());
        btn_register.setOnClickListener(new RegisterListener());

    }

    class RegisterListener implements View.OnClickListener{
        @Override
        public void onClick(View v ){
            switch(v.getId()){
                case R.id.reg_btn_register:
                    userName=et_userName.getText().toString();
                    password=et_password.getText().toString();
                    confirm=et_confirm.getText().toString();
                    trueName=et_trueName.getText().toString();
                    age=et_age.getText().toString();
                    if(rad_sex1.isChecked()) sex="男";
                    if(rad_sex2.isChecked()) sex="女";

                    if(intere1.isChecked()) interesting[0]="体育";
                    if(intere2.isChecked()) interesting[1]="音乐";
                    if(intere3.isChecked()) interesting[2]="旅游";
                    if(intere4.isChecked()) interesting[3]="阅读";
                    for(int i=0;i<interesting.length;i++){
                        hobby+=" "+interesting[i];
                    }

                    /*判空操作未做*/

                    if(!confirm.equals(password)){
                        tv_note.setText("password not equal");
                        et_password.setText(" ");
                        et_confirm.setText(" ");
                        et_password.setFocusable(true);
                        et_password.requestFocus();
                        return;
                    }
                    Intent intent=new Intent(SignIn.this,LoginActivity.class);
                    Bundle ubundle =new Bundle();
                    userName=et_userName.getText().toString();
                    password=et_password.getText().toString();

                    int mode = Activity.MODE_PRIVATE;//使用sharespreferences存储信息
                    SharedPreferences uSetting = getSharedPreferences(ufile,mode);
                    SharedPreferences.Editor editor = uSetting.edit();
                    editor.putString("uname",userName);
                    editor.putString("ukey",password);
                    editor.commit();

                    ubundle.putString("uname",userName);
                    ubundle.putString("ukey",password);
                    intent.putExtras(ubundle);
                    setResult(RESULT_OK,intent);
                    finish();
                case R.id.reg_btn_login:
                    setResult(RESULT_CANCELED);
                    finish();
                    default: break;
            }
        }
    }

}
