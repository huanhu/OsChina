package com.lerrycr.oschina.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lerrycr.oschina.R;

import java.util.ArrayList;

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
        Object viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_news_message, null);
            viewHolder = createViewHolderAndFindViewById(convertView, position);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = convertView.getTag();
        }
        T datas = mList.get(position);
        //设置标题
        showDatas(viewHolder, datas, position);
        return convertView;
    }

    /**
     * 创建viewholder
     *
     * @param convertView
     * @param position
     * @return
     */
    protected abstract Object createViewHolderAndFindViewById(View convertView, int position);

    /**
     * 展示数据
     *
     * @param viewHolder
     * @param datas
     * @param position
     */
    protected abstract void showDatas(Object viewHolder, T datas, int position);

    protected abstract int getItemLayoutResIds(int position);
}
