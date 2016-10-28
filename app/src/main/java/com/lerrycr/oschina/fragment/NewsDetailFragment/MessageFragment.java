package com.lerrycr.oschina.fragment.NewsDetailFragment;

import android.widget.ListView;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Lerry on 2016/10/28.
 */

public class MessageFragment extends BaseFragment {
    @Bind(R.id.message_listview)
    ListView mMessageListview;

    @Override
    protected void initListener() {

    }

    @Override
    protected void iniView() {
//        MessageAdapter messageAdapter = new MessageAdapter();
//        mMessageListview.setAdapter(messageAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData() {

    }
}
