package com.JavaRSS.servlets;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.JavaRSS.Beans.User;
import com.JavaRSS.Interfaces.DatabaseRequester;

public class Feeds extends HttpServlet
{

	static final String INFOS_ATTRIBUTE = "infos";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user;

		user = (User) request.getSession().getAttribute(INFOS_ATTRIBUTE);
		if (user != null)
		{

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user;

		user = (User) request.getSession().getAttribute(INFOS_ATTRIBUTE);
		if (user != null)
		{
			List<String> messages = new ArrayList<String>();
			String url_str = request.getParameter("url");
			String title;
			URL url = new URL(url_str);
			DatabaseRequester dbr = new DatabaseRequester();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			try
			{
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(url.openStream());
				doc.getDocumentElement().normalize();

				NodeList channel = doc.getElementsByTagName("channel");
				Node node = channel.item(0);
				Element element = (Element) node;

				title = element.getElementsByTagName("title").item(0).getTextContent();
				messages = dbr.addFeed(request, url_str, title, user.getId());
				
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		else
		{
			response.getWriter().print("You are not logged in.");
		}
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user;

		user = (User) request.getSession().getAttribute(INFOS_ATTRIBUTE);
		if (user != null)
		{
			int feed_id = Integer.parseInt(request.getParameter("feed_id"));
			DatabaseRequester dbr = new DatabaseRequester();
			dbr.deleteFeed(request, feed_id);
			
		}
		else
		{
			response.getWriter().print("You are not logged in.");
		}
	}
}