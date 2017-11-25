package com.example.kings.mid_term_project.DataBase;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


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

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
//        final byte[] bytes = baos.toByteArray();
//        dest.writeInt(bytes.length);
//        dest.writeByteArray(bytes);

    }

    public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            Person temp = new Person();
            temp.name = source.readString();
            temp.sex = source.readString();
            temp.category = source.readString();
            temp.time = source.readString();
            temp.description = source.readString();

//            int length = source.readInt();
//            byte [] bytes = new byte[length];
//            source.readByteArray(bytes);
//            temp.bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);

            return temp;
        }
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
