package com.example.kings.mid_term_project.DataBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;

public class Person implements Parcelable {
    // 姓名，性别，种类(虚拟或者现实)，生存时间，图片
    private String name;
    private String sex;
    private String category;
    private String time;
    private String description;
    private Bitmap bitmap;

    Person(){}

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

    public Person(Parcel source) {
        name = source.readString();
        sex = source.readString();
        category = source.readString();
        time = source.readString();
        description = source.readString();
        bitmap = source.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeString(category);
        dest.writeString(time);
        dest.writeString(description);
        //dest.writeParcelable(bitmap, 0);

//        ByteArrayOutputStream baos=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] bitmapByte = baos.toByteArray();
//        dest.writeByteArray(bitmapByte);
    }

    public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            Person temp = new Person();
            temp.name = source.readString();
            temp.sex = source.readString();
            temp.category = source.readString();
            temp.time = source.readString();
            temp.description = source.readString();
            //temp.bitmap = Bitmap.CREATOR.createFromParcel(source);
//            byte[] temp_bytes;
//            source.readByteArray(temp_bytes);
//            temp.bitmap = BitmapFactory.decodeByteArray(temp_bytes, 0, temp_bytes.length);
            return temp;
        }
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
