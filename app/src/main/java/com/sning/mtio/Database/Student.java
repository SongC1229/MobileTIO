package com.sning.mtio.Database;

/**
 * Created by apple on 2019/1/2.
 */

public class Student {
    private String id;
    private String name;
    private String pwd;
    private String mail;
    private String phone;
    private String major;
    private int sex;
    private String clas;
    private int year;

    public Student(String id, String name, String pwd, String mail, String phone, String major, int sex, String clas, int year) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.mail = mail;
        this.phone = phone;
        this.major = major;
        this.sex = sex;
        this.clas = clas;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getMajor() {
        return major;
    }

    public int getSex() {
        return sex;
    }

    public String getClas() {
        return clas;
    }

    public int getYear() {
        return year;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
