package com.lerrycr.oschina.adapter;

import com.lerrycr.oschina.base.BasicListAdapter;
import com.lerrycr.oschina.bean.News;

import java.util.ArrayList;

/**
 * Created by Lerry on 2016/10/28.
 */

public class HotsAdapter extends BasicListAdapter<News> {


    public HotsAdapter(ArrayList<News> list) {
        super(list);
    }

    @Override
    protected void showDatas(ViewHolder viewHolder, News datas, int position) {
        viewHolder.mTvItemMessageTitle.setText(datas.getTitle());
        viewHolder.mTvItemMessageContent.setText(datas.getBody());
        viewHolder.mTvItemMessageUserName.setText(datas.getAuthor());
        viewHolder.mTvItemMessageDate.setText(datas.getPubDate());
        viewHolder.mTvItemMessageComment.setText(datas.getCommentCount() + "");
    }

}
