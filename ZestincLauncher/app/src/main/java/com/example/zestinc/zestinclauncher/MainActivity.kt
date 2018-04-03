package com.example.zestinc.zestinclauncher

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast

class MainActivity : Activity() {

    private var mPackageManager: PackageManager? = null
    private var mApps: List<ResolveInfo>? = null
    private var mAppsAdapter: AppsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadApps()
        mAppsAdapter = AppsAdapter(mApps!!, this)

        val gridview = findViewById(R.id.apps_list) as GridView
        gridview.adapter = mAppsAdapter

        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            val info = mApps!![position]
            //该应用的包名
            val pkg = info.activityInfo.packageName
            //应用的主activity类
            val cls = info.activityInfo.name
            val component = ComponentName(pkg, cls)

            val intent = Intent()
            intent.component = component
            startActivity(intent)
        }
    }

    private fun loadApps() {
        mPackageManager = this.packageManager
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        mApps = mPackageManager!!.queryIntentActivities(mainIntent, 0)
    }
}
