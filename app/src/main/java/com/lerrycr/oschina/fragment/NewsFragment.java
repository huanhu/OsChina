package com.lerrycr.oschina.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.View.PagerSlidingTabStrip;
import com.lerrycr.oschina.adapter.NewsFragmentAdpter;
import com.lerrycr.oschina.base.BaseFragment;
import com.lerrycr.oschina.fragment.NewsDetailFragment.BlogFragment;
import com.lerrycr.oschina.fragment.NewsDetailFragment.HotFragment;
import com.lerrycr.oschina.fragment.NewsDetailFragment.MessageFragment;
import com.lerrycr.oschina.fragment.NewsDetailFragment.RecommandFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Lerry on 2016/10/27.
 */

public class NewsFragment extends BaseFragment {

    @Bind(R.id.news_tabs)
    PagerSlidingTabStrip mNewsTabs;
    @Bind(R.id.news_viewpager)
    ViewPager mNewsViewpager;
    private ArrayList<BaseFragment> mFragments;
    private FragmentManager mFragmentManager;

    @Override
    protected void iniView() {
        mFragmentManager = ((FragmentActivity) mActivity).getSupportFragmentManager();
        initViewPagerAndTabs();
    }

    /**
     * 初始化viewpager和头部标签
     */
    private void initViewPagerAndTabs() {
        System.out.println("mNewsTabs=" + mNewsTabs);
        mFragments = new ArrayList<>();
        MessageFragment messageFragment = new MessageFragment();
        HotFragment hotFragment = new HotFragment();
        BlogFragment blogFragment = new BlogFragment();
        RecommandFragment recommandFragment = new RecommandFragment();
        mFragments.add(messageFragment);
        mFragments.add(hotFragment);
        mFragments.add(blogFragment);
        mFragments.add(recommandFragment);

        NewsFragmentAdpter adpter = new NewsFragmentAdpter(mFragmentManager, mFragments);
        mNewsViewpager.setAdapter(adpter);
        //让fragment一次性加载的数据页面
        mNewsViewpager.setOffscreenPageLimit(mFragments.size() - 1);
        mNewsTabs.setViewPager(mNewsViewpager);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

}
