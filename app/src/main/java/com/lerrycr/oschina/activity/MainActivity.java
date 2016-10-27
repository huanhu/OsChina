package com.lerrycr.oschina.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fl_main_container)
    FrameLayout mFlMainContainer;
    @Bind(R.id.rb_main_news)
    RadioButton mRbMainNews;
    @Bind(R.id.rb_main_chat)
    RadioButton mRbMainChat;
    @Bind(R.id.rb_main_popup)
    ImageButton mRbMainPopup;
    @Bind(R.id.rb_main_explore)
    RadioButton mRbMainExplore;
    @Bind(R.id.rb_main_me)
    RadioButton mRbMainMe;
    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    @Bind(R.id.tv_menu_quest)
    TextView mTvMenuQuest;
    @Bind(R.id.tv_menu_opensoft)
    TextView mTvMenuOpensoft;
    @Bind(R.id.tv_menu_blog)
    TextView mTvMenuBlog;
    @Bind(R.id.tv_menu_gitapp)
    TextView mTvMenuGitapp;
    @Bind(R.id.btn_menu_setting)
    Button mBtnMenuSetting;
    @Bind(R.id.btn_menu_exit)
    Button mBtnMenuExit;
    @Bind(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void initView() {
        initActionBar();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected Object getLayoutIdOrView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, 0, 0);
        mToggle.syncState();
        mDrawerlayout.addDrawerListener(mToggle);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.rb_main_popup, R.id.tv_menu_quest, R.id.tv_menu_opensoft, R.id.tv_menu_blog, R.id.tv_menu_gitapp, R.id.btn_menu_setting, R.id.btn_menu_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_main_popup:
                break;
            case R.id.tv_menu_quest:
                break;
            case R.id.tv_menu_opensoft:
                break;
            case R.id.tv_menu_blog:
                break;
            case R.id.tv_menu_gitapp:
                break;
            case R.id.btn_menu_setting:
                break;
            case R.id.btn_menu_exit:
                break;
        }
    }
}
