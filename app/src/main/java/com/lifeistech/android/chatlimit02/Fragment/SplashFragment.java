package com.lifeistech.android.chatlimit02.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifeistech.android.chatlimit02.Activity.SplashActivity;
import com.lifeistech.android.chatlimit02.R;

import java.util.logging.Handler;

/**
 * A placeholder fragment containing a simple view.
 */
public class SplashFragment extends Fragment {

    public SplashFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(),MainFragment.class);
                startActivity(intent);
                finish();
            }
        }, 2000);*/

    }
}
