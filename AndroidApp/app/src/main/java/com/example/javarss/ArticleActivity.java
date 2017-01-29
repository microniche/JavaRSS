package com.example.javarss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by axelc on 1/29/2017.
 */

public class ArticleActivity extends AppCompatActivity {

    Article mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_layout);

        TextView title = (TextView) findViewById(R.id.activity_title);
        TextView date = (TextView) findViewById(R.id.activity_date);
        TextView description = (TextView) findViewById(R.id.activity_description);
        TextView link = (TextView) findViewById(R.id.activity_link);
        mArticle = (Article) getIntent().getSerializableExtra("article");
        if (mArticle != null) {
            title.setText(mArticle.getTitle());
            date.setText(mArticle.getPubDate());
            description.setText(mArticle.getDescription());
            link.setText(mArticle.getLink());
        } else {
            startActivity(new Intent(this, ArticleListActivity.class));
        }
    }
}
