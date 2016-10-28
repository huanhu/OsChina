package com.lerrycr.oschina.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Lerry on 2016/10/27.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mActivity;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        mView = inflater.inflate(getLayoutResId(), null);
        ButterKnife.bind(this, mView);
        /**
         * 绑定返回的view
         */
        iniView();
        initListener();
        return mView;
    }

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化一些工具类和资源
     */
    protected abstract void iniView();

    /**
     * 获取布局的id
     *
     * @return
     */
    protected abstract int getLayoutResId();


    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 指示fragment是否是第一次显示出来
     */
    private boolean firstShow = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        System.out.println("setUserVisibleHint " + isVisibleToUser);
        if (isVisibleToUser && firstShow) {
            // 如果Fragemnt是可见的，并且是第一次显示，则调用initData加载数据
            firstShow = false;

            // setUserVisibleHint这个方法运行的时候，onCeateView还没有执行
            // 为了让onCreateView先执行完，所以这里迟一点再执行initData
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    initData();     // 加载数据
                    System.out.println("initData()");
                }
            };

            new Handler().postDelayed(runnable, 30);    // 30毫秒之后执行runnable中的run方法
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
