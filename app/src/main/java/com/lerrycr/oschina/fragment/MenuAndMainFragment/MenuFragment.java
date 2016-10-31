package com.lerrycr.oschina.fragment.MenuAndMainFragment;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lerrycr.oschina.MyApp;
import com.lerrycr.oschina.R;
import com.lerrycr.oschina.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lerry on 2016/10/27.
 */

public class MenuFragment extends BaseFragment {

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

    @Override
    protected void iniView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_drawerlayout_menu;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_menu_quest, R.id.tv_menu_opensoft, R.id.tv_menu_blog, R.id.tv_menu_gitapp, R.id.btn_menu_setting, R.id.btn_menu_exit})
    public void onClick(View view) {
        switch (view.getId()) {
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
                //点击遍历arraylist
                for (Activity activity : MyApp.getActivities()) {
//                    关闭所有activity
                    activity.finish();
                }
                break;
        }
    }
}
