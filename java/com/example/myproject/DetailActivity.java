package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    private Button return_buton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        return_buton = (Button) findViewById(R.id.Return_button);
        return_buton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
