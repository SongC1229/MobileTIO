package com.sning.mtio.Activities;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sning.mtio.R;
import com.sning.mtio.fragments.Frag_Grade;

public class GradeActivity extends AppCompatActivity {

    private TabLayout tabLayout = null;
    private ViewPager viewPager;
    private Fragment[] mFragmentArrays;
    private String[] mTabTitles ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_grade);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//API>21,设置状态栏颜色透明
            getWindow().setStatusBarColor(Color.parseColor("#994586ef"));
        }
        ImageView backimg=findViewById(R.id.s_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayout =findViewById(R.id.s_grade_tab);
        viewPager =findViewById(R.id.s_grade_page);
        initView();
    }
    private void initView() {
        int a=6,b=2015;
        mTabTitles=new String[a];
        mFragmentArrays=new Fragment[a];
        for(int i=0;i<a;i++) {
            mTabTitles[i] = String.valueOf(b + i / 2) + '-'+String.valueOf(b + i / 2+1) + '-' + String.valueOf(i % 2+1);
            mFragmentArrays[i] = Frag_Grade.newInstance(i+1);
        }
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        PagerAdapter pagerAdapter = new title_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tabLayout.setupWithViewPager(viewPager);
    }
    final class title_Adapter extends FragmentPagerAdapter {
        public title_Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];

        }
    }
}
