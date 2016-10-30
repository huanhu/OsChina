package com.lerrycr.oschina.fragment.NewsDetailFragment;

import android.widget.ListView;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.View.VpSwipeRefreshLayout;
import com.lerrycr.oschina.adapter.MessageAdapter;
import com.lerrycr.oschina.base.BaseContentFragment;
import com.lerrycr.oschina.bean.News;
import com.lerrycr.oschina.bean.NewsList;
import com.lerrycr.oschina.constants.Constants;
import com.lerrycr.oschina.constants.Urls;
import com.lerrycr.oschina.utils.PreferenceUtils;
import com.lerrycr.oschina.utils.XmlUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

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
    private static final int MSG_SUCCESS = 100;
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
        //获取数据
        //创建okhttp对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个request请求
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(Urls.MESSAGE_URL + "/page0.xml").
                build();
        System.out.println("url=" + Urls.MESSAGE_URL + "/page0.xml");

        //new call
        Call call = okHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //失败了打印一下
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                byte[] result = response.body().bytes();
                System.out.println("result=" + result);
                //保存起来
                PreferenceUtils.putString(mActivity, Constants.MESSAGE_FRAGMENT_INFO, new String(result));
//                   封装成bean对象
                mNewsList = XmlUtils.toBean(NewsList.class, result);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initAdapterData();
                    }
                });
            }
        });
    }

}
