package com.sning.mtio.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class courseDBHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "coursedata.db";//用户数据库
    public final static int DATABASE_VERSION = 1;//版本号
    public final static String TABLE_NAME="courseTable";
    public final static String COURSE_KEY1="id";
    public final static String COURSE_KEY2="name";
    public final static String COURSE_KEY3="teacher";
    public final static String COURSE_KEY4="credit";
    public final static String COURSE_KEY5="classroom";
    public final static String COURSE_KEY6="startweek";
    public final static String COURSE_KEY7="endweek";
    public final static String COURSE_KEY8="time";
    public final static String COURSE_KEY9="self";

    public static String CREATE_TABLE ="create table "+TABLE_NAME+" ("+
            COURSE_KEY1+" varchar(20) primary key,"+
            COURSE_KEY2+" varchar(20) not null,"+
            COURSE_KEY3+" varchar(15) not null,"+
            COURSE_KEY4+" double not null,"+
            COURSE_KEY5+" varchar(30) not null,"+
            COURSE_KEY6+" int not null,"+
            COURSE_KEY7+" int not null,"+
            COURSE_KEY8+" int not null,"+
            COURSE_KEY9+" int not null)";//1为自定义课程，
    // 0为大类必修，
    // -1为通识选修，
    // -2为个性课程，
    // -3为专业必修
    // -4为专业选修

    ////////////////缺一个自定义属性

    private Context myContext = null;

    public courseDBHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("test","c");
    }

    public courseDBHelper(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("UseDatabase", "创建用户数据库------------");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP table if exists "+TABLE_NAME);
        onCreate( arg0);
    }

    public void insertCourse(course c){//插入课程

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COURSE_KEY1,c.getId());
        values.put(COURSE_KEY2,c.getName());
        values.put(COURSE_KEY3,c.getTeacher());
        values.put(COURSE_KEY4,c.getCredit());
        values.put(COURSE_KEY5,c.getClassroom());
        values.put(COURSE_KEY6,c.getStartWeek());
        values.put(COURSE_KEY7,c.getEndWeek());
        values.put(COURSE_KEY8,c.getTime());
        values.put(COURSE_KEY9,c.getSelf());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updateCourse(course c){//修改课程
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put(COURSE_KEY1,c.getId());
        values.put(COURSE_KEY2,c.getName());
        values.put(COURSE_KEY3,c.getTeacher());
        // values.put(COURSE_KEY4,c.getCredit());
        values.put(COURSE_KEY5,c.getClassroom());
        values.put(COURSE_KEY6,c.getStartWeek());
        values.put(COURSE_KEY7,c.getEndWeek());
        values.put(COURSE_KEY8,c.getTime());
        int res=db.update(TABLE_NAME,values,COURSE_KEY1+"=?",new String[]{c.getId()});
        db.close();
        return res;
    }

    // public int delCourById(String id){//根据id删除课程
    //     SQLiteDatabase db=this.getWritableDatabase();
    //     int res=db.delete(TABLE_NAME,COURSE_KEY1+"=?",new String[]{id});
    //     db.close();
    //     return res;
    // }

    // public void delAll(){
    //     SQLiteDatabase db=this.getWritableDatabase();
    //     db.delete(TABLE_NAME,COURSE_KEY6+">?",new String[]{"0"});
    //     db.close();
    // }

    // public Cursor selectAll(){
    //     SQLiteDatabase db=this.getWritableDatabase();
    //     Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
    //     return cursor;
    // }

    public course findCourById(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where id=\""+id+"\"",null);
        cursor.moveToFirst();

        String a= cursor.getString(cursor.getColumnIndex(COURSE_KEY2));
        course c=new course(id,
                cursor.getString(cursor.getColumnIndex(COURSE_KEY2)),
                cursor.getString(cursor.getColumnIndex(COURSE_KEY3)),
                cursor.getDouble(cursor.getColumnIndex(COURSE_KEY4)),
                cursor.getString(cursor.getColumnIndex(COURSE_KEY5)),
                cursor.getInt(cursor.getColumnIndex(COURSE_KEY6)),
                cursor.getInt(cursor.getColumnIndex(COURSE_KEY7)),
                cursor.getInt(cursor.getColumnIndex(COURSE_KEY8)),
                cursor.getInt(cursor.getColumnIndex(COURSE_KEY9)));
        db.close();
        return c;
    }
}
