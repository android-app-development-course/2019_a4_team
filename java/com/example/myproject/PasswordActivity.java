package com.example.myproject;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        final EditText et_test;
        final TextView tv_bottom;
        et_test=(EditText)findViewById(R.id.hysmm);
        tv_bottom=(TextView)findViewById(R.id.tv_bottom);
        et_test.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                et_test.getWindowVisibleDisplayFrame(r);
                if (et_test.getRootView().getHeight() - (r.bottom - r.top) > 500) { // if more than 100 pixels, its probably a keyboard...
                    //键盘弹出了
                    tv_bottom.setVisibility(View.VISIBLE);
                } else {
                    //键盘隐藏了
                    tv_bottom.setVisibility(View.GONE);
                }
            }
        });


    }
    public void fh_click1(View v){
        finish();
    }


}
