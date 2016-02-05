package com.lifeistech.android.chatlimit02;

import android.content.Context;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    FrameLayout frame;
    EditText editText;
    Button button;

    //private ActionBarDrawerToggle actionBarDrawerToggle;
    //private DrawerLayout drawerLayout;

    // Viewを作るためのクラス
    LayoutInflater inflater;
    android.os.Handler handler = new android.os.Handler();
    private ParseUser user;
    private Timer timer;
    private Location location;

    private static float MESSAGE_VIEW_WIDTH;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MESSAGE_VIEW_WIDTH = getResources().getDimension(R.dimen.message_view_width);

        user = User.getCurrentUser();

        // FrameLayoutを作る
        frame = (FrameLayout) view.findViewById(R.id.messageLayout);
        editText = (EditText) view.findViewById(R.id.editText);
        button = (Button) view.findViewById(R.id.button);

        //sendButtonを押したときの処理
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = editText.getText().toString();
                editText.setText("");
                //キーボードを非表示にする
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

//                Intent intent = new Intent();
//                intent.putExtra("key_message",message);
                // 画面にメッセージを追加
                addMessage(messageText);
                // メッセージを保存する
                Message message = new Message();
                message.setMessage(messageText);
                message.setUserId(user.getObjectId());
                if (location != null) {
                    message.setLocation(new ParseGeoPoint(location.getLatitude(), location.getLongitude()));
                }
                message.saveInBackground();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        startLoading();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopLoading();
    }

    private void addMessage(String message) {
        // createMessageViewを呼び出してViewを作る
        View v = createMessageView(message);
        // frameLayoutにaddする
        frame.addView(v);
        setViewLimit(v);
        // ViewにsetXとsetYをつける
        Random random = new Random();
        float x = random.nextFloat() * (frame.getWidth() - MESSAGE_VIEW_WIDTH);
        v.setX(x);
        // どこに表示するかのXの座標を保存する
        Log.d(MainFragment.class.getSimpleName(), "X:" + x + "FRAME_Width:" + frame.getWidth() + "MESSAGE_VIEW_WIDTH:" + MESSAGE_VIEW_WIDTH);
        v.setY(random.nextFloat() * (frame.getHeight() - v.getHeight()));
    }

    // Viewを作る
    private View createMessageView(String message) {
        // MessageViewを作る
        View messageView = inflater.inflate(R.layout.view_message, null);
        //TODO TextViewを取得してそこにテキストをセットする
        TextView textView = (TextView) messageView.findViewById(R.id.textView);
        textView.setText(message);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        messageView.setLayoutParams(layoutParams);
        return messageView;
    }

    // 3秒でViewを消すメソッド
    public void setViewLimit(final View view) {
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.GONE);
                    }
                });
            }
        }, 3000);*/
        // 徐々に透明にする
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(5 * 1000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(alphaAnimation);
    }

    private void getMessages() {
        ParseQuery<Message> parseQuery = new ParseQuery<Message>(Message.class);
        parseQuery.whereNotEqualTo("userId", user.getObjectId());
        Date date = new Date();
        date.setTime(date.getTime() - 20 * 1000);
        parseQuery.whereGreaterThanOrEqualTo("createdAt", date);
        if (location != null) {
            ParseGeoPoint point = new ParseGeoPoint(location.getLatitude(), location.getLongitude());
            parseQuery.whereNear("location", point);
        }
        parseQuery.setLimit(5);
        parseQuery.findInBackground(new FindCallback<Message>() {
            @Override
            public void done(List<Message> list, ParseException e) {
                for (Message message : list) {
                    addMessage(message.getMessage());
                }
            }
        });
    }

    private void startLoading() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getMessages();
                    }
                });
            }
        }, 1000, 5 * 1000);
    }

    private void stopLoading() {
        timer.cancel();
        timer = null;
    }

    public void updateLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

}
