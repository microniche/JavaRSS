package com.JavaRSS.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends HttpServlet {
	public static final String ATT_MESSAGES = "messages";
			
public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
	DatabaseRequester dbr = new DatabaseRequester();
	List<String> messages;
	messages = dbr.toLogin(request);
	
    request.setAttribute( ATT_MESSAGES, messages );
    for (int i = 0; i < messages.size(); i++) {
    	response.getWriter().println(messages.get(i));
    }
	}
}
