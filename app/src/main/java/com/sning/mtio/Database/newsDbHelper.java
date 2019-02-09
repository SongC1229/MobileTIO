package com.sning.mtio.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sning.mtio.NewsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/12/30.
 */

public class newsDbHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "newsdata.db";//用户数据库
    public final static int DATABASE_VERSION = 1;//版本号
    public final static String TABLE_NAME="newsTable";

    public final static String NEWS_KEY1="id";
    public final static String NEWS_KEY2="title";
    public final static String NEWS_KEY3="time";
    public final static String NEWS_KEY4="author";
    public final static String NEWS_KEY5="url";

    public static String CREATE_TABLE ="create table "+TABLE_NAME+" ("+
            NEWS_KEY1+" int not null,"+
            NEWS_KEY2+" varchar(100) not null,"+
            NEWS_KEY3+" varchar(30) not null,"+
            NEWS_KEY4+" varchar(20) not null,"+
            NEWS_KEY5+" varchar(150))";

    private Context myContext = null;

    public newsDbHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public newsDbHelper(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Log.i("UseDatabase", "创建用户数据库");
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP table if exists "+TABLE_NAME);
        onCreate( arg0);
    }

    public void insertNews(int id,String title,String time,String author,String url){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(NEWS_KEY1,id);
        values.put(NEWS_KEY2,title);
        values.put(NEWS_KEY3,time);
        values.put(NEWS_KEY4,author);
        values.put(NEWS_KEY5,url);

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<Map<String,Object>> getNews(String type){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where author=\""+type+"\"",null);
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>> ();
        while(cursor.moveToNext()){
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("author",cursor.getString(cursor.getColumnIndex(NEWS_KEY4)));
            String title=cursor.getString(cursor.getColumnIndex(NEWS_KEY2));
            if(title.length()>30)
                title=title.substring(0,28)+"...";
            map.put("title",title);
            map.put("url",cursor.getString(cursor.getColumnIndex(NEWS_KEY5)));
            map.put("time",cursor.getString(cursor.getColumnIndex(NEWS_KEY3)));

            list.add(map);
        }
        db.close();
        return list;
    }

    public Cursor selectAll(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public List<NewsInfo> getAllNews() {
        List<NewsInfo> newsInfoList = new ArrayList<>();
        Cursor cursor = selectAll();
        while (cursor.moveToNext()) {
            String author = cursor.getString(cursor.getColumnIndex(NEWS_KEY4));
            if (author.equals("新闻")) {
                String url = cursor.getString(cursor.getColumnIndex(NEWS_KEY5));
                String title = cursor.getString(cursor.getColumnIndex(NEWS_KEY2));
                String time = cursor.getString(cursor.getColumnIndex(NEWS_KEY3));
                newsInfoList.add(new NewsInfo(title,time,author,url));
            }
        }
        return newsInfoList;
    }

    public List<NewsInfo> getAllInfo() {
        List<NewsInfo> newsInfoList = new ArrayList<>();
        Cursor cursor = selectAll();
        while (cursor.moveToNext()) {
            String author = cursor.getString(cursor.getColumnIndex(NEWS_KEY4));
            if (author.equals("教务处")) {
                String url = cursor.getString(cursor.getColumnIndex(NEWS_KEY5));
                String title = cursor.getString(cursor.getColumnIndex(NEWS_KEY2));
                String time = cursor.getString(cursor.getColumnIndex(NEWS_KEY3));
                newsInfoList.add(new NewsInfo(title,time,author,url));
            }
        }
        return newsInfoList;
    }


    public NewsInfo lastNews() {
        Cursor cursor = selectAll();
        NewsInfo news = new NewsInfo();
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(NEWS_KEY4)).equals("教务处")) {
                String title = cursor.getString(cursor.getColumnIndex(NEWS_KEY2));
                String time = cursor.getString(cursor.getColumnIndex(NEWS_KEY3));
                String author = cursor.getString(cursor.getColumnIndex(NEWS_KEY4));
                String url = cursor.getString(cursor.getColumnIndex(NEWS_KEY5));
                news = new NewsInfo(title,time,author,url);
                break;
            }
        }
        return news;
    }

    public NewsInfo lastInfo() {
        Cursor cursor = selectAll();
        NewsInfo info = new NewsInfo();
        while (cursor.moveToNext()) {
            if (!cursor.getString(cursor.getColumnIndex(NEWS_KEY4)).equals("教务处")) {
                String title = cursor.getString(cursor.getColumnIndex(NEWS_KEY2));
                String time = cursor.getString(cursor.getColumnIndex(NEWS_KEY3));
                String author = cursor.getString(cursor.getColumnIndex(NEWS_KEY4));
                String url = cursor.getString(cursor.getColumnIndex(NEWS_KEY5));
                info = new NewsInfo(title,time,author,url);
                break;
            }
        }
        return info;
    }

}
