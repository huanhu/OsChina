package com.lerrycr.oschina.fragment.NewsDetailFragment;

import android.widget.ListView;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.View.VpSwipeRefreshLayout;
import com.lerrycr.oschina.adapter.MessageAdapter;
import com.lerrycr.oschina.base.BaseContentFragment;
import com.lerrycr.oschina.bean.News;
import com.lerrycr.oschina.bean.NewsList;
import com.lerrycr.oschina.net.ApiClientHelper;
import com.lerrycr.oschina.utils.XmlUtils;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Lerry on 2016/10/28.
 */

public class MessageFragment extends BaseContentFragment {
    /**
     * 发送消息成功
     */
    @Bind(R.id.message_listview)
    ListView mMessageListview;
    @Bind(R.id.message_swipe_refresh)
    VpSwipeRefreshLayout mMessageSwipeRefresh;
    /**
     * 存储数据
     */
    private ArrayList<News> mList;
    /**
     * 资讯信息的bean对象
     */
    private NewsList mNewsList;
    private MessageAdapter mMessageAdapter;


    @Override
    protected void initListener() {

    }

    @Override
    protected void iniView() {
        mList = new ArrayList<>();
    }

    @Override
    protected Object getViewOrLayoutId() {
        return R.layout.fragment_message;
    }


    @Override
    protected void initData() {
        ApiClientHelper.getMessages(getActivity(), this);
    }

    //网络获取数据
    @Override
    public void onFailure(Request request, IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(String response) throws IOException {
        byte[] result = response.getBytes();
        System.out.println("result=" + result);
        mNewsList = XmlUtils.toBean(NewsList.class, result);
        //初始化adapter数据
        initAdapterData();
    }

    /**
     * 初始化viewpager的数据
     */
    private void initAdapterData() {
        if (mNewsList != null) {
            mList = (ArrayList<News>) mNewsList.getList();
            if (checkDatas(mList)) {
                mMessageAdapter = new MessageAdapter(mList);
                mMessageListview.setAdapter(mMessageAdapter);
            }
        }
    }
}
