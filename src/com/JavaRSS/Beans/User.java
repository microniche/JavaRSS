package com.JavaRSS.Beans;

public class User {
	private String username;
	private String email;
	private int id;
	
	public User()
	{
		this.username = null;
		this.email = null;
		this.id = 0;
	}
	
	public User(String username, String email)
	{
		this.username = username;
		this.email = email;
		this.id = 0;
	}
	
	public User(String username, String email, int id)
	{
		this.username = username;
		this.email = email;
		this.id = id;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return (this.username);
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return (this.email);
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return (this.id);
	}
}
