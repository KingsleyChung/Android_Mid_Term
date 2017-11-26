package com.example.kings.mid_term_project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by weimumu on 2017/11/21.
 */

public class DBOperate {
    private MySQLiteHelper dbhelper;
    private Context context;

    //要操作数据库操作实例首先得得到数据库操作实例
    public DBOperate(Context context) {
        this.context=context;
        this.dbhelper = MySQLiteHelper.getInstance(context);
    }

    //return all person in the database
    public ArrayList<Person> returnAll() {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ArrayList<Person> result = new ArrayList<>();
        Cursor cur = db.rawQuery("SELECT * FROM Person", null);
        while(cur.moveToNext()) {
            String name = cur.getString(cur.getColumnIndex("name"));
            String sex = cur.getString(cur.getColumnIndex("sex"));
            String category = cur.getString(cur.getColumnIndex("category"));
            String time = cur.getString(cur.getColumnIndex("time"));
            String description = cur.getString(cur.getColumnIndex("description"));
            byte[] imgData = cur.getBlob(cur.getColumnIndex("avatar"));
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            Person person = new Person(name, sex, category, time, description, imageBitmap);
            result.add(person);
        }
        db.close();
        return result;
    }

    public void deleteAll() {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.execSQL("DELETE FROM Person");
        db.close();
    }

    //insert one person
    public boolean insertOne(Person person) {
        ArrayList<Person> result = returnAll();
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).getName().equals(person.getName())) {
                return false;
            }
        }
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cValue = new ContentValues();
        cValue.put("name", person.getName());
        cValue.put("sex", person.getSex());
        cValue.put("category", person.getCategory());
        cValue.put("time", person.getTime());
        cValue.put("description", person.getDecription());
        cValue.put("avatar", bitmabToBytes(person.getBitmap()));
        db.insert("Person", null, cValue);
        db.close();
        return true;
    }

    // delete one person
    public int deleteOne(String name) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = "name=?";
        String[] whereArgs = {name};
        int num = db.delete("Person", whereClause, whereArgs);
        db.close();
        return num;
    }

    // update one person
    public int updateOne(String name, Person person) {
        ContentValues cValue = new ContentValues();
        cValue.put("name", person.getName());
        cValue.put("sex", person.getSex());
        cValue.put("category", person.getCategory());
        cValue.put("time", person.getTime());
        cValue.put("description", person.getDecription());
        cValue.put("avatar", bitmabToBytes(person.getBitmap()));
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String whereClause = "name=?";
        String[] whereArgs = {name};
        return db.update("Person", cValue, whereClause, whereArgs);
    }

    //模糊搜索  使用名字作为查询  匹配原则: 子字符串匹配模式
    public ArrayList<Person> searchMany(String search) {
        ArrayList<Person> result = new ArrayList<>();
        String sql = "SELECT * FROM Person WHERE name LIKE '%" + search + "%'";
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cur = db.rawQuery(sql, null);
        while(cur.moveToNext()) {
            String name = cur.getString(cur.getColumnIndex("name"));
            String sex = cur.getString(cur.getColumnIndex("sex"));
            String category = cur.getString(cur.getColumnIndex("category"));
            String time = cur.getString(cur.getColumnIndex("time"));
            String description = cur.getString(cur.getColumnIndex("description"));
            byte[] imgData = cur.getBlob(cur.getColumnIndex("avatar"));
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            Person person = new Person(name, sex, category, time, description, imageBitmap);
            result.add(person);
        }
        db.close();
        return result;
    }


    //图片转为二进制数据
    public byte[] bitmabToBytes(Bitmap bitmap){
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
        //将字节数组输出流转化为字节数组byte[]
        byte[] imagedata = baos.toByteArray();
        return imagedata;
    }

}
