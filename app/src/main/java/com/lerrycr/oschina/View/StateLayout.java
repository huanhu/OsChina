package com.lerrycr.oschina.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Lerry on 2016/10/28.
 */

public class StateLayout extends FrameLayout {
    /**
     * 网络问题的view
     */
    private View mNetView;
    /**
     * 加载失败的view
     */
    private View mFailedView;
    /**
     * 数据为空的view
     */
    private View mEmptyView;
    /**
     * 正在读取数据的view
     */
    private View mProgressView;
    /**
     * 显示内容的view
     */
    private View mContentView;

    public StateLayout(Context context) {
        this(context, null);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化工具类和资源
     */
    private void initView() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mNetView = getChildAt(0);
        mFailedView = getChildAt(1);
        mEmptyView = getChildAt(2);
        mProgressView = getChildAt(3);
        //默认显示转圈的view
    }

    /**
     * 显示网络问题的view
     */
    public void showNetView() {
        showView(mNetView);
    }

    public void showFailedView() {
        showView(mFailedView);
    }

    public void showEmpty() {
        showView(mEmptyView);
    }

    public void showProgressView() {
        showView(mProgressView);
    }

    public void showContentView() {
        showView(mContentView);
    }

    /**
     * 显示view
     *
     * @param view
     */
    private void showView(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            view.setVisibility(child == view ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * 设置正常界面，可以是一个View，也可以是一个布局id
     */
    public void setContentView(Object viewOrLayoutId) {
        if (viewOrLayoutId == null) {
            throw new IllegalArgumentException("viewOrLayoutId参数不能为空");
        }

        if (viewOrLayoutId instanceof Integer) {
            // 如果是整数，说明是布局id
            int layoutId = (int) viewOrLayoutId;
            mContentView = View.inflate(getContext(), layoutId, null);
        } else {
            mContentView = (View) viewOrLayoutId;
        }

        addView(mContentView);
        mContentView.setVisibility(View.GONE);   // 先隐藏，最开始先显示LoadingView
    }
}
