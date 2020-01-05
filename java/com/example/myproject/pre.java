package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import static com.example.myproject.PostFragment.delstring;
import static com.example.myproject.PostFragment.locationstring;
import static com.example.myproject.PostFragment.numberstring;
import static com.example.myproject.PostFragment.picturePath;
import static com.example.myproject.PostFragment.selectText;
import static com.example.myproject.PostFragment.titlestring;

public class pre extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        File file = new File(picturePath);
        ImageView img = (ImageView) findViewById(R.id.image1);
        if(file.exists()){
            Bitmap bm = BitmapFactory.decodeFile(picturePath);
            img.setImageBitmap(bm);
        }
        EditText tm = (EditText)findViewById(R.id.tm);
        tm.setText(titlestring);
        EditText dd = (EditText)findViewById(R.id.dd);
        dd.setText(locationstring);
        TextView qx = (TextView)findViewById(R.id.qx);
        qx.setText(titlestring);
        TextView rs = (TextView)findViewById(R.id.rs);
        rs.setText(numberstring);
        TextView lx = (TextView)findViewById(R.id.lx);
        lx.setText(selectText);
        TextView jj = (TextView)findViewById(R.id.jj);
        jj.setText(delstring);

    }
}

