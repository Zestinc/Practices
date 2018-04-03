package com.example.zestinc.zestinclauncher

import android.app.Activity
import android.content.pm.ResolveInfo
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView

class AppsAdapter internal constructor(private val mApps: List<ResolveInfo>, private val mActivity: Activity) : BaseAdapter() {

    override fun getCount(): Int {
        return mApps.size
    }

    override fun getItem(i: Int): Any {
        return mApps[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }


    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val iv: ImageView

        if (view == null) {
            iv = ImageView(mActivity)
            iv.scaleType = ImageView.ScaleType.FIT_CENTER
            val iconSize = mActivity.resources.getDimensionPixelSize(R.dimen.app_icon_size)
            iv.layoutParams = ViewGroup.LayoutParams(iconSize, iconSize)
        } else {
            iv = view as ImageView
        }
        val info = mApps[i]
        iv.setImageDrawable(info.activityInfo.loadIcon(mActivity.packageManager))
        return iv
    }
}