package com.lerrycr.oschina.adapter;

import com.lerrycr.oschina.base.BasicListAdapter;
import com.lerrycr.oschina.bean.Blog;

import java.util.ArrayList;

/**
 * Created by Lerry on 2016/10/30.
 */
public class BlogAdapter extends BasicListAdapter<Blog> {


    public BlogAdapter(ArrayList<Blog> list) {
        super(list);
    }

    @Override
    protected void showDatas(ViewHolder viewHolder, Blog datas, int position) {
        viewHolder.mTvItemMessageTitle.setText(datas.getTitle());
        viewHolder.mTvItemMessageContent.setText(datas.getBody());
        viewHolder.mTvItemMessageUserName.setText(datas.getAuthor());
        viewHolder.mTvItemMessageDate.setText(datas.getPubDate());
        viewHolder.mTvItemMessageComment.setText(datas.getCommentCount() + "");
    }
}
