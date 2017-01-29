package com.example.javarss;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.javarss.databinding.FeedItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by axelc on 1/29/2017.
 */

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.BindingHolder> {

    private List<FeedViewModel> mFeeds;
    private Context mContext;

    public FeedListAdapter(List<Feed> feeds, Context context) {
        mContext = context;
        mFeeds = new ArrayList<>();
        for (Feed feed : feeds) {
            this.mFeeds.add(new FeedViewModel(feed, mContext));
        }
    }

    @Override
    public FeedListAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeedItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.feed_item, parent, false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(FeedListAdapter.BindingHolder holder, int position) {
        FeedItemBinding binding = holder.binding;
        binding.setFvm(mFeeds.get(position));
    }

    @Override
    public int getItemCount() {
        return mFeeds.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private FeedItemBinding binding;

        public BindingHolder(FeedItemBinding binding) {
            super(binding.feedItem);
            this.binding = binding;
        }
    }
}
