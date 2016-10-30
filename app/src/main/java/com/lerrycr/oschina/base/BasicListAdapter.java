package com.lerrycr.oschina.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lerrycr.oschina.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lerry on 2016/10/30.
 */

public abstract class BasicListAdapter<T> extends BaseAdapter {


    private ArrayList<T> mList;

    public ArrayList<T> getList() {
        return mList;
    }

    public BasicListAdapter(ArrayList<T> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
        T datas = mList.get(position);
        //设置数据
        showDatas(viewHolder, datas, position);
        return convertView;
    }

    /**
     * 展示数据
     *
     * @param viewHolder
     * @param datas
     * @param position
     */
    protected abstract void showDatas(ViewHolder viewHolder, T datas, int position);

    public static class ViewHolder {
        @Bind(R.id.tv_item_message_title)
        public TextView mTvItemMessageTitle;
        @Bind(R.id.tv_item_message_content)
        public TextView mTvItemMessageContent;
        @Bind(R.id.tv_item_message_user_name)
        public TextView mTvItemMessageUserName;
        @Bind(R.id.tv_item_message_date)
        public TextView mTvItemMessageDate;
        @Bind(R.id.tv_item_message_comment)
        public TextView mTvItemMessageComment;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
