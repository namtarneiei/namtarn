package com.example.computer.workshop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by computer on 5/11/2559.
 */
public class CustomAdpater extends BaseAdapter{
    Context mContext;
    String[] NewsName;
    String[] DateName;
    int[] resId;

    public CustomAdpater(Context context, String[] NewsName,String[] DateName, int[] resID) {
        this.mContext = context;
        this.NewsName = NewsName;
        this.DateName = DateName;
        this.resId = resID;
    }

    @Override
    public int getCount() {
        return NewsName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
