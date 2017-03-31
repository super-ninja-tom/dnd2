package com.example.tom.myapplication.actions;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.example.tom.myapplication.activity.LockActivity;

/**
 * Created by tom on 16/03/2017.
 */

public class LockAction {
    public static void lock(Activity lockActivity) {
        Settings.System.putInt(lockActivity.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 0);
//        DevicePolicyManager mDPM = (DevicePolicyManager) sleepActivity.getSystemService(Context.DEVICE_POLICY_SERVICE);
//        ComponentName cn = new ComponentName(sleepActivity.getApplicationContext(), DeviceAdminSampleReceiver.class);
//        if (!mDPM.isAdminActive(cn)) {
//            System.out.println("hi2");
//            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
//            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
//            sleepActivity.startActivity(intent);
//        } else {
//            mDPM.lockNow();
//        }
    }

    public static void unlock(LockActivity lockActivity) {
        Settings.System.putInt(lockActivity.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 120000);
    }
}
