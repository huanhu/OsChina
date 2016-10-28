package com.lerrycr.oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lerrycr.oschina.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Lerry on 2016/10/28.
 */

public class NewsFragmentAdpter extends FragmentPagerAdapter {


    private ArrayList<BaseFragment> mFragments;

    public NewsFragmentAdpter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * 这个返回数据
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "你好";
    }
}
