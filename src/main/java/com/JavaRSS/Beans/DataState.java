package com.JavaRSS.Beans;

import SoftwareRSS.Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ruano_a on 29/01/2017.
 */
public class DataState implements Serializable {
    private List<Article> articles;
    private List<Feed> feeds;
    private Date lastUpdate;

    private static String saveFile = "save.xml";
    public DataState()
    {
        articles = null;
        feeds = null;
        lastUpdate = null;
    }

    public DataState(List<Article> articles, List<Feed> feeds, Date lastUpdate)
    {
        this.articles = articles;
        this.feeds = feeds;
        this.lastUpdate = lastUpdate;
    }
    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }


    public List<Feed> getFeeds()
    {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds)
    {
        this.feeds = feeds;
    }

    public List<Article> getArticles()
    {
        return articles;
    }

    public void setArticles(List<Article> articles)
    {
        this.articles = articles;
    }

    public String toString()
    {
        return ("DataState toString: " + (articles != null ? articles.size() : "null") + " " + (feeds != null ? feeds.size() : "null") + " " + (lastUpdate != null ? lastUpdate.toString() : null));
    }
    static public DataState getDatas()
    {
        return (Utils.deserializeDataState(saveFile));
    }
    static public void saveDatas(DataState dataState)
    {
        Utils.serialize(dataState, saveFile);
    }
    static private boolean checkExistingFeed(int idFeed, List<Feed> feeds)
    {
        for (Feed feed : feeds)
        {
            if (feed.getId() == idFeed)
                return (true);
        }
        return (false);
    }
    static public void removeArticlesFromDeadFeeds(List<Article> articles, List<Feed> feeds)
    {
        Article article;
        System.out.println("removeArticlesFromDeadFeeds BEFORE " + articles.size());
        for (int i = 0; i < articles.size(); i++)
        {
            article = articles.get(i);
            if (!checkExistingFeed(article.getOwnerRss(), feeds)) {
                System.out.println("remove one article");
                i = -1; //will be incremented, so has to be -1
                articles.remove(article);
            }
        }
        System.out.println("removeArticlesFromDeadFeeds AFTER " + articles.size());
    }
}
