package com.example.tom.myapplication;

import android.app.Activity;
import android.os.Bundle;

public class SleepActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LockAction.lock(this);

        finish();
    }
}
