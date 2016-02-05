package com.lifeistech.android.chatlimit02;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {
    Toolbar toolbar;
    Button button;
    EditText editTextUserName,editTextPassword;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(Color.WHITE);
        button = (Button)view.findViewById(R.id.button);
        editTextUserName = (EditText)view.findViewById(R.id.editText2);
        editTextPassword = (EditText)view.findViewById(R.id.editText3);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                User.logInInBackground(username,password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e == null) {
                            Log.e(SignUpFragment.class.getSimpleName(), "ログイン成功");
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            e.printStackTrace();
                            Log.e(SignUpFragment.class.getSimpleName(), "エラーによりログイン失敗");
                            Toast.makeText(getActivity(), "エラーによりログイン失敗", Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });
    }
}
