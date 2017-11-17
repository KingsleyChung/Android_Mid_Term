package com.example.kings.mid_term_project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private String[] introduction = {"人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边",
            "人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边",
            "人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边",
            "人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边",
            "人称卧龙先生，曾被三顾茅庐，是个法师，使用技能会有多个魔法球围绕在身边"};
    private List<Map<String, Object>> data;

    DataStorage() {
        init();
    }

    private void init() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("icon", icon[i]);
            temp.put("name", name[i]);
            temp.put("gender", gender[i]);
            temp.put("birthday", birthday[i]);
            temp.put("hometown", hometown[i]);
            temp.put("workfor", workfor[i]);
            temp.put("introduction", introduction[i]);
            temp.put("index", i);
            data.add(temp);
        }
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void removeFromData(int index) {
        data.remove(index);
    }

}
