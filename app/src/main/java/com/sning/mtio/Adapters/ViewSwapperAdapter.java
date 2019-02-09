package com.sning.mtio.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sning.mtio.fragments.Frag_Schedule;
import com.sning.mtio.fragments.UserFragment;
import com.sning.mtio.fragments.HomeFragment;

import org.buffer.adaptablebottomnavigation.adapter.FragmentStateAdapter;


public class ViewSwapperAdapter extends FragmentStateAdapter {

    private static final int INDEX_HOME_PAGE = 0;
    private static final int INDEX_COURSE_TABLE_PAGE = 1;
    private static final int INDEX_USER_PAGE = 2;

    private int week = -1;

    public ViewSwapperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case INDEX_HOME_PAGE:
                return HomeFragment.newInstance();
            case INDEX_COURSE_TABLE_PAGE:
                if (week < 1) {
                    return Frag_Schedule.newInstance(1);
                } else {
                    return Frag_Schedule.newInstance(week);
                }

            case INDEX_USER_PAGE:
                return UserFragment.newInstance();
        }
        return HomeFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
