package com.example.finalproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadCast extends BroadcastReceiver {

    public final static String MyAction = "com.example.broadcasttest.ACTION_MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction()))
        {
            Toast.makeText(context, "전원이 연결되었습니다.", Toast.LENGTH_LONG).show();
        }
        else if(Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction()))
        {
            Toast.makeText(context, "전원 연결이 해제되었습니다.", Toast.LENGTH_LONG).show();
        }
    }
}
