package com.apps.finalproj.mobsec.receivers;

/**
 * Created by Poornima on 12/5/15.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.apps.finalproj.mobsec.services.BackgroundService;


public abstract class BootAndUpdateReceiver extends BroadcastReceiver {

//    private static final String TAG = "BootAndUpdateReceiver";
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") ||
//                intent.getAction().equals("android.intent.action.MY_PACKAGE_REPLACED")) {
//            Intent startServiceIntent = new Intent(context, BackgroundService.class);
//            context.startService(startServiceIntent);
//        }
//    }
}