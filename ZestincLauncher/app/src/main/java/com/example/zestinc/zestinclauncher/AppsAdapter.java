package com.example.zestinc.zestinclauncher;

import android.app.Activity;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class AppsAdapter extends BaseAdapter {

    private List<ResolveInfo> mApps;
    private Activity mActivity;

    AppsAdapter(List<ResolveInfo> apps, Activity activity){
        mApps = apps;
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mApps.size();
    }

    @Override
    public Object getItem(int i) {
        return mApps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView iv;

        if(view == null){
            iv = new ImageView(mActivity);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            int iconSize = mActivity.getResources().getDimensionPixelSize(R.dimen.app_icon_size);
            iv.setLayoutParams(new GridView.LayoutParams(iconSize, iconSize));
        } else {
            iv = (ImageView) view;
        }
        ResolveInfo info = mApps.get(i);
        iv.setImageDrawable(info.activityInfo.loadIcon(mActivity.getPackageManager()));
        return iv;
    }
}