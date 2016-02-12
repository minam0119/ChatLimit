package com.lifeistech.android.chatlimit02.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lifeistech.android.chatlimit02.R;
import com.lifeistech.android.chatlimit02.Class.User;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar actionbar = this.getActionBar();
        if(actionbar!=null)actionbar.hide();
    }
}
