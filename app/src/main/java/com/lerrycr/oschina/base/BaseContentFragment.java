package com.lerrycr.oschina.base;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.View.StateLayout;

import butterknife.Bind;

/**
 * Created by Lerry on 2016/10/28.
 */

public abstract class BaseContentFragment extends BaseFragment {
    @Bind(R.id.statelayout)
    StateLayout mStatelayout;

    @Override
    protected void initListener() {

    }

    @Override
    protected void iniView() {
        mStatelayout.setContentView(getViewOrLayoutId());
    }

    /**
     * 获取内容页面的布局
     *
     * @return
     */
    protected abstract Object getViewOrLayoutId();

    @Override
    protected int getLayoutResId() {
        return R.layout.state_layout;
    }

    @Override
    protected void initData() {

    }
}
