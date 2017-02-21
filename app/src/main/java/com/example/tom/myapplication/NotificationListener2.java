package com.example.tom.myapplication;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.util.Log;

public class NotificationListener2 extends NotificationListenerService {
    @Override
    public void onCreate() {
        Log.d("NotificationListener2", "onCreate");
        super.onCreate();
        MuteAction.listener = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("NotificationListener2", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onListenerConnected() {
        Log.d("NotificationListener2", "onListenerConnected");
        super.onListenerConnected();
    }

    public void toggle() {
        Log.d("NotificationListener2", "toggle");
        int currentInterruptionFilter = getCurrentInterruptionFilter();
        if (currentInterruptionFilter == NotificationListenerService.INTERRUPTION_FILTER_NONE) {
            requestInterruptionFilter(NotificationListenerService.INTERRUPTION_FILTER_ALL);
        } else {
            requestInterruptionFilter(NotificationListenerService.INTERRUPTION_FILTER_NONE);
        }
    }


    @Override
    public void onInterruptionFilterChanged(int interruptionFilter) {
        Log.d("NotificationListener2", "onInterruptionFilterChanged " + getCurrentInterruptionFilter());
    }
}
