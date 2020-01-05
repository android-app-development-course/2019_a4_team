package com.example.myproject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ShowidentityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showidentity);
        Button back=(Button)findViewById(R.id.fh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void bj(View v){
        Intent intent = new Intent(ShowidentityActivity.this,IdentityActivity.class);
        startActivity(intent);
    }
}
