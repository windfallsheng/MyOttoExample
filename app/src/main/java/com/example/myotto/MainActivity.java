package com.example.myotto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtnJumpToPost, mBtnJumpToProduce;
    private TextView mTvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvMessage = (TextView) this.findViewById(R.id.tv_message);
        mBtnJumpToPost = (Button) this.findViewById(R.id.btn_jump_to_post);
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
        OttoBus.getInstance().register(this);
//        CrashReport.testJavaCrash();
    }

    @Subscribe
    public void refreshMessage(EventData eventData) {
        Log.i(TAG, "method:Subscribe#refreshMessage#eventData=" + eventData);
        mTvMessage.setText(eventData.getUserName() + ":\n\n" + eventData.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoBus.getInstance().unregister(this);
    }
}
