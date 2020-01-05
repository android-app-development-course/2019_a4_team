package com.example.myproject;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class PostFragment extends Fragment implements View.OnClickListener {

    PostFragment() { }

    private Uri imageuri;
    public static final int CHOOSE_PHOTO = 2;
    static String picturePath = null;
    static String titlestring = null;
    static String locationstring = null;
    static String timestring = null;
    static String numberstring = null;
    static String selectText = null;
    static String delstring = null;

    Helpersave hp;
    private EditText e1, e2, e3, e4, del;
    private Button pub, pre;
    private ImageView v;

    private Helpersave databaseHelper;
    private Spinner s1;
    //数据库名称
    private static final String DATABASE_NAME = "Information.db";
    //数据库版本号
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Image";
    private SQLiteDatabase db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_post, container, false);
        e1 = (EditText) view.findViewById(R.id.t1);
        e2 = (EditText) view.findViewById(R.id.lo);
        e3 = (EditText) view.findViewById(R.id.ti);
        e4 = (EditText) view.findViewById(R.id.num);
        s1 = (Spinner) view.findViewById(R.id.types);
        del = (EditText) view.findViewById(R.id.del);
        LayoutInflater factory = LayoutInflater.from(getActivity());
        View layout = factory.inflate(R.layout.activity_pre,null);
        v = (ImageView)layout.findViewById(R.id.image1);
        final Button picture = (Button) view.findViewById(R.id.pic);
        pub = (Button) view.findViewById(R.id.button2);

        pre = (Button) view.findViewById(R.id.button1);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titlestring = e1.getText().toString();
                locationstring = e2.getText().toString();
                timestring = e3.getText().toString();
                numberstring = e4.getText().toString();
                selectText = s1.getSelectedItem().toString();
                delstring = del.getText().toString();
                Intent i = new Intent(getActivity(), pre.class);
                startActivity(i);
            }
        });
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    photos();
                }
            }
        });
        pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titlestring = e1.getText().toString();
                locationstring = e2.getText().toString();
                timestring = e3.getText().toString();
                numberstring = e4.getText().toString();
                selectText = s1.getSelectedItem().toString();
                delstring = del.getText().toString();

                if (titlestring.equals("") || locationstring.equals("") || timestring.equals("") || numberstring.equals("") || delstring.equals("")) {
                    new AlertDialog.Builder(getActivity()).setTitle("错误").setMessage("不能为空").setPositiveButton("确定", null).show();
                } else {
                    databaseHelper = new Helpersave(getActivity(), DATABASE_NAME, null, DATABASE_VERSION);
                    db = databaseHelper.getReadableDatabase();
                    db.execSQL("insert into image(title,location,vtime,people,path,type,detail) values(?,?,?,?,?,?,?)", new String[]{titlestring, locationstring, timestring, numberstring, picturePath, selectText, delstring});

                    Toast.makeText(getActivity(), "发布成功！", Toast.LENGTH_LONG).show();
                    Intent b = new Intent(getActivity(), MainActivity.class);
                    startActivity(b);
                }
            }
        });
        return view;
    }
    public void photos() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== CHOOSE_PHOTO){
            if (resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImageOnKitKat(data);
                } else {
                    handleImageBeforeKitKat(data);
                }
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    photos();
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        imageuri = data.getData();
        if(DocumentsContract.isDocumentUri(getActivity(),imageuri)){
            String docID = DocumentsContract.getDocumentId(imageuri);
            if("com.android.providers.media.documents".equals(imageuri.getAuthority())){
                String id = docID.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                picturePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.provider.download.documents".equals(imageuri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:downloads/public_downloads"),Long.valueOf(docID));
                picturePath = getImagePath(contentUri,null);
            }
            else if("content".equalsIgnoreCase(imageuri.getScheme())){
                picturePath = getImagePath(imageuri,null);
            }
            else if("file".equalsIgnoreCase(imageuri.getScheme())){
                picturePath = imageuri.getPath();
            }
            displayImage(picturePath);
        }
    }
    private void handleImageBeforeKitKat(Intent data){
        imageuri = data.getData();
        picturePath = getImagePath(imageuri,null);
        displayImage(picturePath);
    }
    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String path){
        if(path != null){
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            v.setImageBitmap(bitmap);
        }
        else{
            Toast.makeText(getActivity(),"获取相册失败！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
