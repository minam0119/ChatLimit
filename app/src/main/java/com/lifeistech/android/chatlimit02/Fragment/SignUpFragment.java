package com.lifeistech.android.chatlimit02.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeistech.android.chatlimit02.Activity.LoginActivity;
import com.lifeistech.android.chatlimit02.Activity.MainActivity;
import com.lifeistech.android.chatlimit02.R;
import com.lifeistech.android.chatlimit02.Class.User;
import com.parse.ParseException;
import com.parse.SignUpCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class SignUpFragment extends Fragment {
    EditText editTextUserName;
    EditText editTextMail;
    EditText editTextPassword;
    ImageView button;
    Toolbar toolBar;
    TextView loginText;
    SharedPreferences sharedPreferences;

    public SignUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextUserName = (EditText) view.findViewById(R.id.editTextUserName);
        editTextMail = (EditText) view.findViewById(R.id.editTextMail);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);

        button = (ImageView) view.findViewById(R.id.button);
        toolBar = (Toolbar) view.findViewById(R.id.toolbar);

        toolBar.setTitle("Signup");
        toolBar.setTitleTextColor(Color.WHITE);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final boolean isLogin = sharedPreferences.getBoolean("isLogin",false);

        if(isLogin){
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);

        }

        loginText = (TextView)view.findViewById(R.id.textView6);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUserName.getText().toString();
                String mail = editTextMail.getText().toString();
                String password = editTextPassword.getText().toString();

                sharedPreferences.edit().putBoolean("isLogin",true).commit();

                User user = new User();
                user.setUsername(username);
                // Emailをここに入れる
                user.setEmail(mail);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.e(SignUpFragment.class.getSimpleName(), "サインアップ成功");
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            e.printStackTrace();
                            Log.e(SignUpFragment.class.getSimpleName(), "エラーによりサインアップ失敗");
                            Toast.makeText(getActivity(), "エラーによりサインアップ失敗", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }


}
