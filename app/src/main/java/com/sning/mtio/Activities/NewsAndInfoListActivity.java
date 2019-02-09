package com.sning.mtio.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sning.mtio.R;
import com.sning.mtio.fragments.LoginFragment;
import com.sning.mtio.fragments.NewsInfoFragment;
import com.sning.mtio.fragments.TitleFragmentMoreAndUser;

public class NewsAndInfoListActivity extends AppCompatActivity implements LoginFragment.CallBackValue {


    public static int TYPE_NEWS_LIST = 1;
    public static int TYPE_INFO_LIST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_and_info_list);

        Intent intent = getIntent();
        int t = intent.getIntExtra("TYPE",-1);
        Log.d("NewsAndInfoActivity--",String.valueOf(t));
        if (t == TYPE_NEWS_LIST) {
            TitleFragmentMoreAndUser fragmentTitle = TitleFragmentMoreAndUser.newInstance(3);
            replaceTitleFragment(fragmentTitle);
            NewsInfoFragment fragmentContent = NewsInfoFragment.newInstance(NewsInfoFragment.TYPE_NEWS);
            replaceContentFragment(fragmentContent);
        } else if (t == TYPE_INFO_LIST) {
            TitleFragmentMoreAndUser fragmentTitle = TitleFragmentMoreAndUser.newInstance(4);
            replaceTitleFragment(fragmentTitle);
            NewsInfoFragment fragmentContent = NewsInfoFragment.newInstance(NewsInfoFragment.TYPE_INFO);
            replaceContentFragment(fragmentContent);
        }

    }

    private void replaceTitleFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.z_fl_news_info_title_bar,fragment);
        ft.commit();
    }

    private void replaceContentFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.z_fl_news_info_fragment,fragment);
        ft.commit();
    }

    @Override
    public void SendMessageValue(String value) {
        Intent intent = new Intent(this,NewsWebActivity.class);
        intent.putExtra("URL",value);
        startActivity(intent);
    }
}
