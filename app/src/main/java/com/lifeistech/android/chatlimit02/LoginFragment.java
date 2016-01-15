package com.lifeistech.android.chatlimit02;

import android.app.ActionBar;
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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {
    EditText editTextUserName;
    EditText editTextMail;
    EditText editTextPassword;
    Button button;
    Toolbar toolBar;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextUserName = (EditText)view.findViewById(R.id.editTextUserName);
        editTextMail = (EditText)view.findViewById(R.id.editTextMail);
        editTextPassword = (EditText)view.findViewById(R.id.editTextPassword);

        button = (Button)view.findViewById(R.id.button2);
        toolBar = (Toolbar)view.findViewById(R.id.toolbar);

        toolBar.setTitle("Signup");
        toolBar.setTitleTextColor(Color.WHITE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUserName.getText().toString();
                String mail = editTextUserName.getText().toString();
                String password = editTextUserName.getText().toString();

                User user = new User();
                user.setUsername(username);
                // Emailをここに入れる
                user.setEmail(mail);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.e(LoginFragment.class.getSimpleName(), "サインアップ成功");
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.e(LoginFragment.class.getSimpleName(), "エラーによりサインアップ失敗");
                            Toast.makeText(getActivity(), "エラーによりサインアップ失敗", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }


}
