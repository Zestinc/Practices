package com.example.zestinc.zestinclauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private final Activity mActivity;
    private List<ResolveInfo> mApps;

    HomeAdapter(Activity activity) {
        mActivity = activity;
        loadApps();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(
                LayoutInflater.from(mActivity).inflate(R.layout.recycler_view_item, parent,
                false));
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        ImageView iv;

        if(holder.iv == null) {
            iv = new ImageView(mActivity);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            int iconSize = mActivity.getResources().getDimensionPixelSize(R.dimen.app_icon_size);
            iv.setLayoutParams(new GridView.LayoutParams(iconSize, iconSize));
        } else {
            iv = holder.iv;
        }
        ResolveInfo info = mApps.get(position);
        iv.setImageDrawable(info.activityInfo.loadIcon(mActivity.getPackageManager()));

        holder.iv.setImageDrawable(info.activityInfo.loadIcon(mActivity.getPackageManager()));
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResolveInfo info = mApps.get(holder.getAdapterPosition());
                //该应用的包名
                String pkg = info.activityInfo.packageName;
                //应用的主activity类
                String cls = info.activityInfo.name;
                ComponentName component = new ComponentName(pkg, cls);

                Intent intent = new Intent();
                intent.setComponent(component);
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    private void loadApps() {
        PackageManager mPackageManager = mActivity.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = mPackageManager.queryIntentActivities(mainIntent, 0);
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        HomeViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.icon_id);
        }
    }
}
