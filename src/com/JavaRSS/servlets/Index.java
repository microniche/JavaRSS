package com.JavaRSS.servlets;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.JavaRSS.Beans.Article;
import com.JavaRSS.Interfaces.DatabaseRequester;

public class Index extends HttpServlet {
	public static final String ATT_MESSAGES = "messages";
			
	private void serialization(OutputStream out) throws IOException
	{
		Article article = new Article(1, 1, 1, "google.fr", new Date(), "un test", new Date(), false);
		Article article2 = new Article(1, 2, 3, "dtc.fr", new Date(), "deux shibal", new Date(), false);
		List<Article> list = new LinkedList<Article>();
		list.add(article);
		list.add(article2);
		
		XMLEncoder encoder = null;
		 
		try 
		{
			encoder = new XMLEncoder(new BufferedOutputStream(out));
			encoder.writeObject(list);
			encoder.flush();
		} 
		finally
		{
			if (encoder != null)
			{
				encoder.close();
			}
		}
	}
public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
	serialization(response.getOutputStream());
	/*URL url = new URL("http://www.01net.com/rss/info/flux-rss/flux-toutes-les-actualites/");
	List<String> messages = new ArrayList<String>();
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

		messages.add("Flux RSS Added from url");
		messages.add("Title :" + element.getElementsByTagName("title").item(0).getTextContent());
		System.out.println("\nCurrent Element : " + node.getNodeName());
		//messages = dbr.addFeed(request);

		NodeList nList = doc.getElementsByTagName("item");

		
	} catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
    //request.setAttribute( ATT_MESSAGES, messages );
    for (int i = 0; i < messages.size(); i++) {
    	response.getWriter().println(messages.get(i));
    }*/
	}
}
