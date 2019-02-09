package com.sning.mtio.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
/**
 * Created by apple on 2018/12/30.
 */

public class memoDbHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "memodata.db";//用户数据库
    public final static int DATABASE_VERSION = 1;//版本号
    public final static String TABLE_NAME="memoTable";

    public final static String MEMO_KEY1="id";
    public final static String MEMO_KEY2="user_id";
    public final static String MEMO_KEY3="time";
    public final static String MEMO_KEY4="title";
    public final static String MEMO_KEY5="content";

    public static String CREATE_TABLE ="create table "+TABLE_NAME+" ("+
            MEMO_KEY1+" int not null,"+
            MEMO_KEY2+" varchar(20) not null,"+
            MEMO_KEY3+" varchar(30) not null,"+
            MEMO_KEY4+" varchar(50) not null,"+
            MEMO_KEY5+" varchar(400))";

    private Context myContext = null;

    public memoDbHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public memoDbHelper(Context context)
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
    //插入memo
    public int insertMemo(String user_id,String title,String content){
        SQLiteDatabase db=this.getWritableDatabase();

        SimpleDateFormat format = new SimpleDateFormat("MM.dd HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));// 中国北京时间，东八区

        ContentValues values=new ContentValues();

        idDbHelper idDb=new idDbHelper(myContext);

        values.put(MEMO_KEY1,Integer.parseInt(idDb.getID(3)));
        values.put(MEMO_KEY2,user_id);
        values.put(MEMO_KEY3,format.format(new Date()));
        values.put(MEMO_KEY4,title);
        values.put(MEMO_KEY5,content);

        db.insert(TABLE_NAME,null,values);
        db.close();
        return 1;//插入成功
    }

    public int insertMemo(int id,String user_id,String title,String content){
        SQLiteDatabase db=this.getWritableDatabase();

        SimpleDateFormat format = new SimpleDateFormat("MM.dd HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));// 中国北京时间，东八区

        ContentValues values=new ContentValues();

       // idDbHelper idDb=new idDbHelper(myContext);

        values.put(MEMO_KEY1,id);
        values.put(MEMO_KEY2,user_id);
        values.put(MEMO_KEY3,format.format(new Date()));
        values.put(MEMO_KEY4,title);
        values.put(MEMO_KEY5,content);

        db.insert(TABLE_NAME,null,values);
        db.close();
        return 1;//插入成功
    }


    public List<Map<String,Object>> findMemoByUserId(String user_id){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+MEMO_KEY2
                +"=\""+user_id+"\"",null);
        while(cursor.moveToNext()){
            Map<String,Object> map=new HashMap<String,Object>();
            int memo_id=cursor.getInt(cursor.getColumnIndex(MEMO_KEY1));
            String time=cursor.getString(cursor.getColumnIndex(MEMO_KEY3));
            String getTitle=cursor.getString(cursor.getColumnIndex(MEMO_KEY4));
            String getCont=cursor.getString(cursor.getColumnIndex(MEMO_KEY5));

            map.put("memo_id",memo_id);
            map.put("time",time);
            map.put("getTitle",getTitle);
            map.put("getCont",getCont);

            list.add(map);
        }
        return list;
    }

    public void delAll(){//删除全部memo
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"id>?",new String[]{"0"});
        db.close();
    }

    public void delById(int id){//根据id删除memo
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{id+""});
        db.close();
    }

    public void delByUserId(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"user_id=?",new String[]{id});
        db.close();
    }

    public Cursor selectAll(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public void updateMemo(int id,String title,String content){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(MEMO_KEY4,title);
        value.put(MEMO_KEY5,content);
        db.update(TABLE_NAME,value,"id=?",new String[]{id+""});
        db.close();
    }
}
