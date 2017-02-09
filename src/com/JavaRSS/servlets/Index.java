package com.JavaRSS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JavaRSS.Beans.Article;
import com.JavaRSS.Beans.Feed;
import com.JavaRSS.Beans.User;
import com.JavaRSS.Interfaces.DatabaseRequester;
import com.JavaRSS.Interfaces.XmlSerializer;

public class Index extends HttpServlet {
	public static final String ATT_MESSAGES = "messages";
			
public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	User user = (User) request.getSession().getAttribute(Login.INFOS_ATTRIBUTE);
	
	if (user != null)
	{
		DatabaseRequester db = new DatabaseRequester();
		db.connect();
		List<Feed> feeds = db.getFeeds(user.getId());
		List<Article> articles = db.getArticlesFromUser(user.getId());
		System.out.println("size: " + feeds.size());
		db.disconnect();

		request.setAttribute("articles", articles);
		request.setAttribute("feeds", feeds);
	}
	this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
	}
}
