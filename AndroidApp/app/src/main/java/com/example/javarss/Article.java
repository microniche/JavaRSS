package com.example.javarss;

import java.io.Serializable;

/**
 * Created by axelc on 1/28/2017.
 */

public class Article implements Serializable {

    private String  title;
    private String  description;
    private String  link;
    private String  pubDate;
    private String  imageUrl;
    private boolean read;

    public Article(String title, String description, String link, String pubDate, String imageUrl, boolean read) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.imageUrl = imageUrl;
        this.read = read;
    }

    public Article(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = null;
        this.imageUrl = null;
        this.read = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
