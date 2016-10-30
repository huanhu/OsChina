package com.lerrycr.oschina.activity;

import android.content.Intent;
import android.widget.TextView;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.base.BaseActivity;
import com.lerrycr.oschina.fragment.NewsDetailFragment.MessageFragment;

public class ItemDetialActivity extends BaseActivity {

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String detial_url = intent.getStringExtra(MessageFragment.MESSAGE_DETIAL_URL);
        TextView textview = (TextView) findViewById(R.id.text);
        textview.setText(detial_url);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected Object getLayoutIdOrView() {
        return R.layout.activity_item_detial;
    }
}
