package com.lifeistech.android.chatlimit02.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lifeistech.android.chatlimit02.Class.User;
import com.lifeistech.android.chatlimit02.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionbar = this.getActionBar();
        if (actionbar != null) actionbar.hide();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent;
                        if (User.getCurrentUser() == null || !User.getCurrentUser().isAuthenticated()) {
                            intent = new Intent(SplashActivity.this, SignUpActivity.class);
                        } else {
                            intent = new Intent(SplashActivity.this, MainActivity.class);
                        }
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, 2000);
    }

}
