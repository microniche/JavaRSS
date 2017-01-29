package com.example.javarss;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.javarss.databinding.ArticleItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by axelc on 1/28/2017.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.BindingHolder> {

    private List<ArticleViewModel>  mArticles;
    private Context                 mContext;

    public ArticleListAdapter(List<Article> articles, Context context) {
        mContext = context;
        mArticles = new ArrayList<>();
        for (Article article : articles) {
            this.mArticles.add(new ArticleViewModel(article, mContext));
        }
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArticleItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.article_item, parent, false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ArticleItemBinding binding = holder.binding;
        binding.setAvm(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ArticleItemBinding binding;

        public BindingHolder(ArticleItemBinding binding) {
            super(binding.articleItem);
            this.binding = binding;
        }
    }
}

