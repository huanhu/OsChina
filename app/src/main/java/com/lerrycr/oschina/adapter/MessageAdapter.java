package com.lerrycr.oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lerrycr.oschina.R;
import com.lerrycr.oschina.bean.News;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lerry on 2016/10/28.
 */

public class MessageAdapter extends BaseAdapter {


    private ArrayList<News> mList;

    public ArrayList<News> getList() {
        return mList;
    }

    public MessageAdapter(ArrayList<News> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_news_message, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        News news = mList.get(position);
        //设置标题
        viewHolder.mTvItemMessageTitle.setText(news.getTitle());
        viewHolder.mTvItemMessageContent.setText(news.getBody());
        viewHolder.mTvItemMessageUserName.setText(news.getAuthor());
        viewHolder.mTvItemMessageDate.setText(news.getPubDate());
        viewHolder.mTvItemMessageComment.setText(news.getCommentCount() + "");

        return convertView;
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
