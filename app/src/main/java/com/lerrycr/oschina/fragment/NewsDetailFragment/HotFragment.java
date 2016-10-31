package com.lerrycr.oschina.fragment.NewsDetailFragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.lerrycr.oschina.activity.ItemDetialActivity;
import com.lerrycr.oschina.adapter.HotsAdapter;
import com.lerrycr.oschina.base.BaseContentFragment;
import com.lerrycr.oschina.bean.News;
import com.lerrycr.oschina.bean.NewsList;
import com.lerrycr.oschina.net.ApiClientHelper;
import com.lerrycr.oschina.utils.Logger;
import com.lerrycr.oschina.utils.UiUtils;
import com.lerrycr.oschina.utils.XmlUtils;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lerry on 2016/10/28.
 */

public class HotFragment extends BaseContentFragment {
    public static final String HOTS_DETIAL_ID = "HOTS_DETIAL_ID";
    private ArrayList<News> mList;
    private HotsAdapter mHotsAdapter;


    @Override
    protected void iniView() {
        mList = new ArrayList<>();
    }

    @Override
    protected void initListen() {
        super.initListen();
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = (News) parent.getItemAtPosition(position);
                int news_id = news.getId();
                Intent intent = new Intent(getActivity(), ItemDetialActivity.class);
                intent.putExtra(HOTS_DETIAL_ID, news_id);
                intent.setAction("com.oschina.hots.detial");
                startActivity(intent);
            }
        });
    }

    @Override
    protected synchronized void initData() {
        int index = 0;
        if (mHotsAdapter == null) {
            index = 0;
        } else {

            if (mSwipeRefresh.isRefreshing()) {
                index = 0;
            }

            if (mHotsAdapter.getCount() - 1 == mListview.getLastVisiblePosition()) {
                //索引加1

                index = mHotsAdapter.getCount() % 19;
                System.out.println("index=" + index);
            }
        }
        if (index > 0) {
            UiUtils.showToast("我是有底线的");
            return;
        }
        ApiClientHelper.getHots(getActivity(), index, this);
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(String response) throws IOException {
        byte[] result = response.getBytes();
        NewsList newsList = XmlUtils.toBean(NewsList.class, result);
        System.out.println("我走了吗,hotfragment");
        //初始化adapter数据
        initAdapterData(newsList);
    }

    private void initAdapterData(NewsList newsList) {
        mList = (ArrayList<News>) newsList.getList();
        if (mHotsAdapter == null) {
            if (checkDatas(mList)) {
                Logger.i(this, "第一次加载");
                mHotsAdapter = new HotsAdapter(mList);
                mListview.setAdapter(mHotsAdapter);
            }
        } else {
            //下拉刷新
            //说明不是第一次加载数据
            //清空之前的数据
            if (mSwipeRefresh.isRefreshing()) {
                mHotsAdapter.getList().clear();
                mSwipeRefresh.setRefreshing(false);
                UiUtils.showToast("刷新成功");
            }
            mHotsAdapter.getList().addAll(mList);
            //通知adapter更新
            mHotsAdapter.notifyDataSetChanged();

        }
    }

}
