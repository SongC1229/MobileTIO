package com.sning.mtio.Database;

public class course {

    private String id;
    private String name;
    private String teacher;
    private double credit;
    private String classroom;
    private int startWeek;
    private int endWeek;
    private int time;
    private int self;//1为自定义课程，
    // 0为大类必修，
    // -1为通识选修，
    // -2为个性课程，
    // -3为专业必修
    // -4为专业选修


    public course(String id,
                  String name,
                  String teacher,
                  double credit,
                  String classroom,
                  int startWeek,
                  int endWeek,
                  int time,
                  int self
    ){
        this.id=id;
        this.name=name;
        this.teacher=teacher;
        this.credit=credit;
        this.classroom=classroom;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.time=time;
        this.self=self;
    }

    public void setId(String value){id=value;}
    public void setName(String value){name=value;}
    public void setTeacher(String value){teacher=value;}
    public void setCredit(double value){credit=value;}
    public void setClassroom(String value){classroom=value;}
    public void setStartWeek(int value){ startWeek =value;}
    public void setEndWeek(int value){ endWeek =value;}
    public void setTime(int value){time=value;}
    public void setSelf(int value){self=value;}



    public String getId(){return id;}
    public String getName(){return name;}
    public String getTeacher(){return teacher;}
    public double getCredit(){return credit;}
    public String getClassroom(){return classroom;}
    public int getStartWeek(){return startWeek;}
    public int getEndWeek(){return endWeek;}
    public int getTime(){return time;}
    public int getSelf(){return self;}

}
