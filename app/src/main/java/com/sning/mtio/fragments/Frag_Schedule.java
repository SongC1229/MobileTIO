package com.sning.mtio.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sning.mtio.Adapters.Course_Adapter;
import com.sning.mtio.R;
import com.sning.mtio.Database.cour_stuDbHelper;

import java.util.List;
import java.util.Map;

public class Frag_Schedule extends Fragment implements View.OnClickListener{

    private static int currentWeek=1;
    private static Boolean [][] paintColor=new Boolean[5][7];

    public static Fragment newInstance(int currentWeek) {
        Frag_Schedule fragment = new Frag_Schedule();
        Bundle bundle = new Bundle();
        bundle.putInt("currentWeek", currentWeek);
        fragment.setArguments(bundle);
        return fragment;
    }

    private static String[][] course_id=new String[5][7];
    private static String[][] course_name=new String[5][7];
    private static String[][] course_room=new String [5][7];
    private static String[][] course_start=new String[5][7];
    private static String[][] course_end=new String [5][7];
    private static String[][] course_teacher=new String[5][7];
    private static String[][] course_self=new String[5][7];
    private String[][] contents = new String[5][7];

    private void init_data(){
        for(int i=0;i<7;i++){
            for(int j =0;j<5;j++){
                paintColor[j][i]=false;
                course_id[j][i]="0";
                course_name[j][i]="";
                course_room[j][i]="";
                course_start[j][i]="1";
                course_end[j][i]="20";
                course_teacher[j][i]="";
                contents[j][i]="";
                course_self[j][i]="1";
            }
        }
    }

    public static String[] get_data(int row, int column){
        String[] data={course_name[row][column],course_teacher[row][column],course_room[row][column], course_start[row][column], course_end[row][column], course_self[row][column]};
        return data;
    }
    public static String getId(int row,int column){
        return course_id[row][column];
    }
    public static void setId(int row,int column,String id){
        course_id[row][column]=id;
    }
    public static void set_data(int row,int column,String [] data){
        course_name[row][column]=data[0];course_teacher[row][column]=data[1];
        course_room[row][column]=data[2]; course_start[row][column]=data[3];
        course_end[row][column]=data[4];
    }

    public static Boolean getpaint(int row, int column){
        return paintColor[row][column];
    }
    public static void setpaint(int row, int column,Boolean bool){
         paintColor[row][column]=bool;
    }

    public static int getweek(){
        return currentWeek;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_course,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.currentWeek=getArguments().getInt("currentWeek");
        GridView schedule = view.findViewById(R.id.s_course_details);
        init_data();
        init_data_from_db(view.getContext());
        //创建Adapter

        Course_Adapter course_adapter = new Course_Adapter(view.getContext());
        course_adapter.setContent(contents, 5, 7);
        schedule.setAdapter(course_adapter);
    }


    private void init_data_from_db(Context ctx){
        cour_stuDbHelper db=new cour_stuDbHelper(ctx);
        List<Map<String,Object>> data=db.findCourByStuId("1000001",4);
        for(int count=0;count<data.size();count++){
            Map item=data.get(count);
            String id=item.get("courId").toString();
            String name=item.get("courName").toString();
            String teacher=item.get("courTeacher").toString();
            String classroom=item.get("courClassr").toString();
            String startweek=item.get("courS").toString();
            String endweek=item.get("courE").toString();
            String ifself=item.get("courSelf").toString();
            int week = Integer.parseInt(item.get("courTime").toString());

            int sweek=Integer.parseInt(startweek);
            int eweek=Integer.parseInt(endweek);
            Boolean piant=false;
            if(sweek<=this.currentWeek&&this.currentWeek<=eweek){
                piant=true;
            }
            int col;//天数，列值
            int row;//节数，行值
            if(week>99999){
                col=week/100000-1;
                row=week%100000/10000-1;
                week=week%10000;
                paintColor[row][col]=piant;
                course_id[row][col]=id;
                course_name[row][col]=name;
                course_room[row][col]=classroom;
                course_start[row][col]=startweek;
                course_end[row][col]=endweek;
                course_teacher[row][col]=teacher;
                course_self[row][col]=ifself;
                contents[row][col]=name+"\n"+classroom;
            }
            if(week>999){
                col=week/1000-1;
                row=week%1000/100-1;
                week=week%100;
                paintColor[row][col]=piant;
                course_id[row][col]=id;
                course_name[row][col]=name;
                course_room[row][col]=classroom;
                course_start[row][col]=startweek;
                course_end[row][col]=endweek;
                course_teacher[row][col]=teacher;
                course_self[row][col]=ifself;
                contents[row][col]=name+"\n"+classroom;
            }
            if(week>9){
                col=week/10-1;
                row=week%10-1;
                paintColor[row][col]=piant;
                course_id[row][col]=id;
                course_name[row][col]=name;
                course_room[row][col]=classroom;
                course_start[row][col]=startweek;
                course_end[row][col]=endweek;
                course_teacher[row][col]=teacher;
                course_self[row][col]=ifself;
                contents[row][col]=name+"\n"+classroom;
            }
        }

    }
    public void onClick(View view) {

    }


}
