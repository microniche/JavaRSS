package com.JavaRSS.servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Articles extends HttpServlet
{
	static final String INFOS_ATTRIBUTE = "infos";

	private void parseAndAddFeedInDatabase(Feed feed, User user, DatabaseRequester db, HttpServletResponse response) throws IOException
	{
		URL url;
		
		try {
			url = new URL(feed.getLink());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return;
		}
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
					//Date date = DateFormat.getInstance().parse(eElement.getElementsByTagName("pubDate").item(0).getTextContent());
					SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
					Date date = format.parse(eElement.getElementsByTagName("pubDate").item(0).getTextContent());
					Article article = new Article(-1, 
													feed.getId(), 
													user.getId(), 
													eElement.getElementsByTagName("title").item(0).getTextContent(),
													eElement.getElementsByTagName("link").item(0).getTextContent(), 
													date,
													eElement.getElementsByTagName("description").item(0).getTextContent(),
													new Date(),
													false, eElement.getElementsByTagName("guid").item(0).getTextContent());
					db.addArticle(feed.getId(),
							user.getId(),
							article.getTitle(),
							article.getUrl(),
							article.getPubDate(),
							article.getDescription(),
							article.getGuid());
				}
			}
			
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user = (User) request.getSession().getAttribute(INFOS_ATTRIBUTE);
		
		if (user != null)
		{
			DatabaseRequester db = new DatabaseRequester();
			db.connect();
			List<Feed> feeds = db.getFeeds(user.getId());

			for (int i = 0; i != feeds.size(); i++)
			{
				parseAndAddFeedInDatabase(feeds.get(i), user, db, response);
			}
			List<Article> list = null;
			String param = request.getParameter("lastUpdate");
			if (param != null)
			{
				Date lastUpdate = new Date(Long.parseLong(param));
				list = db.getArticlesFromLastUpdate(lastUpdate, user.getId());
			}
			else
			{
				list = db.getArticlesFromUser(user.getId());
			}
			XmlSerializer.writeTo(list, response.getOutputStream());
			db.disconnect();
		}
		else
		{
			response.getWriter().print("You are not logged in.");
		}
	}
}
