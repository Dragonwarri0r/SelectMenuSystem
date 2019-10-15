package com.example.selectmenu;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicPlayerService extends Service {

    MediaPlayer mp;
    private MyApplication app;
    ServiceReceiver serviceReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate(){
        app=(MyApplication)getApplication();
        serviceReceiver = new ServiceReceiver();
        IntentFilter filter = new IntentFilter();//创建过滤器
        filter.addAction("com.example.selectmenu.MusicPlayer.control");
        registerReceiver(serviceReceiver,filter);


        super.onCreate();
    }

    @Override
    public void onStart(Intent intent,int startId){
        super.onStart(intent,startId);
        mp=MediaPlayer.create(this,R.raw.bg_music);
        app.setState(1);
        mp.start();
        mp.setLooping(true);

        Intent sendIntent = new Intent("com.example.selectmenu.MusicPlayer.update");
        sendBroadcast(sendIntent);
    }

    @Override
    public void onDestroy(){
        unregisterReceiver(serviceReceiver);
        mp.stop();
        super.onDestroy();
    }

    public class ServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context,Intent intent){
            Intent sendIntent;
            int action= app.getState();
            if(action==1){
                mp.start();
                mp.setLooping(true);
            }
            if(action==2){
                mp.pause();
                sendIntent=new Intent("com.example.selectmenu.MusicPlayer.update");
                sendBroadcast(sendIntent);
            }

        }
    }
}
