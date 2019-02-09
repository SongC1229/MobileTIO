package com.sning.mtio.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class idDbHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "iddata.db";//用户数据库
    public final static int DATABASE_VERSION = 1;//版本号
    public final static String TABLE_NAME="idTable";
    public final static String ID_KEY1="id";
    public final static String ID_KEY2="ste";

    public static String CREATE_TABLE ="create table "+TABLE_NAME+" ("+
            ID_KEY1+" varchar(20),"+//编号
            ID_KEY2+" varchar(20) primary key)";//属性（学生or课程）1为学生 2 为课程

    private Context myContext = null;

    public idDbHelper(Context context, String name,
                      SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public idDbHelper(Context context)
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

    public void InsertID(String id,int ste){//插入id
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(ste==1){

            values.put(ID_KEY1,id);
            values.put(ID_KEY2,"1");
        }
        else if(ste==2){

            values.put(ID_KEY1,id);
            values.put(ID_KEY2,"2");
        }
        else{
            values.put(ID_KEY1,id);
            values.put(ID_KEY2,"3");
        }
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


    public String getID(int ste){//获取学生或课程的id
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where ste=\""+ste+"\"",null);
        cursor.moveToFirst();
        String res=cursor.getString(cursor.getColumnIndex(ID_KEY1));
        int id_count=Integer.parseInt(res);
        id_count++;
        String new_id=id_count+"";
        ContentValues values=new ContentValues();
        values.put(ID_KEY1,new_id);
        db.update(TABLE_NAME,values,ID_KEY2+"=?",new String[]{ste+""});
        db.close();
        return res;
    }

    public void delAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"ste=? or ste=? or ste=?",new String[]{"1","2","3"});
        db.close();
    }

}
