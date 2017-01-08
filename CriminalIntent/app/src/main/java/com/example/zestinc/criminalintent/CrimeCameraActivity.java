package com.example.zestinc.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.view.WindowManager;

public class CrimeCameraActivity extends SingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstaceState) {
        // Hide the window title
        /*
            Original code is "requestWindowFeature(Window.FEATURE_NO_TITLE);"
            However, it doesn't work. (Maybe due to the new theme? Or action bar mechanism)
        */
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // Hide the status bar and other OS-level chrome
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstaceState);
    }
    @Override
    protected Fragment createFragment() {
        return new CrimeCameraFragment();
    }
}
