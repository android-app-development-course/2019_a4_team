package com.example.myproject;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class GroupListFragment extends Fragment {
    private FragmentManager fManager;
    private ArrayList<Group> groups;
    private ListView list_group;
    private ImageButton like;
    private Button search;
    private int flag=0;
    private static String path;

    private MyAdapter<Group> myAdapter=null;
    private List<Group> mData= null;

    private SQLiteDatabase db;
    private Helpersave myDBHelper;
    private StringBuilder sb;

    public GroupListFragment(FragmentManager fManager,ArrayList<Group> groups){
        this.fManager = fManager;
        this.groups = groups;
    }

    public GroupListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_grouplist, container, false);
        list_group=(ListView) view.findViewById(R.id.list_group);
        search = (Button)view.findViewById(R.id.Search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                //创建组件，通过组件来响应
                ComponentName component = new ComponentName(getActivity(),SearchActivity.class);
                intent1.setComponent(component);
                startActivity(intent1);
            }
        });
        //like = (ImageButton)getActivity().findViewById(R.id.Like);
        init();



        return view;
    }

    private  void init(){
        Bitmap bm=null;
        //数据初始化
        myDBHelper= new Helpersave(getActivity(),"Information.db",null,1);
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
        //mData.add(new Group("Tag","TestName","TestAddress",1,2,"123"));
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
                holder.setOnClickListener(R.id.Group_image, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent();
                                //创建组件，通过组件来响应
                                ComponentName component = new ComponentName(getActivity(), DetailActivity.class);
                                intent1.setComponent(component);
                                startActivity(intent1);
                            }
                        });
                holder.setOnClickListener(R.id.Like, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(flag==1)
                        {
                            like.setBackgroundResource(R.drawable.ic_star1);
                            flag=0;
                        }
                        else
                        {
                            like.setBackgroundResource(R.drawable.ic_star2);
                            flag=1;
                        }
                    }
                });
            }
        };
        //ListView设置下Adapter
        list_group.setAdapter(myAdapter);

    }

}
