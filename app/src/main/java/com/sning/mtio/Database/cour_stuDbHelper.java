package com.sning.mtio.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cour_stuDbHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "csdata.db";//用户数据库
    public final static int DATABASE_VERSION = 1;//版本号
    public final static String TABLE_NAME="cour_stuTable";

    public final static String COUR_STU_KEY1="stu_id";
    public final static String COUR_STU_KEY2="cour_id";
    public final static String COUR_STU_KEY3="score";
    public final static String COUR_STU_KEY4="term";

    public static String CREATE_TABLE ="create table "+TABLE_NAME+" ("+
            COUR_STU_KEY1+" varchar(20) not null,"+
            COUR_STU_KEY2+" varchar(20) not null,"+
            COUR_STU_KEY3+" double ,"+
            COUR_STU_KEY4+" int not null)";

    private Context myContext = null;

    public cour_stuDbHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public cour_stuDbHelper(Context context)
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
    //插入学生课程关系
    public int insertCourStu(String stu_id,String cour_id,double score,int term){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+
                        COUR_STU_KEY1+"=\""+stu_id+"\" and "+
                        COUR_STU_KEY2+"=\""+cour_id+"\" and "+
                        COUR_STU_KEY4+"="+term,
                null);

        if(cursor.moveToFirst()){
            return 0;//已创建关系
        }
        else{
            ContentValues values=new ContentValues();
            values.put(COUR_STU_KEY1,stu_id);
            values.put(COUR_STU_KEY2,cour_id);
            values.put(COUR_STU_KEY3,score);
            values.put(COUR_STU_KEY4,term);
            db.insert(TABLE_NAME,null,values);
            db.close();
            return 1;//插入成功
        }
    }
    // public Cursor selectAll(){
//         SQLiteDatabase db=this.getWritableDatabase();
//     Cursor cursor=db.rawQuery("select * from "+TABLE_NAME, null);
//     return cursor;
// }
    public String insertCourStu(String stu_id, course c, int term){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+
                        COUR_STU_KEY1+"=\""+stu_id+"\" and "+
                        COUR_STU_KEY2+"=\""+c.getId()+"\" and "+
                        COUR_STU_KEY4+"="+term,
                null);

        if(cursor.moveToFirst()){
            return "0";//已创建关系
        }
        else{
            courseDBHelper courDb=new courseDBHelper(myContext);
            idDbHelper idDb=new idDbHelper(myContext);
            c.setId(idDb.getID(2));
            courDb.insertCourse(c);
            ContentValues values=new ContentValues();
            values.put(COUR_STU_KEY1,stu_id);
            values.put(COUR_STU_KEY2,c.getId());
            values.put(COUR_STU_KEY3,-1);
            values.put(COUR_STU_KEY4,term);
            db.insert(TABLE_NAME,null,values);
            db.close();
            return c.getId();
        }
    }

    //获得某学期已获得学分及学期gpa
    public double[] findTermGC(String stu_id,int term){
        double gpa,cred,credGet;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+COUR_STU_KEY1
                +"=\""+stu_id+"\" and "+COUR_STU_KEY4+"="+term,null);//筛选学期选课数据
        courseDBHelper coudb=new courseDBHelper(myContext);
        gpa=0.0;cred=0.0;credGet=0.0;//初始化
        while(cursor.moveToNext()){
            String cour_id=cursor.getString(cursor.getColumnIndex(COUR_STU_KEY2));
            double score=cursor.getDouble(cursor.getColumnIndex(COUR_STU_KEY3));
            course c=coudb.findCourById(cour_id);
            double credit=c.getCredit();
            if(score>=0){
                if(score>=60)
                    credGet+=credit;
                cred+=credit;
                gpa+=scoreToGPA(score)*credit;
            }
        }
        gpa=gpa/cred;
        double [] result=new double[2];
        result[0]=gpa;
        result[1]=credGet;
        return result;
    }

    //获得全部已获得学分及学期gpa
    public double[] findAllGC(String stu_id){
        double gpa,cred,credGet;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+COUR_STU_KEY1
                +"=\""+stu_id+"\"",null);//筛选全部选课数据
        courseDBHelper coudb=new courseDBHelper(myContext);
        gpa=0.0;cred=0.0;credGet=0.0;//初始化
        while(cursor.moveToNext()){
            String cour_id=cursor.getString(cursor.getColumnIndex(COUR_STU_KEY2));
            double score=cursor.getDouble(cursor.getColumnIndex(COUR_STU_KEY3));
            course c=coudb.findCourById(cour_id);
            double credit=c.getCredit();
            double trgpa=0;
            if(score>=0){
                if(score>=60)
                    credGet+=credit;
                cred+=credit;
                trgpa=scoreToGPA(score);
                gpa+=trgpa*credit;
            }
        }
        gpa=gpa/cred;
        double [] result=new double[2];
        result[0]=gpa;
        result[1]=credGet;
        return result;
    }

    public double scoreToGPA(double score){
        double gpa=0.0;
        if(score<60)
            return 0;
        else{
            score*=100;
            switch((int)(score/1000)){
                case 6:{gpa=score/1000-5;break;}
                case 7:{gpa=score/1000-5;break;}
                case 8:{gpa=score/1000-5;break;}
                case 9:{gpa=score/1000-5;break;}
                case 10:{gpa=5;break;}
            }
            return gpa;
        }
    }
    //更新课程成绩
    public int updateCS_Score(String stu_id,String cour_id,double score,int term,double new_score){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+
                        COUR_STU_KEY1+"=\""+stu_id+"\" and "+
                        COUR_STU_KEY2+"=\""+cour_id+"\" and "+
                        COUR_STU_KEY4+"="+term,
                null);
        if(!cursor.moveToFirst()){
            return 0;//没有指定课程学生关系
        }
        else{
            ContentValues values=new ContentValues();
            values.put(COUR_STU_KEY3,new_score);
            int res=db.update(TABLE_NAME,values,COUR_STU_KEY1+"=? and "
                    +COUR_STU_KEY2+"=? and "
                    +COUR_STU_KEY4+"=?",new String[]{stu_id,cour_id,term+""});
            db.close();
            return res;//返回更新条数
        }
    }

    //删除选课
    public int delCS(String stu_id,String cour_id,int term){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+
                        COUR_STU_KEY1+"=\""+stu_id+"\" and "+
                        COUR_STU_KEY2+"=\""+cour_id+"\" and "+
                        COUR_STU_KEY4+"="+term,
                null);
        if(!cursor.moveToFirst()){
            return 0;//没有指定课程学生关系
        }
        else{
            int res=db.delete(TABLE_NAME,COUR_STU_KEY1+"=? and "
                    +COUR_STU_KEY2+"=? and "
                    +COUR_STU_KEY4+"=?",new String[]{stu_id,cour_id,term+""});
            db.close();
            return res;//返回更新条数
        }
    }

    public List<Map<String,Object>> findCourByStuId(String stu_id, int term){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+COUR_STU_KEY1
                +"=\""+stu_id+"\" and "+COUR_STU_KEY4+"="+term,null);
        courseDBHelper coudb=new courseDBHelper(myContext);
        while(cursor.moveToNext()){
            Map<String,Object> map=new HashMap<String,Object>();
            String cour_id=cursor.getString(cursor.getColumnIndex(COUR_STU_KEY2));
            double score=cursor.getDouble(cursor.getColumnIndex(COUR_STU_KEY3));
            course c=coudb.findCourById(cour_id);

            map.put("courName",c.getName());
            map.put("courId",c.getId());
            map.put("courTeacher",c.getTeacher());
            map.put("courCred",c.getCredit());
            map.put("courClassr",c.getClassroom());
            map.put("courS",c.getStartWeek());
            map.put("courE",c.getEndWeek());
            map.put("courTime",c.getTime());
            map.put("courSelf",c.getSelf());
            //map.put(COUR_STU_KEY3,score);

            list.add(map);
        }
        return list;
    }

    public List<Map<String,Object>> findScoreByStuId(String stu_id,int term){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+" where "+COUR_STU_KEY1
                +"=\""+stu_id+"\" and "+COUR_STU_KEY4+"="+term+" and score>-1",null);
        courseDBHelper coudb=new courseDBHelper(myContext);
        while(cursor.moveToNext()){
            Map<String,Object> map=new HashMap<String,Object>();
            String cour_id=cursor.getString(cursor.getColumnIndex(COUR_STU_KEY2));
            double score=cursor.getDouble(cursor.getColumnIndex(COUR_STU_KEY3));
            course c=coudb.findCourById(cour_id);
            map.put("courName",c.getName());
            map.put("courCred",c.getCredit());
            map.put("courTerm",term);
            map.put("type",c.getSelf());
            map.put(COUR_STU_KEY3,score);

            list.add(map);
        }
        return list;
    }

    public void delAll(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,COUR_STU_KEY4+">?",new String[]{"0"});
        db.close();
    }

}
