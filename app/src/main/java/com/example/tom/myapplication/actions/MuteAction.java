package com.example.tom.myapplication.actions;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.text.TextUtils;
import android.util.Log;

public class MuteAction extends NotificationListenerService {

    private static final String TAG = MuteAction.class.getName();
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    private AudioManager audioManager;
    private static MuteAction instance;

    public static void mute(Activity mainActivity) {


        if (!isNotificationServiceEnabled(mainActivity)) {
            mainActivity.startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
        } else {
            Log.d("MuteActivity", "startService");
            mainActivity.startService(new Intent(mainActivity, MuteAction.class));
        }

        if (instance != null) {
            instance.toggle();
        } else {
            Log.w(TAG, "no instance");
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

    @Override
    public void onCreate() {
        Log.d("MuteAction", "onCreate");
        super.onCreate();

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (instance == null) {
            instance = this;
        } else {
            Log.w(TAG, "instance would have been reset");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MuteAction", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onListenerConnected() {
        Log.d("MuteAction", "onListenerConnected");
        super.onListenerConnected();
    }

    public void toggle() {
        Log.d("MuteAction", "toggle");

        if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) { //getCurrentInterruptionFilter() == NotificationListenerService.INTERRUPTION_FILTER_NONE) {
//            requestInterruptionFilter(NotificationListenerService.INTERRUPTION_FILTER_ALL);
            if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
                Log.d("MuteActivity", "Increase media vol");
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 3, 0);
            }
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        } else {
//            requestInterruptionFilter(NotificationListenerService.INTERRUPTION_FILTER_NONE);
            Log.d("MuteActivity", "Silent media vol");
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }
    }


    @Override
    public void onInterruptionFilterChanged(int interruptionFilter) {
        Log.d("MuteAction", "onInterruptionFilterChanged " + getCurrentInterruptionFilter());
    }
}
