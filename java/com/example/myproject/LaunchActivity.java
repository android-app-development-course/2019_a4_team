package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @date 2018-04-24
 * @author jkloshhm
 *
 */

public class LaunchActivity extends AppCompatActivity {

    private Timer mTimer = new Timer();

    private CountdownCircleProgressBar mCircleProgressBar;
    /**
     * 开屏时长固定为5000ms
     */
    public final static int OPEN_SCREEN_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mCircleProgressBar = (CountdownCircleProgressBar) findViewById(R.id.skipBtn);
        mCircleProgressBar.setTimeMillis(OPEN_SCREEN_TIME);
        //mCircleProgressBar 播放动画
        mCircleProgressBar.play();
        //mCircleProgressBar 跳过按钮点击事件
        mCircleProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });

        //使用计时器Task来等待5秒跳转
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startMainActivity();
            }
        };
        mTimer.schedule(timerTask,OPEN_SCREEN_TIME);

    }

    /**
     * 跳转到MainActivity，同时finish WelcomeActivity
     */
    private void startMainActivity(){
        startActivity(new Intent(LaunchActivity.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注意：WelcomeActivity销毁时应该停止线程，防止内存泄漏！！！
        mCircleProgressBar.isRunning = false;
        // 注意：WelcomeActivity销毁时应该停止mTimer，防止内存泄漏！！！
        mTimer.cancel();
    }
}
