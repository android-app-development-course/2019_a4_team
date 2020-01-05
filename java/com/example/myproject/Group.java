package com.example.myproject;

import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.widget.ImageButton;

public class Group {
    private String aType;
    private String aName;
    private String aAddress;
    private int aTag1;
    private int aTag2;
    private Bitmap aImage;
    private int aLike;

    public Group(){}

    public Group(String aType,String aName,String aAddress,int aTag1,int aTag2,Bitmap aImage){
        this.aType = aType;
        this.aName = aName;
        this.aAddress = aAddress;
        this.aTag1 = aTag1;
        this.aTag2 = aTag2;
        this.aImage = aImage;
    }

    public Bitmap getaImage() {
        return aImage;
    }

    public String getaType() {
        return aType;
    }

    public String getaName() {
        return aName;
    }

    public String getaAddress() {
        return aAddress;
    }

    public int getaTag1() {
        return aTag1;
    }

    public int getaTag2() {
        return aTag2;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setaAddress(String aAddress) {
        this.aAddress = aAddress;
    }

    public void setaTag1(int aTag1) {
        this.aTag1 = aTag1;
    }

    public void setaTag2(int aTag2) {
        this.aTag2 = aTag2;
    }

    public void setaImage(Bitmap aImage) {
        this.aImage = aImage;
    }

    public int getaLike(){
        return aLike;
    }
}
