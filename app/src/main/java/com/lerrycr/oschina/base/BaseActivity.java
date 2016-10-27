package com.lerrycr.oschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Lerry on 2016/10/27.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        /**
         * 绑定view
         */
        ButterKnife.bind(this);
        /**
         * 初始化一些其他的view
         */
        initView();
        /**
         * 初始化数据
         */
        initData();
        /**初始化监听*/
        initListener();
    }


    /**
     * 初始化其他的view
     */
    protected abstract void initView();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置contentView
     */
    private void setContentView() {
        Object viewOrLayoutId = getLayoutIdOrView();
        System.out.println("viewOrLayoutId=" + viewOrLayoutId);
        if (viewOrLayoutId == null) {
            throw new IllegalArgumentException("viewOrLayoutId参数不能为空");
        }

        if (viewOrLayoutId instanceof Integer) {
            // 如果是整数，说明是布局id
            int layoutId = (int) viewOrLayoutId;
            setContentView(layoutId);
        } else {
            setContentView((View) viewOrLayoutId);
        }
    }


    /**
     *
     */


    /**
     * 初始化数据
     */
    protected abstract Object getLayoutIdOrView();

}