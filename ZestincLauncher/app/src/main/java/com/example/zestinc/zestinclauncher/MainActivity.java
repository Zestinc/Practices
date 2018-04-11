package com.example.zestinc.zestinclauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    private PackageManager mPackageManager;
    private List<ResolveInfo> mApps;
    private AppsAdapter mAppsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadApps();
        mAppsAdapter = new AppsAdapter(mApps, this);

        GridView gridview = (GridView) findViewById(R.id.apps_list);
        gridview.setAdapter(mAppsAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                ResolveInfo info = mApps.get(position);
                //该应用的包名  
                String pkg = info.activityInfo.packageName;
                //应用的主activity类
                String cls = info.activityInfo.name;
                ComponentName component = new ComponentName(pkg, cls);

                Intent intent = new Intent();
                intent.setComponent(component);
                startActivity(intent);
            }
        });
    }

    private void loadApps() {
        mPackageManager = this.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = mPackageManager.queryIntentActivities(mainIntent, 0);
    }
}
