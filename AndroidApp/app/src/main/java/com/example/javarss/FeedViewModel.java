package com.example.javarss;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by axelc on 1/29/2017.
 */

public class FeedViewModel extends BaseObservable {

    private Feed mFeed;
    private Context mContext;

    public FeedViewModel(Feed feed, Context context) {
        this.mFeed = feed;
        this.mContext = context;
    }

    @Bindable
    public String getLink() {
        return mFeed.getLink();
    }

}
