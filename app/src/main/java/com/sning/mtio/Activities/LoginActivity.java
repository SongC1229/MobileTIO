package com.sning.mtio.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import com.sning.mtio.BaseSystem.BaseActivity;
import com.sning.mtio.Database.Student;
import com.sning.mtio.R;
import com.sning.mtio.fragments.LoginFragment;
import com.sning.mtio.fragments.SignUpFragment;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends BaseActivity implements LoginFragment.CallBackValue {

    private static boolean isFirstLogin = true;
    public static Student currentUser;

    public static Student getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Student currentUser) {
        LoginActivity.currentUser = currentUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inDB();
        if (isFirstLogin) {
            //如果是第一次登陆，则展示登陆界面
            isFirstLogin = false;
            LoginFragment loginFragment = new LoginFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.z_fl_login,loginFragment).commit();
        } else {                        //已经登陆过，则直接进入主界面
            Intent intent = new Intent(this,MainActivity.class);
            startActivityForResult(intent,1);
        }
    }



    /**
     * 作用：当用户注销登陆时退回到登陆界面
     * 作者：z
     * 最后修改时间：12.28 16：20
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:{
                if (resultCode == RESULT_OK) {//用户注销登陆,并设置为第一次登陆
                    isFirstLogin = true;
                    currentUser = null;
                    //EditText name = findViewById(R.id.z_input_name_login);
                    this.recreate();
                    //setContentView(R.layout.activity_login);
                    //initView();
                }
            }//end of case
        }//end of switch
    }//end of function


    @Override
    public void SendMessageValue(String value) {
        if (value.equals("LOGIN_SUCCEED")) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivityForResult(intent,1);
        } else if (value.equals("GOTO_SIGN_UP")) {
            SignUpFragment fragment = new SignUpFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.z_fl_login,fragment).commit();
        } else if (value.equals("SIGN_UP_SUCCEED")) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivityForResult(intent,1);
        } else if (value.equals("CANCEL_SIGN_UP")) {
            LoginFragment loginFragment = new LoginFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.z_fl_login,loginFragment).commit();
        }
    }

    void inDB() {
        String dbDirPath ="/data/data/com.sning.mtio/databases";
        File dbDir = new File(dbDirPath);
        if (!dbDir.exists()) { // 如果不存在该目录则创建
            dbDir.mkdir();
            // 打开静态数据库文件的输入流
            try {
                InputStream is1 = this.getResources().openRawResource(R.raw.iddata);
                FileOutputStream os1 = new FileOutputStream(dbDirPath + "/iddata.db");

                InputStream is2 = this.getResources().openRawResource(R.raw.userdata);
                FileOutputStream os2 = new FileOutputStream(dbDirPath + "/userdata.db");

                InputStream is3 = this.getResources().openRawResource(R.raw.coursedata);
                FileOutputStream os3 = new FileOutputStream(dbDirPath + "/coursedata.db");

                InputStream is4 = this.getResources().openRawResource(R.raw.csdata);
                FileOutputStream os4 = new FileOutputStream(dbDirPath + "/csdata.db");

                InputStream is5 = this.getResources().openRawResource(R.raw.newsdata);
                FileOutputStream os5 = new FileOutputStream(dbDirPath + "/newsdata.db");

                InputStream is6 = this.getResources().openRawResource(R.raw.memodata);
                FileOutputStream os6 = new FileOutputStream(dbDirPath + "/memodata.db");
                byte[] buffer = new byte[1024];
                int count = 0;
                // 将静态数据库文件拷贝到目的地
                while ((count = is1.read(buffer)) > 0) {
                    os1.write(buffer, 0, count);
                }
                while ((count = is2.read(buffer)) > 0) {
                    os2.write(buffer, 0, count);
                }
                while ((count = is3.read(buffer)) > 0) {
                    os3.write(buffer, 0, count);
                }
                while ((count = is4.read(buffer)) > 0) {
                    os4.write(buffer, 0, count);
                }
                while ((count = is5.read(buffer)) > 0) {
                    os5.write(buffer, 0, count);
                }
                while ((count = is6.read(buffer)) > 0) {
                    os6.write(buffer, 0, count);
                }
                is1.close();is2.close();is3.close();is4.close();is5.close();is6.close();
                os1.close();os2.close();os3.close();os4.close();os5.close();os6.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("LoginActivity--","数据库创建完成");
    }


}//end of class
