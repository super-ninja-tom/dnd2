package com.example.tom.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.tom.myapplication.actions.MuteAction;

public class MuteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MuteAction.mute(this);

        finish();
    }
}
