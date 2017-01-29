package com.example.javarss;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import java.io.Serializable;

/**
 * Created by axelc on 1/28/2017.
 */

public class ArticleViewModel extends BaseObservable implements Serializable{

    private Article mArticle;
    private Context mContext;

    public ArticleViewModel(Article article, Context context) {
        this.mArticle = article;
        this.mContext = context;
    }

    @Bindable
    public String getTitle() {
        return mArticle.getTitle();
    }

    public String getDate() { return mArticle.getPubDate(); }

    public String getDescription() { return mArticle.getDescription(); }

    public View.OnClickListener OpenDetails() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                // Set read here
                //
                Intent intent = new Intent(mContext, ArticleActivity.class);
                intent.putExtra("article", mArticle);
                mContext.startActivity(intent);
            }
        };
    }
}