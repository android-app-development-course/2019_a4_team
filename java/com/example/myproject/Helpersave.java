package com.example.myproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Helpersave extends SQLiteOpenHelper{

    public Helpersave(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub

    }
    public Helpersave(Context context) {
        // TODO Auto-generated constructor stub
        super(context, "Information", null, 1);//这个构造器必须有，activity里会调用；
    }
    @Override
    public void onCreate(SQLiteDatabase jb) {
        // TODO Auto-generated method stub
        jb.execSQL("create table image (title varchar(20) primary key,location varchar(50),vtime int,people int,path String,type String,detail varchar(100));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}