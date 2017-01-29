package com.example.javarss;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.javarss.databinding.ManageFeedLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by axelc on 1/29/2017.
 */

public class ManageFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_feed_layout);

        ManageFeedLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.manage_feed_layout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.feedList.setLayoutManager(layoutManager);

        List<Feed> feeds = new ArrayList<>();

        for (int i = 0; i < 42; i++)
            feeds.add(new Feed("FeedName" + i));

        FeedListAdapter adapter = new FeedListAdapter(feeds, this);
        binding.feedList.setAdapter(adapter);
    }
}
