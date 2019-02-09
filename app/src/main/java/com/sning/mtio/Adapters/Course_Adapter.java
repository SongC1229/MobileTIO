package com.sning.mtio.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sning.mtio.Database.course;
import com.sning.mtio.Database.cour_stuDbHelper;
import com.sning.mtio.Database.courseDBHelper;
import com.sning.mtio.R;
import com.sning.mtio.Widget.Alter_Dialog;
import com.sning.mtio.fragments.Frag_Schedule;

public class Course_Adapter extends BaseAdapter {

    private Context mContext;

    private String[][] contents;

    private int rowTotal;

    private int columnTotal;

    private int positionTotal;
    public Course_Adapter(Context context) {
        this.mContext = context;
    }

    public int getCount() {
        return positionTotal;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        //求余得到二维索引
        int column = position % columnTotal;
        //求商得到二维索引
        int row = position / columnTotal;
        return contents[row][column];
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        int [] theme_a={Color.parseColor("#999c3da4"),Color.parseColor("#997f7428"),
                Color.parseColor("#9963769a"),Color.parseColor("#994382eb"),
                Color.parseColor("#99ff8b3a"),Color.parseColor("#99e059bf"),
                Color.parseColor("#9919caad"),Color.parseColor("#99f4606c"),
        };
        int [] theme_b={Color.parseColor("#CCa2b3cf"),Color.parseColor("#cccfcf91"),
                Color.parseColor("#ccd9cfc5"),Color.parseColor("#ccd8a374"),
                Color.parseColor("#ccefc8c9"),Color.parseColor("#ccc38eac"),
                Color.parseColor("#ccabd9c9"),Color.parseColor("#ccc8e4ef"),
        };

        if( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.s_grib_item, null);
        }
        TextView textView =convertView.findViewById(R.id.s_text);

        //动态设置shape 背景
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(10);
        gd.setColor(Color.parseColor("#00abd9c9"));
        textView.setBackground(gd);
        textView.setText((String)getItem(position));

        if(!getItem(position).equals("")){
            gd.setColor(Color.parseColor("#4Ac3b4d2"));

        }        //如果有课,那么添加数据
        if( Frag_Schedule.getpaint( position / columnTotal,position % columnTotal)) {
            //随机变换颜色
            int rand =(int)(0+Math.random()*(8));
            gd.setColor(theme_a[rand]);
            textView.setBackground(gd);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int row = position / columnTotal;
                int column = position % columnTotal;
                String[] data=Frag_Schedule.get_data(row,column);
                setcourse = new Alter_Dialog(mContext,R.style.s_nobackdialog, alter_listener,row,column,data);
                setcourse.show();
            }
        });

        return convertView;
    }

    private Alter_Dialog setcourse=null;
    private View.OnClickListener alter_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cour_stuDbHelper db=new cour_stuDbHelper(mContext);
            switch (v.getId()) {

                case R.id.s_btn_save_course:
                    String text_name = setcourse.text_name.getText().toString().trim();
                    String text_teacher = setcourse.text_teacher.getText().toString().trim();
                    String text_room = setcourse.text_room.getText().toString().trim();
                    String text_start=setcourse.text_start.getText().toString().trim();
                    String text_end=setcourse.text_end.getText().toString().trim();
                    boolean iscolor=false;
                    if (text_name.equals(""))
                        Toast.makeText(mContext, "课程名称为空", Toast.LENGTH_SHORT).show();
                    else iscolor=true;
                    if(text_teacher.equals(""))
                        Toast.makeText(mContext, "教师信息为空", Toast.LENGTH_SHORT).show();
                    if (text_room.equals(""))
                        Toast.makeText(mContext, "教室信息为空", Toast.LENGTH_SHORT).show();
                    else iscolor=true;
                    int sweek=Integer.parseInt(text_start);
                    int eweek=Integer.parseInt(text_end);
                    if(sweek>Frag_Schedule.getweek()||Frag_Schedule.getweek()>eweek){
                        iscolor=false;
                    }
                    if (!text_name.equals("")||!text_room.equals("")){
                        contents[setcourse.row][setcourse.col]=text_name+"\n"+text_room;
                    }
                    else contents[setcourse.row][setcourse.col]="";
                    String[] data={text_name,text_teacher,text_room,text_start,text_end};
                    Frag_Schedule.set_data(setcourse.row,setcourse.col,data);
                    Frag_Schedule.setpaint(setcourse.row,setcourse.col,iscolor);
                    setcourse.dismiss();
                    notifyDataSetChanged();
                    int s;
                    try {
                        s = Integer.parseInt(text_start);
                    }catch (Exception e){
                        s=1;
                    }

                    int e;
                    try {
                        e = Integer.parseInt(text_end);
                    }catch (Exception err){
                        e=20 ;
                    }
                    String id=Frag_Schedule.getId(setcourse.row,setcourse.col);
                    if(!id.equals("0")){
                        course temp=new course(id,text_name,text_teacher,1.0,text_room,s,e,(setcourse.col+1)*10+setcourse.row+1,1);
                        courseDBHelper cdb=new courseDBHelper(mContext);
                        Toast.makeText(mContext,"更新",Toast.LENGTH_SHORT).show();
                        cdb.updateCourse(temp);
                    }
                    else {
                        course temp=new course("",text_name,text_teacher,1.0,text_room,s,e,(setcourse.col+1)*10+setcourse.row+1,1);
                        String c_id=db.insertCourStu( "1000001",temp,4);
                        Frag_Schedule.setId(setcourse.row,setcourse.col,c_id);
                    }
                    break;

                case R.id.s_btn_delete_course:
                    String[] null_data={"","","","1","20"};
                    contents[setcourse.row][setcourse.col]="";
                    Frag_Schedule.set_data(setcourse.row,setcourse.col,null_data);
                    Frag_Schedule.setpaint(setcourse.row,setcourse.col,false);
                    setcourse.dismiss();
                    notifyDataSetChanged();
                    //通知数据库
                    db.delCS("1000001",Frag_Schedule.getId(setcourse.row,setcourse.col),4);
                    break;
                case R.id.s_btn_cancel:
                    setcourse.dismiss();
                    break;
            }
        }
    };

    /**
     * 设置内容、行数、列数
     */
    public void setContent(String[][] contents, int row, int column) {
        this.contents = contents;
        this.rowTotal = row;
        this.columnTotal = column;
        this.positionTotal = rowTotal * columnTotal;
    }



}
