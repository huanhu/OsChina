package com.lerrycr.oschina.fragment.NewsDetailFragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.lerrycr.oschina.activity.ItemDetialActivity;
import com.lerrycr.oschina.adapter.RecommendAdapter;
import com.lerrycr.oschina.base.BaseContentFragment;
import com.lerrycr.oschina.bean.Blog;
import com.lerrycr.oschina.bean.BlogList;
import com.lerrycr.oschina.net.ApiClientHelper;
import com.lerrycr.oschina.utils.UiUtils;
import com.lerrycr.oschina.utils.XmlUtils;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lerry on 2016/10/28.
 */

public class RecommendFragment extends BaseContentFragment {

    public static final String RECOMMEND_DETIAL_ID = "RECOMMEND_DETIAL_ID";
    private ArrayList<Blog> mBloglist;
    private RecommendAdapter mRecommendAdapter;

    @Override
    protected void iniView() {

    }

    @Override
    protected void initListen() {
        super.initListen();
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Blog blog = (Blog) parent.getItemAtPosition(position);
                        int blog_id = blog.getId();
                        Intent intent = new Intent(getActivity(), ItemDetialActivity.class);
                        intent.putExtra(RECOMMEND_DETIAL_ID, blog_id);
                        intent.setAction("com.oschina.recommend.detial");
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    protected synchronized void initData() {
        int index = 0;
        if (mRecommendAdapter == null) {
            index = 0;
        } else {
            if (mSwipeRefresh.isRefreshing()) {
                index = 0;
            }

            if (mRecommendAdapter.getCount() - 1 == mListview.getLastVisiblePosition()) {
                //索引加1

                index = mRecommendAdapter.getCount() % 19;
                System.out.println("index=" + index);
            }
        }

        if (index > 3) {
            UiUtils.showToast("我是有底线的");
            return;
        }
        ApiClientHelper.getRecommand(getActivity(), index, this);
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(String response) throws IOException {
        byte[] result = response.getBytes();
        BlogList blogList = XmlUtils.toBean(BlogList.class, result);
        //初始化adapter数据
        initAdapterData(blogList);
    }

    private void initAdapterData(BlogList blogList) {
        mBloglist = (ArrayList<Blog>) blogList.getBloglist();
        if (mRecommendAdapter == null) {  //adapter说明是第一次加载
            if (checkDatas(mBloglist)) {    //根据服务器返回的数据判断显示view
                //如果是第一次展示界面,new adapter,设置给适配器
                mRecommendAdapter = new RecommendAdapter(mBloglist);
                mListview.setAdapter(mRecommendAdapter);
            }

        } else {
            //说明不是第一次加载数据
            //下拉刷新
            //清空之前的数据
            if (mSwipeRefresh.isRefreshing()) {
                mRecommendAdapter.getList().clear();
                mSwipeRefresh.setRefreshing(false);
                UiUtils.showToast("刷新成功");
            }
            mRecommendAdapter.getList().addAll(mBloglist);
            //通知adapter更新
            mRecommendAdapter.notifyDataSetChanged();

        }
    }


}
