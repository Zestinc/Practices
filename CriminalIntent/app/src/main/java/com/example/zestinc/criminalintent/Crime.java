package com.example.zestinc.criminalintent;

import java.util.UUID;

/**
 * Created by zestinc on 16/9/28.
 */

public class Crime {
    private UUID mId;
    private String mTitle;

    public Crime() {
        // Generate the unique ID
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
