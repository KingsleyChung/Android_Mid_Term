package com.example.kings.mid_term_project;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import com.example.kings.mid_term_project.Activities.MainActivity;
import com.example.kings.mid_term_project.DataBase.DBOperate;
import com.example.kings.mid_term_project.DataBase.Person;
import java.util.ArrayList;

/**
 * Created by Kings on 2017/11/17.
 */

public class DataStorage {

    private static ArrayList<Person> data = null;
    private static DBOperate dbOperate;
    public DataStorage(Context context) {
        init(context);
    }

    private void initizateDB() {
        Person [] initPerson = new Person[10];
        initPerson[0] = new Person("曹操", "男", "史实人物", "155--220", "曹操是西园八校尉之一，曾只身行刺董卓，失败后和袁绍共同联合天下诸侯讨伐董卓，后独自发展自身势力，一生中先后战胜了袁术、吕布、张绣、袁绍、刘表、张鲁、马超等割据势力，统一了北方。但是在南下讨伐江东的战役中，曹操在赤壁惨败。后来在和蜀汉的汉中争夺战中，曹操再次无功而返。曹操一生未称帝，他病死后，曹丕继位后不久称帝，追封曹操为魏武皇帝。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.caocao));
        initPerson[1] = new Person("刘备", "男", "史实人物", "161--223", "刘备，蜀汉的开国皇帝，汉景帝之子中山靖王刘胜的后代。刘备少年孤贫，以贩鞋织草席为生。黄巾起义时，刘备与关羽、张飞桃园结义，成为异姓兄弟，一同剿除黄巾，有功，任安喜县尉，不久辞官；董卓乱政之际，刘备随公孙瓒讨伐董卓，三人在虎牢关战败吕布。后诸侯割据，刘备势力弱小，经常寄人篱下，先后投靠过公孙瓒、曹操、袁绍、刘表等人，几经波折，却仍无自己的地盘。赤壁之战前夕，刘备在荆州三顾茅庐，请诸葛亮出山辅助，在赤壁之战中，联合孙权打败曹操，奠定了三分天下的基础。刘备在诸葛亮的帮助下占领荆州，不久又进兵益州，夺取汉中，建立了横跨荆益两州的政权。后关羽战死，荆州被孙权夺取，刘备大怒，于称帝后伐吴，在夷陵之战中为陆逊用火攻打得大败，不久病逝于白帝城，临终托孤于诸葛亮。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.liubei));
        initPerson[2] = new Person("小喬", "女", "史实人物", "?--?", "庐江皖县桥国老次女，秀美绝伦，貌压群芳，又琴棋书画无所不通周瑜攻取皖城，迎娶小乔为妻。周郎小乔英雄美女、郎才女貌 ，被流传为千古佳话。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.xiaoqiao));
        initPerson[3] = new Person("周瑜", "男", "史实人物", "175--210", "偏将军、南郡太守。自幼与孙策交好，策离袁术讨江东，瑜引兵从之。为中郎将，孙策相待甚厚，又同娶二乔。策临终，嘱弟权曰：“外事不决，可问周瑜”。瑜奔丧还吴，与张昭共佐权，并荐鲁肃等，掌军政大事。赤壁战前，瑜自鄱阳归。力主战曹，后于群英会戏蒋干、怒打黄盖行诈降计、后火烧曹军，大败之。后下南郡与曹仁相持，中箭负伤，与诸葛亮较智斗，定假涂灭虢等计，皆为亮破，后气死于巴陵，年三十六岁。临终，上书荐鲁肃代其位，权为其素服吊丧。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.zhouyu));
        initPerson[4] = new Person("诸葛亮", "男", "史实人物", "181--234", "人称卧龙先生，有经天纬地之才，鬼神不测之机。刘皇叔三顾茅庐，遂允出山相助。曾舌战群儒、借东风、智算华容、三气周瑜，辅佐刘备于赤壁之战大败曹操，更取得荆州为基本。后奉命率军入川，于定军山智激老黄忠，斩杀夏侯渊，败走曹操，夺取汉中。刘备伐吴失败，受遗诏托孤，安居平五路，七纵平蛮，六出祁山，鞠躬尽瘁，死而后已。其手摇羽扇，运筹帷幄的潇洒形象，千百年来已成为人们心中“智慧”的代名词。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.zhugeliang));
        initPerson[5] = new Person("關羽", "男", "史实人物", "?--219", "因本处势豪倚势凌人，关羽杀之而逃难江湖。闻涿县招军破贼，特来应募。与刘备、张飞桃园结义，羽居其次。使八十二斤青龙偃月刀随刘备东征西讨。虎牢关温酒斩华雄，屯土山降汉不降曹。为报恩斩颜良、诛文丑，解曹操白马之围。后得知刘备音信，过五关斩六将，千里寻兄。刘备平定益州后，封关羽为五虎大将之首，督荆州事。羽起军攻曹，放水淹七军，威震华夏。围樊城右臂中箭，幸得华佗医治，刮骨疗伤。但未曾提防东吴袭荆州，关羽父子败走麦城，突围中被捕，不屈遭害。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.guanyu));
        initPerson[6] = new Person("貂蟬", "女", "虚构人物", "?--?", "舍身报国的可敬女子，她为了挽救天下黎民，为了推翻权臣董卓的荒淫统治，受王允所托，上演了可歌可泣的连环计（连环美人计），周旋于两个男人之间，成功的离间了董卓和吕布，最终吕布将董卓杀死，结束了董卓专权的黑暗时期。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.diaochan));
        initPerson[7] = new Person("大喬", "女", "史实人物", "?--?", "江东乔国老有二女，大乔和小乔。大乔有沉鱼落雁之资，倾国倾城之容。孙策征讨江东，攻取皖城，娶大乔为妻。自古美女配英雄，伯符大乔堪绝配。曹操赤壁鏖兵，虎视江东，曾有揽二乔娱暮年，还足平生之愿。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.daqiao));
        initPerson[8] = new Person("孫權", "男", "史实人物", "182--252", "孙权19岁就继承了其兄孙策之位，力据江东，击败了黄祖。后东吴联合刘备，在赤壁大战击溃了曹操军。东吴后来又和曹操军在合肥附近鏖战，并从刘备手中夺回荆州、杀死关羽、大破刘备的讨伐军。曹丕称帝后孙权先向北方称臣，后自己建吴称帝，迁都建业。\n", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.sunqun));
        initPerson[9] = new Person("安阳公主", "女", "史实人物", "?--?", "虎贲中郎将荀恽妻，曹操之女。献帝建安中嫁给荀恽，后称安阳公主。", BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.anyang));
        for (int i = 0; i < 10; ++i)
            addPerson(initPerson[i]);
    }

    private void init(Context context) {
        if (data == null) {
            data = new ArrayList<Person>();
            dbOperate = new DBOperate(context);
            data = dbOperate.returnAll();
            if(data.size() == 0) {
                initizateDB();
            }
        }
    }

    public boolean addPerson(Person person) {
        data.add(person);
        //return true;
        return dbOperate.insertOne(person);
    }

    public int deletePerson(String name) {
        for (int i = 0; i < data.size();++i) {
            if (data.get(i).getName().equals(name)) {
                data.remove(i);
            }
        }
        //return 1;
        return dbOperate.deleteOne(name);
    }

    public boolean deleteSomePerson(ArrayList<String> name) {
        for (int i = 0; i< name.size(); ++i)
            if(deletePerson(name.get(i)) != 1) {
                return false;
            }
        return true;
    }

    public int updatePerson(int index, String originalName, Person person) {
        data.get(index).setName(person.getName());
        data.get(index).setBitmap(person.getBitmap());
        data.get(index).setCategory(person.getCategory());
        data.get(index).setDecription(person.getDecription());
        data.get(index).setSex(person.getSex());
        data.get(index).setTime(person.getTime());
        return dbOperate.updateOne(originalName, person);
    }

    public ArrayList<Person> searchPerson(String name) {
        return dbOperate.searchMany(name);

    }

    public static ArrayList<Person> getData() {
        return data;
    }

    public static Bitmap getBitmapFromName(String name) {
        for (int i = 0; i < data.size();++i) {
            if(data.get(i).getName().equals(name))
                return data.get(i).getBitmap();
        }
        return null;
    }

}

