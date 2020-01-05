package com.example.myproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //数据库名称
    private static final String DATABASE_NAME="Information.db";

    //数据库版本号
    private static final int DATABASE_VERSION=1;

    //数据库SQL语句 添加一个表

    public DatabaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table user ( name varchar(30) primary key,password varchar(30));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
