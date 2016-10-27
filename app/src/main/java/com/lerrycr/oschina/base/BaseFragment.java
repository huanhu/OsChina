package com.lerrycr.oschina.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lerry on 2016/10/27.
 */

public abstract class BaseFragment extends Fragment {

    protected View mView;
    protected Context mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        mView = initView();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 初始化view
     *
     * @return
     */
    protected abstract View initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();
}
