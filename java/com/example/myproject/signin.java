package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    private TextView textview;
    //数据库名称
    private static final String DATABASE_NAME="Information.db";

    //数据库版本号
    private static final int DATABASE_VERSION=1;
    //表名
    private static final String TABLE_NAME="user";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Button button1,button2;
    private EditText nameText,passText;
    private Intent intent;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        nameText=(EditText)findViewById(R.id.name);
        passText=(EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.button);
        Button zhuce = (Button)findViewById(R.id.Zhuce);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setOnClickListener(new LoginListener());
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(signin.this,signup.class);
                startActivity(i1);
            }
        });
    }
    class LoginListener implements View.OnClickListener {
        public void onClick(View v){
            String nameString =nameText.getText().toString();
            String passString=passText.getText().toString();
            if(nameString.equals("")||passString.equals(""))
            {
                //弹出消息框
                new AlertDialog.Builder(signin.this).setTitle("错误")
                        .setMessage("帐号或密码不能空").setPositiveButton("确定", null)
                        .show();
            }else{
                isUserinfo(nameString,passString);
            }
        }
    }

    public Boolean isUserinfo(String name,String pass)
    {
        String nameString=name;
        String passString=pass;
        databaseHelper=new DatabaseHelper(signin.this,DATABASE_NAME,null,DATABASE_VERSION);
        db =  databaseHelper.getReadableDatabase();
        try{
            Cursor c = db.rawQuery("select * from   user  where   name=? ",new String[] { nameString });
            if(c.getCount()==0)
                Toast.makeText(this,"用户不存在",Toast.LENGTH_LONG).show();
            else{
                Cursor cursor=db.query(TABLE_NAME, new String[]{"name","password"},"name=?",new String[]{nameString},null,null,"password");
                    while(cursor.moveToNext()) {

                        String password = cursor.getString(cursor.getColumnIndex("password"));

                        if (passString.equals(password)) {
                            new AlertDialog.Builder(signin.this).setTitle("正确")
                                    .setMessage("成功登录").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    Intent a = new Intent(signin.this, MainActivity.class);
                                    startActivity(a);
                                }
                            }).show();

                            break;
                        } else {
                            Toast.makeText(this, "用户名密码不正确", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
            }
        }catch(SQLiteException e){
            CreatTable();
        }
        return false;
    }
    private void CreatTable() {
        // TODO Auto-generated method stub
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " (name varchar(30) primary key,password varchar(30));";
        try{
            db.execSQL(sql);
        }catch(SQLException ex){}
    }

}
