package com.lerrycr.oschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.View.StateLayout;
import com.lerrycr.oschina.listener.OnResponseListener;

import java.util.Collection;

import butterknife.ButterKnife;

/**
 * Created by Lerry on 2016/10/28.
 */

public abstract class BaseContentFragment extends BaseFragment implements OnResponseListener {

    private StateLayout mStateLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mStateLayout = (StateLayout) inflater.inflate(R.layout.state_layout, null);
        mStateLayout.setContentView(getViewOrLayoutId());
        ButterKnife.bind(this, mStateLayout);
        return mStateLayout;
    }

    /**
     * 获取内容页面的布局
     *
     * @return
     */
    protected abstract Object getViewOrLayoutId();

    /**
     * 根据服务器返回的数据决定显示哪个状态的View
     *
     * @param datas
     * @return 如果数据是正常的，则返回true
     */
    protected boolean checkDatas(Collection<?> datas) {
        boolean result = false;
        // 根据服务器返回的数据决定显示哪个状态的View
        if (datas == null) {
            mStateLayout.showFailedView();
        } else if (datas.isEmpty()) {
            mStateLayout.showEmpty();
        } else {
            mStateLayout.showContentView();
            result = true;
        }
        return result;
    }

    /**
     * 不实现
     *
     * @return
     */
    @Override
    protected int getLayoutResId() {
        return 0;
    }


}
