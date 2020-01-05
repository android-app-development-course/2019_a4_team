package com.example.myproject;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyPostActivity extends AppCompatActivity {
    private MyAdapter<Group> myAdapter=null;
    private List<Group> mData= null;
    private ListView list_group;
    private ImageButton like;
    int flag = 0;
    private static String path;

    private SQLiteDatabase db;
    private Helpersave myDBHelper;
    private StringBuilder sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypost);
        list_group=(ListView)findViewById(R.id.My_post);
        init();
    }
    private  void init(){
        //数据初始化
        Bitmap bm=null;
        //数据初始化
        myDBHelper= new Helpersave(MyPostActivity.this,"Information.db",null,1);
        db = myDBHelper.getWritableDatabase();
        sb=new StringBuilder();
        Cursor cursor = db.query("image", null, null, null, null, null,null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中
        mData = new ArrayList<Group>();
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String location=cursor.getString(cursor.getColumnIndex("location"));
            int vtime=cursor.getInt(cursor.getColumnIndex("vtime"));
            int people=cursor.getInt(cursor.getColumnIndex("people"));
            path=cursor.getString(cursor.getColumnIndex("path"));
            File file = new File(path);
            if(file.exists()){
                bm = BitmapFactory.decodeFile(path);
            }

            String type=cursor.getString(cursor.getColumnIndex("type"));
            String detail=cursor.getString(cursor.getColumnIndex("detail"));
            mData.add(new Group(type,title,location,vtime,people,bm));
            sb.append("id："+title);

        }
        // 关闭游标，释放资源
        cursor.close();
        //mData.add(new Group("Tag","TestName","TestAddress","TestTag1","TestTag2",R.drawable.img3));
        //Adapter初始化
        myAdapter = new MyAdapter<Group>((ArrayList)mData,R.layout.item_one) {
            @Override
            public void bindView(ViewHolder holder, Group obj) {
                //like = (ImageButton) LayoutInflater.from(MainActivity.this).inflate(R.layout.item_one, null).findViewById(R.id.Like);
                //like = (ImageButton)getActivity().findViewById(R.id.Like);;
                like = holder.getView(R.id.Like);
                holder.setImageResource(R.id.Group_image,obj.getaImage());
                holder.setText(R.id.Group_name,obj.getaName());
                holder.setText(R.id.Group_address,obj.getaAddress());
                holder.setText(R.id.Group_type,obj.getaType());
                holder.setText(R.id.Group_tag1,obj.getaTag1());
                holder.setText(R.id.Group_tag2,obj.getaTag2());
            }
        };
        //ListView设置下Adapter
        list_group.setAdapter(myAdapter);

    }
    public void fh_click2(View v){
        finish();
    }
}
