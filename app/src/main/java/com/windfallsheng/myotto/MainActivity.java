package com.example.myotto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtnJumpToPost, mBtnJumpToProduce, mBtnJumpToMainActivity;
    private TextView mTvMessage, mTvMessageUpdate;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvMessage = (TextView) this.findViewById(R.id.tv_message);
        mTvMessageUpdate = (TextView) this.findViewById(R.id.tv_message_update);
        mBtnJumpToPost = (Button) this.findViewById(R.id.btn_jump_to_post);
        mBtnJumpToMainActivity = (Button) this.findViewById(R.id.btn_jump_to_main_activity);
        mBtnJumpToProduce = (Button) this.findViewById(R.id.btn_jump_to_produce);
        mBtnJumpToPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostActivity.start(MainActivity.this);
            }
        });
        mBtnJumpToProduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProduceActivity.start(MainActivity.this);
            }
        });
        mBtnJumpToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(MainActivity.this);
            }
        });
        Log.i(TAG, "method:onCreate#this#hashCode=" + this.hashCode());
        OttoBus.getInstance().register(this);
    }

    @Subscribe
    public void refreshMessage(EventData eventData) {
        Log.i(TAG, "method:Subscribe#refreshMessage#eventData#hashCode=" + eventData.hashCode());
        Log.i(TAG, "method:Subscribe#refreshMessage#eventData=" + eventData);
        mTvMessage.setText(eventData.getUserName() + ":\n\n" + eventData.getMessage());
    }

    @Subscribe
    public void updateMessage(EventData eventData) {
        Log.i(TAG, "method:updateMessage#updateMessage#eventData#hashCode=" + eventData.hashCode());
        Log.i(TAG, "method:Subscribe#updateMessage#eventData=" + eventData);
        mTvMessageUpdate.setText(eventData.getUserName() + ":\n\n" + eventData.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }
}
