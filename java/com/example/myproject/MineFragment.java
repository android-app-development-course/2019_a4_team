package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;


public class MineFragment extends Fragment implements View.OnClickListener{
    private LinearLayout notification;
    private LinearLayout feedback;
    private LinearLayout person_data;
    private LinearLayout my_post;
    private ImageView head;
    MineFragment(){ }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_mine, container, false);
        notification = (LinearLayout)view.findViewById(R.id.Notification);
        notification.setOnClickListener(this);
        feedback = (LinearLayout)view.findViewById(R.id.Feedback);
        feedback.setOnClickListener(this);
        person_data = (LinearLayout)view.findViewById(R.id.Person_data);
        person_data.setOnClickListener(this);
        my_post = (LinearLayout)view.findViewById(R.id.My_post);
        my_post.setOnClickListener(this);
        head = (ImageView) view.findViewById(R.id.Head);
        head.setOnClickListener(this);

        return view;
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.Notification:
                Intent intent1 = new Intent(getActivity(),ShowidentityActivity.class);
                startActivity(intent1);
                break;
            case R.id.Feedback:
                Intent intent2 = new Intent(getActivity(),PasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.Person_data:
                Intent intent3 = new Intent(getActivity(),LikeActivity.class);
                startActivity(intent3);
                break;
            case R.id.My_post:
                Intent intent4 = new Intent(getActivity(),MyPostActivity.class);
                startActivity(intent4);
                break;
            case R.id.Head:
                Intent intent5 = new Intent(getActivity(),signin.class);
                startActivity(intent5);
                break;
        }

    }
}
