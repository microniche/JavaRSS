package com.JavaRSS.Beans;

import java.util.Date;

public class Feed {
	private int id;
	private int ownerUser;
	private String link;
	private String title;
	private String description;
	private Date lastBuildDate;
	
	public Feed()
	{
		this.id = -1;
		this.ownerUser = -1;
		this.link = null;
		this.title = null;
		this.description = null;
		this.lastBuildDate = null;
	}
	
	public Feed(int id, int ownerUser, String link, String title, String description, Date lastBuildDate) 
	{
		this.id = id;
		this.ownerUser = ownerUser;
		this.link = link;
		this.title = title;
		this.description = description;
		this.lastBuildDate = lastBuildDate;
	}
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public int getOwnerUser() 
	{
		return ownerUser;
	}
	public void setOwnerUser(int ownerUser) 
	{
		this.ownerUser = ownerUser;
	}
	
	public String getLink() 
	{
		return link;
	}
	public void setLink(String link) 
	{
		this.link = link;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Date getLastBuildDate()
	{
		return lastBuildDate;
	}
	public void setLastBuildDate(Date lastBuildDate)
	{
		this.lastBuildDate = lastBuildDate;
	}
}
