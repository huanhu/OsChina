package com.lerrycr.oschina.fragment.NewsDetailFragment;

import com.lerrycr.oschina.adapter.BlogAdapter;
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

public class BlogFragment extends BaseContentFragment {

    private ArrayList<Blog> mBloglist;
    private BlogAdapter mBlogAdapter;


    @Override
    protected void iniView() {

    }

    @Override
    protected synchronized void initData() {
        int index = 0;
        if (mBlogAdapter == null) {
            index = 0;
        } else {
            if (mSwipeRefresh.isRefreshing()) {
                index = 0;
            }

            if (mBlogAdapter.getCount() - 1 == mListview.getLastVisiblePosition()) {
                //索引加1
                index = mBlogAdapter.getCount() % 19;
                System.out.println(index);
                System.out.println("index=" + index);
            }
        }
        if (index > 3) {
            UiUtils.showToast("我是有底线的");
            return;
        }

        ApiClientHelper.getBlog(getActivity(), index, this);
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
        if (mBlogAdapter == null) {  //adapter说明是第一次加载
            if (checkDatas(mBloglist)) {    //根据服务器返回的数据判断显示view
                //如果是第一次展示界面,new adapter,设置给适配器
                mBlogAdapter = new BlogAdapter(mBloglist);
                mListview.setAdapter(mBlogAdapter);
            }
        } else {
            //说明不是第一次加载数据
            //下拉刷新
            //清空之前的数据
            if (mSwipeRefresh.isRefreshing()) {
                mBlogAdapter.getList().clear();
                mSwipeRefresh.setRefreshing(false);
                UiUtils.showToast("刷新成功");
            }
            mBlogAdapter.getList().addAll(mBloglist);
            //通知adapter更新
            mBlogAdapter.notifyDataSetChanged();
        }
    }


}
