package com.JavaRSS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JavaRSS.Beans.Feed;
import com.JavaRSS.Beans.User;
import com.JavaRSS.Interfaces.DatabaseRequester;
import com.JavaRSS.Interfaces.XmlSerializer;

public class ArticleRead extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user;

		user = (User) request.getSession().getAttribute(Login.INFOS_ATTRIBUTE);
		if (user == null || request.getParameter("id") == null)
			return;
		int id = Integer.parseInt(request.getParameter("id"));
		DatabaseRequester db = new DatabaseRequester();
		db.connect();
		db.setRead(id, user.getId());
		db.disconnect();
	}
}
