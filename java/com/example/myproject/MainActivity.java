package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener {

    private RadioButton tab_groupinfo;
    private RadioGroup home_tab;
    private ImageButton like;
    private  FrameLayout hc;

    private int flag = 0;
    private ListView list_group;

    private MyAdapter<Group> myAdapter = null;
    private List<Group> mData = null;
    private ArrayList<Group> groups = null;

    private FragmentManager fManager;
    private PostFragment fg2;
    private GroupListFragment fg1;
    private MineFragment fg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        home_tab = (RadioGroup)findViewById(R.id.Home_tab);
        home_tab.setOnCheckedChangeListener(this);
        tab_groupinfo = (RadioButton)findViewById(R.id.Tab_groupinfo);
        tab_groupinfo.setChecked(true);
        fg1 = new GroupListFragment(fManager,groups);
        //FragmentTransaction ft = fManager.beginTransaction();
        //ft.replace(R.id.home_container, fg1);
        //ft.commit();
        //init();
        //like.bringToFront();

    }


    @Override
    public void onCheckedChanged(RadioGroup group,int checkedId){
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.Tab_groupinfo:
                    fg1 = new GroupListFragment(fManager,groups);
                    fTransaction.replace(R.id.home_container,fg1);
                break;
            case R.id.tab_postinfo:
                    fg2 = new PostFragment();
                    fTransaction.replace(R.id.home_container,fg2);
                break;
            case R.id.tab_my:
                    fg3 = new MineFragment();
                    fTransaction.replace(R.id.home_container,fg3);
                break;

        }
        fTransaction.commit();
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }
}

