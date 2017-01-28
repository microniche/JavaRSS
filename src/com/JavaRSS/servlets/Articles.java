package com.JavaRSS.servlets;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.JavaRSS.Beans.Article;
import com.JavaRSS.Beans.Feed;
import com.JavaRSS.Beans.User;
import com.JavaRSS.Interfaces.DatabaseRequester;
import com.JavaRSS.Interfaces.XmlSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Articles extends HttpServlet
{
	static final String INFOS_ATTRIBUTE = "infos";

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user;

		user = (User) request.getSession().getAttribute(INFOS_ATTRIBUTE);
		if (user != null)
		{
			DatabaseRequester db = new DatabaseRequester();
			db.connect();
			List<Feed> feeds = db.getFeeds(user.getId());

			for (int i = 0; i != feeds.size(); i++)
			{
				URL url = new URL(feeds.get(i).getLink());
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder;
				try
				{
					dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(url.openStream());
					doc.getDocumentElement().normalize();

					NodeList nList = doc.getElementsByTagName("item");

					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;
							
							db.addArticle(feeds.get(i).getId(),
									user.getId(),
									eElement.getElementsByTagName("link").item(0).getTextContent(),
									DateFormat.getInstance().parse(eElement.getElementsByTagName("pubDate").item(0).getTextContent()),
									eElement.getElementsByTagName("description").item(0).getTextContent(),
									eElement.getElementsByTagName("guid").item(0).getTextContent());
						}
					}
					
					
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					List<Article> list = db.getArticlesFromUser(user.getId());
					XmlSerializer.writeTo(list, response.getOutputStream());
				}

				
			}
			
		}
		else
		{
			response.getWriter().print("You are not logged in.");
		}
	}
}
