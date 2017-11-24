package com.example.kings.mid_term_project;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.kings.mid_term_project.Activities.MainActivity;
import com.example.kings.mid_term_project.DataBase.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kings on 2017/11/17.
 */

public class DataStorage {
    private int[] icon = {R.mipmap.zhugeliang, R.mipmap.liubei, R.mipmap.caocao, R.mipmap.zhouyu, R.mipmap.xiaoqiao};
    private String[] name = {"诸葛亮", "刘备", "曹操", "周瑜", "小乔"};
    private String[] gender = {"男", "男", "男", "男", "女"};
    private String[] birthday = {"公元234年12月25日", "公元234年12月25日", "公元234年12月25日", "公元234年12月25日", "公元234年12月25日"};
    private String[] hometown = {"中国", "中国", "中国", "中国", "中国"};
    private String[] workfor = {"蜀国", "蜀国", "吴国", "蜀国", "魏国"};
    private String[] type = {"史实人物", "虚构人物", "史实人物", "史实人物", "虚构人物"};
    private String[] introduction = {"人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边",
            "人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边",
            "人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边",
            "人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边",
            "人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边"};
    private List<Person> data;

    public DataStorage() {
        init();
    }

    private Resources getResources() {
        Resources mResources = null;
        mResources = getResources();
        return mResources;
    }

    private void init() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.resourcesInstance, icon[i]);
            Person temp = new Person(name[i], gender[i], type[i], birthday[i], introduction[i], bitmap);
            //temp.put("hometown", hometown[i]);
            //temp.put("workfor", workfor[i]);
            //"index", i);
            data.add(temp);
        }
    }

    public List<Person> getData() {
        return data;
    }

    public void removeFromData(int index) {
        data.remove(index);
    }

}
