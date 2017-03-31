package com.example.tom.myapplication.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tom.myapplication.R;
import com.example.tom.myapplication.actions.LockAction;

import static java.lang.Thread.sleep;

public class LockActivity extends Activity {
    private static final String TAG = LockActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();


        LockAction.lock(this);

//        finish();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onDestroy();

//        try {
//            sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        LockAction.unlock(this);
        finish();
    }
}
