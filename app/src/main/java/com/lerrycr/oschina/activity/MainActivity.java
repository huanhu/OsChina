package com.lerrycr.oschina.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.base.BaseActivity;
import com.lerrycr.oschina.constants.Constants;
import com.lerrycr.oschina.fragment.MenuAndMainFragment.MainFragment;
import com.lerrycr.oschina.fragment.MenuAndMainFragment.MenuFragment;
import com.lerrycr.oschina.utils.UiUtils;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.fl_main_container)
    FrameLayout mFlMainContainer;
    @Bind(R.id.fl_menu_container)
    FrameLayout mFlMenuContainer;
    @Bind(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    private FragmentManager mFragmentManager;
    private ActionBarDrawerToggle mToggle;
    private MainFragment mMainFragment;
    private MenuFragment mMenuFragment;
    //退出书架的变量保存
    private long exitTime = 0;

    @Override
    protected void initView() {
        mFragmentManager = getSupportFragmentManager();
        replaceMainAndMenu();
        initActionBar();
    }

    /**
     * 替换内容页和菜单页的布局为fragment
     */
    private void replaceMainAndMenu() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mMainFragment == null) {
            mMainFragment = new MainFragment();
        }
        transaction.replace(R.id.fl_main_container, mMainFragment, Constants.MAIN_FRAGMENT);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuFragment();
        }
        transaction.replace(R.id.fl_menu_container, mMenuFragment, Constants.MENU_FRAGMENT);
        //一定不要忘记提交
        transaction.commit();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scan:
                UiUtils.showToast("我是扫一扫");
                break;
        }
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //按两下按键退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
