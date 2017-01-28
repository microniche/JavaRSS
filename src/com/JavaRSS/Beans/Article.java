package com.JavaRSS.Beans;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{
	private int id;
	private int ownerRss;
	private int ownerUser;
	private String url;
	private Date pubDate;
	private String description;
	private Date dateAdded;
	private boolean isRead;
	private String guid;
	
	public Article()
	{
		this.id = -1;
		this.ownerRss = -1;
		this.ownerUser = -1;
		this.url = null;
		this.pubDate = null;
		this.description = null;
		this.dateAdded = null;
		this.guid = null;
	}
	public Article(int id, int ownerRss, int ownerUser, String url, Date pubDate, String description, Date dateAdded,
			boolean isRead, String guid)
	{
		this.id = id;
		this.ownerRss = ownerRss;
		this.ownerUser = ownerUser;
		this.url = url;
		this.pubDate = pubDate;
		this.description = description;
		this.dateAdded = dateAdded;
		this.isRead = isRead;
		this.guid = guid;
	}
	
	public String getGuid()
	{
		return guid;
	}
	public void setGuid(String guid)
	{
		this.guid = guid;
	}
	public void setRead(boolean isRead) 
	{
		this.isRead = isRead;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getOwnerRss() 
	{
		return ownerRss;
	}
	public void setOwnerRss(int ownerRss) 
	{
		this.ownerRss = ownerRss;
	}
	
	public int getOwnerUser() 
	{
		return ownerUser;
	}
	public void setOwnerUser(int ownerUser) 
	{
		this.ownerUser = ownerUser;
	}
	
	public String getUrl() 
	{
		return url;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	public Date getPubDate() 
	{
		return pubDate;
	}
	public void setPubDate(Date pubDate) 
	{
		this.pubDate = pubDate;
	}
	
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public Date getDateAdded()
	{
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded)
	{
		this.dateAdded = dateAdded;
	}
	
	public boolean getIsRead() 
	{
		return isRead;
	}
	public void setIsRead(boolean isRead) 
	{
		this.isRead = isRead;
	}
}
