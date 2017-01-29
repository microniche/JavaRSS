package com.example.javarss;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.javarss.databinding.ArticleListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by axelc on 1/29/2017.
 */

public class ArticleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_list_layout);

        ArticleListLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.article_list_layout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.articleList.setLayoutManager(layoutManager);

        List<Article> articles = new ArrayList<>();

        for (int i = 0; i < 42; i++)
            articles.add(new Article("Title" + i,"Description" + i,"Link" + i, "PubDate" + i, "ImageLink" + i, false));

        ArticleListAdapter adapter = new ArticleListAdapter(articles, this);
        binding.articleList.setAdapter(adapter);
    }
}
