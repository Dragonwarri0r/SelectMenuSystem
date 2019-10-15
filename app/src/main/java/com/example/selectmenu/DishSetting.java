package com.example.selectmenu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DishSetting extends Activity {
    ImageButton musicbtn = null;
    Button setexit;
    private int state;
    ActivityReceiver activityReceiver;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        app=(MyApplication)getApplication();
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.content_dish_setting);
        musicbtn=(ImageButton) findViewById(R.id.music);

        state=app.getState();
        if(state==1){
            musicbtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
        }
        if(state==2){
            musicbtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
        }

        activityReceiver = new ActivityReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.selectmenu.MusicPlayer.update");
        registerReceiver(activityReceiver,filter);

        setexit=(Button)findViewById(R.id.settingexit);
        setexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageButton.OnClickListener buttonListener = new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v){
                state=app.getState();
                Intent sendIntent = new Intent("com.example.selectmenu.MusicPlayer.control");
                if(state==1){
                    app.setState(2);
                    sendBroadcast(sendIntent);
                }
                if(state==2){
                    app.setState(1);
                    sendBroadcast(sendIntent);
                }
            }
        };
        musicbtn.setOnClickListener(buttonListener);


    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(activityReceiver);
    }

    public class ActivityReceiver extends BroadcastReceiver {
        @Override
        public void  onReceive(Context context, Intent intent){
            int mupdate = app.getState();
            switch (mupdate){
                case 1:
                    musicbtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
                    break;
                case 2:
                    musicbtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
                    break;
            }
        }
    }

}
