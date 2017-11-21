package com.example.kings.mid_term_project.DataBase;

import android.graphics.Bitmap;

/**
 * Created by weimumu on 2017/11/21.
 */


public class Person {
    // 姓名，性别，种类(虚拟或者现实)，生存时间，图片
    private String name;
    private String sex;
    private String category;
    private String time;
    private String description;
    private Bitmap bitmap;

    public Person(String name, String sex, String category, String time, String description, Bitmap bitmap) {
        this.name = name;
        this.time = time;
        this.sex = sex;
        this.category = category;
        this.description = description;
        this.bitmap = bitmap;
    }

    public String getDecription() {
        return description;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getCategory() {
        return category;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setDecription(String description) {
        this.description = description;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
