package com.apps.finalproj.mobsec.services;

/**
 * Created by Poornima on 12/5/15.
 */

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.apps.finalproj.mobsec.receivers.PeriodicTaskReceiver;

// This will periodically send usage info to backend server for ML
public abstract class BackgroundService extends Service {

//    private static final String TAG = "BackgroundService";
//
//    PeriodicTaskReceiver mPeriodicTaskReceiver = new PeriodicTaskReceiver();
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        SharedPreferences sharedPreferences = PreferenceManager
//                .getDefaultSharedPreferences(getApplicationContext());
//        IntentFilter batteryStatusIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        Intent batteryStatusIntent = registerReceiver(null, batteryStatusIntentFilter);
//
//        if (batteryStatusIntent != null) {
//            int level = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//            int scale = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
//            float batteryPercentage = level / (float) scale;
//            float lowBatteryPercentageLevel = 0.14f;
//
//            try {
//                int lowBatteryLevel = Resources.getSystem().getInteger(Resources.getSystem().getIdentifier("config_lowBatteryWarningLevel", "integer", "android"));
//                lowBatteryPercentageLevel = lowBatteryLevel / (float) scale;
//            } catch (Resources.NotFoundException e) {
//                Log.e(TAG, "Missing low battery threshold resource");
//            }
//
//            sharedPreferences.edit().putBoolean(PeriodicTaskReceiver.BACKGROUND_SERVICE_BATTERY_CONTROL, batteryPercentage >= lowBatteryPercentageLevel).apply();
//        } else {
//            sharedPreferences.edit().putBoolean(PeriodicTaskReceiver.BACKGROUND_SERVICE_BATTERY_CONTROL, true).apply();
//        }
//
//        mPeriodicTaskReceiver.restartPeriodicTaskHeartBeat(getApplicationContext());
//        return START_STICKY;
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
}