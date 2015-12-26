package com.lifeistech.android.chatlimit02;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.zip.Inflater;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    FrameLayout frame;
    EditText editText;
    Button button;
    // Viewを作るためのクラス
    LayoutInflater inflater;

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

        // FrameLayoutを作る
        frame = (FrameLayout) view.findViewById(R.id.messageLayout);
        editText = (EditText) view.findViewById(R.id.editText);
        button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editText.getText().toString();
//                Intent intent = new Intent();
//                intent.putExtra("key_message",message);
                // createMessageViewを呼び出してViewを作る
                View v = createMessageView(message);
                // frameLayoutにaddする
                frame.addView(v);
                // ViewにsetXとsetYをつける
                Random random = new Random();
                float x = random.nextFloat() * (frame.getWidth() - MESSAGE_VIEW_WIDTH);
                v.setX(x);
                // どこに表示するかのXの座標を保存する
                Log.d(MainFragment.class.getSimpleName(), "X:" + x + "FRAME_Width:" + frame.getWidth() + "MESSAGE_VIEW_WIDTH:" + MESSAGE_VIEW_WIDTH);
                v.setY(random.nextFloat() * (frame.getHeight() - v.getHeight()));
            }
        });
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


}
