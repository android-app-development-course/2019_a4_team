package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class signup extends AppCompatActivity {


    private DatabaseHelper databaseHelper;
    private static final String DATABASE_NAME="Information.db";
    //数据库版本号
    private static final int DATABASE_VERSION=1;
    private  EditText name;
    private  EditText repassword;
    private  EditText password;
    private  Button register;
    private  Button back;

    private static final String TABLE_NAME="user";
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.Name);
        password=(EditText)findViewById(R.id.Password);
        repassword=(EditText)findViewById(R.id.Password2);
        register=(Button)findViewById(R.id.Button);

        Button xieyi = (Button) findViewById(R.id.B1);
        Button yinsi = (Button) findViewById(R.id.B2);
        Button back = (Button) findViewById(R.id.Back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(signup.this, LoginAssociation.class);
                startActivity(i1);
            }
        });
        yinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(signup.this, privacy.class);
                startActivity(i2);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namestring = name.getText().toString();
                String passstring = password.getText().toString();
                String repassstring = repassword.getText().toString();
                if (passstring.equals(repassstring)) {
                    databaseHelper = new DatabaseHelper(signup.this, DATABASE_NAME, null, DATABASE_VERSION);
                    db = databaseHelper.getReadableDatabase();
                    db.execSQL("insert into user (name,password) values(?,?)", new String[]{namestring, passstring});

                    Toast.makeText(signup.this, "注册成功！", Toast.LENGTH_LONG).show();
                    Intent b = new Intent(signup.this, signin.class);
                    startActivity(b);
                } else {
                    Toast.makeText(signup.this, "两次密码不一致", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

}


