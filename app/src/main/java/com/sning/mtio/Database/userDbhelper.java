package com.sning.mtio.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 2018/12/28.
 */

public class userDbhelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "userdata.db";//用户数据库
    public final static int DATABASE_VERSION = 1;//版本号

    // 用于创建表的SQL语句
    public static String CREATE_TABLE = "create table "+ "userTable" +" (" +
            "id" + " varchar(20) primary key, " +//学号
            "name"+ " vachar(30), " +//姓名
            "pwd" + " vachar(30), " +//密码
            "mail" + " varchar(50),"+//邮箱
            "phone"+" varchar(20) ,"+//电话
            "sex"+" int,"+//性别
            "year"+" int,"+//年级
            "marjor"+" varchar(50),"+//专业
            "class"+" varchar(50))"; //班级

    private Context myContext = null;

    public userDbhelper(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public userDbhelper(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP table if exists "+"userTable");
        onCreate( arg0);
    }


    public int insertUser(String id,String name,String pwd){

        Student res=selectById(id);
        if(res==null)
            return -1;//id不存在
        if(res.getName().equals(name)){
            ContentValues cV = new ContentValues();
            cV.put("pwd", pwd);
            SQLiteDatabase db = this.getWritableDatabase();
            db.update("userTable", cV,"id=?",new String[]{id});
            db.close();
            return 1;//插入成功
        }
        else{
            return 0;//用户姓名不匹配
        }
    }



    public Student selectById(String id){//查询
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userTable where id=\""+id+"\"", null);
        if(cursor.moveToNext()) {
            Student stu = new Student(
                    cursor.getString(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("pwd")),
                    cursor.getString(cursor.getColumnIndex("mail")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("marjor")),
                    cursor.getInt(cursor.getColumnIndex("sex")),
                    cursor.getString(cursor.getColumnIndex("class")),
                    cursor.getInt(cursor.getColumnIndex("year"))

            );
            db.close();
            return stu;
        }
        else {
            db.close();
            return null;
        }
    }
    public int updateStu(Student s){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",s.getName());
        values.put("mail",s.getMail());
        values.put("phone",s.getPhone());
        values.put("marjor",s.getMajor());
        values.put("sex",s.getSex());
        values.put("year",s.getYear());
        values.put("class",s.getClas());
        int res=db.update("userTable",values,"id=?",new String[]{s.getId()});
        db.close();
        return res;
    }

    public int judgeUser(String id,String pwd){//身份认证
        Student stu=selectById(id);
        if(stu==null){
            return -1;//用户不存在
        }
        else{
            if(pwd.equals(stu.getPwd())){
                return 1;//登录成功
            }
            else
                return 0;//密码错误
        }
    }

    public int chagePwd(String id,String old_pwd,String new_pwd){//修改密码
        int res1=judgeUser(id,old_pwd);
        if(res1!=1)
            return res1;
        else{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("pwd",new_pwd);
            return db.update("userTable",values,"id=?",new String[]{id});
        }
    }
}
