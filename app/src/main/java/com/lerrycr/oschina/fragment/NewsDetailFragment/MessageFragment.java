package com.lerrycr.oschina.fragment.NewsDetailFragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.lerrycr.oschina.activity.ItemDetialActivity;
import com.lerrycr.oschina.adapter.MessageAdapter;
import com.lerrycr.oschina.base.BaseContentFragment;
import com.lerrycr.oschina.bean.News;
import com.lerrycr.oschina.bean.NewsList;
import com.lerrycr.oschina.net.ApiClientHelper;
import com.lerrycr.oschina.utils.UiUtils;
import com.lerrycr.oschina.utils.XmlUtils;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerry on 2016/10/28.
 */

public class MessageFragment extends BaseContentFragment {

    public static final String MESSAGE_DETIAL_ID = "message_detial_id";
    /**
     * 存储数据
     */

    private MessageAdapter mMessageAdapter;
    private List<News> mList;


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
                intent.putExtra(MESSAGE_DETIAL_ID, news_id);
                intent.setAction("com.oschina.message.detial");
                startActivity(intent);
            }
        });
    }

    @Override
    protected synchronized void initData() {
        int index = 0;
        if (mMessageAdapter == null) {
            index = 0;
        } else {
            if (mSwipeRefresh.isRefreshing()) {
                index = 0;
            }

            if (mMessageAdapter.getCount() - 1 == mListview.getLastVisiblePosition()) {
                //索引加1

                index = mMessageAdapter.getCount() % 19;
                System.out.println(mMessageAdapter.getCount());
                System.out.println("index=" + index);
            }
        }

        if (index > 3) {
            UiUtils.showToast("我是有底线的");
            return;
        }
        ApiClientHelper.getMessages(getActivity(), index, this);
    }

    //网络获取数据
    @Override
    public void onFailure(Request request, IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(String response) throws IOException {
        byte[] result = response.getBytes();
        NewsList newsList = XmlUtils.toBean(NewsList.class, result);
        //初始化adapter数据
        initAdapterData(newsList);
    }

    /**
     * 初始化viewpager的数据
     *
     * @param newsList
     */
    private void initAdapterData(NewsList newsList) {
        mList = newsList.getList();
        if (mMessageAdapter == null) {
            if (checkDatas(mList)) {
                mMessageAdapter = new MessageAdapter((ArrayList<News>) mList);
                mListview.setAdapter(mMessageAdapter);
            }
        } else {
            //如果是下拉刷新
            if (mSwipeRefresh.isRefreshing()) {
                //下拉刷新
                mMessageAdapter.getList().clear();
                mSwipeRefresh.setRefreshing(false);
                UiUtils.showToast("刷新成功");
            }

            mMessageAdapter.getList().addAll(mList);
            mMessageAdapter.notifyDataSetChanged();
        }


    }

}
