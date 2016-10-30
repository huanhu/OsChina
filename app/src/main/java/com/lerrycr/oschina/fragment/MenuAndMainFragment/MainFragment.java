package com.lerrycr.oschina.fragment.MenuAndMainFragment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.View.ButtomDialog;
import com.lerrycr.oschina.activity.SaveIpActivity;
import com.lerrycr.oschina.base.BaseFragment;
import com.lerrycr.oschina.constants.Constants;
import com.lerrycr.oschina.fragment.ChatFragment;
import com.lerrycr.oschina.fragment.ExploreFragment;
import com.lerrycr.oschina.fragment.MeFragment;
import com.lerrycr.oschina.fragment.NewsFragment;
import com.lerrycr.oschina.utils.UiUtils;

import butterknife.Bind;

/**
 * Created by Lerry on 2016/10/27.
 */

public class MainFragment extends BaseFragment {

    @Bind(R.id.rb_main_news)
    RadioButton mRbMainNews;
    @Bind(R.id.rb_main_chat)
    RadioButton mRbMainChat;
    @Bind(R.id.ib_main_popup)
    ImageButton mibMainPopup;
    @Bind(R.id.rb_main_explore)
    RadioButton mRbMainExplore;
    @Bind(R.id.rb_main_me)
    RadioButton mRbMainMe;
    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    @Bind(R.id.fl_drawlayout_container)
    FrameLayout mFlMainContainer;
    private FragmentManager mFragmentManager;

    /**
     * radiobutton的监听
     */
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            switch (checkedId) {
                case R.id.rb_main_news:
                    //选中新闻页面,
                    if (mNewsFragment == null) {
                        mNewsFragment = new NewsFragment();
                    }
                    transaction.replace(R.id.fl_drawlayout_container, mNewsFragment, Constants.NEWS_FRAGMENT);
                    break;
                case R.id.rb_main_chat:
                    if (mChatFragment == null) {
                        mChatFragment = new ChatFragment();
                    }
                    transaction.replace(R.id.fl_drawlayout_container, mChatFragment, Constants.CHAT_FRAGMENT);
                    break;
                case R.id.rb_main_explore:
                    if (mExploreFragment == null) {
                        mExploreFragment = new ExploreFragment();
                    }
                    transaction.replace(R.id.fl_drawlayout_container, mExploreFragment, Constants.EXPLORE_FRAGMENT);
                    break;
                case R.id.rb_main_me:
                    if (mMeFragment == null) {
                        mMeFragment = new MeFragment();
                    }
                    transaction.replace(R.id.fl_drawlayout_container, mMeFragment, Constants.ME_FRAGMENT);
                    break;
            }

            transaction.commit();
        }
    };
    /**
     * 中间的小绿点的长按点击监听
     */
    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            //开启保存ipactivity
            startSaveIpActivity();
            //返回值返回true表明长按按钮消费了事件
            return true;
        }
    };

    /**
     * 开启保存ip的activity
     */
    private void startSaveIpActivity() {
        Intent intent = new Intent(mActivity, SaveIpActivity.class);
        startActivity(intent);
    }

    /**
     * 中间的小绿button的点击监听
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //弹出一个自定义dialog
            ButtomDialog buttomDialog = new ButtomDialog(mActivity);
            buttomDialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
            buttomDialog.show();
            UiUtils.showToast("我是轻按");

        }
    };
    private NewsFragment mNewsFragment;
    private ChatFragment mChatFragment;
    private ExploreFragment mExploreFragment;
    private MeFragment mMeFragment;


    @Override
    protected void initListener() {
        //radiogroup的选择事件
        mRgMain.setOnCheckedChangeListener(onCheckedChangeListener);
        //中间小绿点的点击事件
        mibMainPopup.setOnClickListener(onClickListener);
        //中间小绿点的长按点击事件
        mibMainPopup.setOnLongClickListener(onLongClickListener);
    }

    @Override
    protected void iniView() {
//        选中button1
        mRgMain.check(R.id.rb_main_news);
        mFragmentManager = getChildFragmentManager();
        //默认替换第一页的fragment
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mNewsFragment == null) {
            mNewsFragment = new NewsFragment();
        }
        fragmentTransaction.replace(R.id.fl_drawlayout_container, mNewsFragment, Constants.NEWS_FRAGMENT);
        fragmentTransaction.commit();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_drawerlayout_main;
    }

    @Override
    protected void initData() {

    }
}
