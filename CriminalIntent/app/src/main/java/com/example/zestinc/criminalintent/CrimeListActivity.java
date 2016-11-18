package com.example.zestinc.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by zestinc on 16/10/5.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
