package com.example.tom.myapplication;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by tom on 02/03/2017.
 */
public class DeviceAdminSampleReceiver extends DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        Toast.makeText(context, "enabled", Toast.LENGTH_SHORT).show();
    }
}
