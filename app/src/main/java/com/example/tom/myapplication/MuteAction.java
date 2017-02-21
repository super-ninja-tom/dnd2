package com.example.tom.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by tom on 16/03/2017.
 */
public class MuteAction {

    public static NotificationListener2 listener;
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

    public static void mute(Activity mainActivity) {


        if (!isNotificationServiceEnabled(mainActivity)) {
            mainActivity.startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
        } else {
            Log.d("MainActivity", "startService");
            mainActivity.startService(new Intent(mainActivity, NotificationListener2.class));
        }

        if (listener != null) {
            listener.toggle();
        } else {
            Log.w("MainActivity", "no listener");
        }

        AudioManager audioManager = (AudioManager) mainActivity.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
            Log.d("MainActivity", "Silent media vol");
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        } else {
            if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
                Log.d("MainActivity", "Increase media vol");
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 3, 0);
            }
        }
    }


    private static boolean isNotificationServiceEnabled(Activity mainActivity) {
        String pkgName = mainActivity.getPackageName();
        final String flat = Settings.Secure.getString(mainActivity.getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
