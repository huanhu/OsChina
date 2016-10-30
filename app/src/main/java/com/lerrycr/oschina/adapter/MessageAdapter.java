package com.lerrycr.oschina.adapter;

import android.view.View;
import android.widget.TextView;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.base.BasicListAdapter;
import com.lerrycr.oschina.bean.News;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lerry on 2016/10/28.
 */

public class MessageAdapter extends BasicListAdapter<News> {


    public MessageAdapter(ArrayList<News> list) {
        super(list);
    }

    @Override
    protected Object createViewHolderAndFindViewById(View convertView, int position) {
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    protected void showDatas(Object viewHolder, News datas, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        //设置标题
        holder.mTvItemMessageTitle.setText(datas.getTitle());
        holder.mTvItemMessageContent.setText(datas.getBody());
        holder.mTvItemMessageUserName.setText(datas.getAuthor());
        holder.mTvItemMessageDate.setText(datas.getPubDate());
        holder.mTvItemMessageComment.setText(datas.getCommentCount() + "");
    }

    @Override
    protected int getItemLayoutResIds(int position) {
        return R.layout.item_news_message;
    }


    static class ViewHolder {
        @Bind(R.id.tv_item_message_title)
        TextView mTvItemMessageTitle;
        @Bind(R.id.tv_item_message_content)
        TextView mTvItemMessageContent;
        @Bind(R.id.tv_item_message_user_name)
        TextView mTvItemMessageUserName;
        @Bind(R.id.tv_item_message_date)
        TextView mTvItemMessageDate;
        @Bind(R.id.tv_item_message_comment)
        TextView mTvItemMessageComment;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
