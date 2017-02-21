package com.example.tom.myapplication;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by tom on 16/03/2017.
 */

public class LockAction {
    public static void lock(Activity sleepActivity) {
        DevicePolicyManager mDPM = (DevicePolicyManager) sleepActivity.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName cn = new ComponentName(sleepActivity.getApplicationContext(), DeviceAdminSampleReceiver.class);
        if (!mDPM.isAdminActive(cn)) {
            System.out.println("hi2");
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
            sleepActivity.startActivity(intent);
        } else {
            mDPM.lockNow();
        }
    }
}
