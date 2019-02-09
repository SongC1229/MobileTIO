package com.sning.mtio.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.sning.mtio.BaseSystem.BaseActivity;
import com.sning.mtio.Database.newsDbHelper;
import com.sning.mtio.R;
import com.sning.mtio.fragments.Frag_Schedule;
import com.sning.mtio.fragments.HomeFragment;
import com.sning.mtio.fragments.LoginFragment;
import com.sning.mtio.fragments.TitleFragmentCourseTable;
import com.sning.mtio.fragments.TitleFragmentMoreAndUser;
import com.sning.mtio.fragments.UserFragment;


public class MainActivity extends BaseActivity implements LoginFragment.CallBackValue,BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar navigationBar;
    private com.sning.mtio.Database.newsDbHelper newsDbHelper;
    private int week = -1;


    private TitleFragmentMoreAndUser moreAndUser;
    private TitleFragmentCourseTable courseTable;
    private TitleFragmentMoreAndUser moreAndUser2;
    private Fragment home;
    private Fragment user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsDbHelper = new newsDbHelper(this);
        initView();

    }


    private void initView() {
        navigationBar = findViewById(R.id.z_bottom_navigation);
        navigationBar.setActiveColor(R.color.colorPrimaryDark);
        navigationBar.addItem(new BottomNavigationItem(R.drawable.home_tab,"首页"));
        navigationBar.addItem(new BottomNavigationItem(R.drawable.course_table_tab,"课程表"));
        navigationBar.addItem(new BottomNavigationItem(R.drawable.user_tab,"我"));

        navigationBar.setFirstSelectedPosition(0);
        navigationBar.initialise();
        navigationBar.setTabSelectedListener(this);
        //从数据库获取当前周数，再加载到

        replaceTitleFragment(TitleFragmentMoreAndUser.newInstance(0));
        replaceContentFragment(HomeFragment.newInstance());

    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:{
                if (moreAndUser==null)
                    moreAndUser = TitleFragmentMoreAndUser.newInstance(0);
                if (home==null)
                    home= HomeFragment.newInstance();
                replaceTitleFragment(moreAndUser);
                replaceContentFragment(home);
                break;
            }
            case 1:{
                if(courseTable==null)
                    courseTable= TitleFragmentCourseTable.newInstance();
                replaceTitleFragment(courseTable);
                Fragment sf;
                if (week > 0)
                    sf = Frag_Schedule.newInstance(week);
                else
                    sf = Frag_Schedule.newInstance(1);
                replaceContentFragment(sf);
                break;
            }
            case 2:{
                if(moreAndUser2==null)
                    moreAndUser2=TitleFragmentMoreAndUser.newInstance(2);
                replaceTitleFragment(moreAndUser2);
                if(user==null)
                    user=UserFragment.newInstance();
                replaceContentFragment(user);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    private void replaceTitleFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.z_fl_title_bar,fragment).commit();
    }

    private void replaceContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.z_fl_content,fragment).commit();
    }

    @Override
    public void SendMessageValue(String value) {
        int type = Integer.valueOf(value);
        if (type > 10) {
            week = type - 10;
            Fragment sf;
            if (week > 0)
                sf = Frag_Schedule.newInstance(week);
            else
                sf = Frag_Schedule.newInstance(1);
            replaceContentFragment(sf);
        } else {
            switch (type) {
                case 1:{
                    Intent intent = new Intent(this,GradeActivity.class);
                    startActivity(intent);
                    break;
                }
                case 2:{
                    Intent intent = new Intent(this,NewsAndInfoListActivity.class);
                    intent.putExtra("TYPE",NewsAndInfoListActivity.TYPE_NEWS_LIST);
                    startActivity(intent);
                    break;
                }
                case 3:{
                    Intent intent = new Intent(this,NewsWebActivity.class);
                    intent.putExtra("URL",newsDbHelper.lastNews().getUrl());
                    startActivity(intent);
                    break;
                }
                case 4:{
                    Intent intent = new Intent(this,NewsAndInfoListActivity.class);
                    intent.putExtra("TYPE",NewsAndInfoListActivity.TYPE_INFO_LIST);
                    startActivity(intent);
                    break;
                }
                case 5:{
                    Intent intent = new Intent(this,NewsWebActivity.class);
                    intent.putExtra("URL",newsDbHelper.lastInfo().getUrl());
                    startActivity(intent);
                    break;
                }
                case 6:{
                    Intent intent = new Intent(this,SettingActivity.class);
                    startActivityForResult(intent,6);
                    break;
                }
                default:
                    break;
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 6:{
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        }
    }
}//end of class
