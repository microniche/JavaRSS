package com.JavaRSS.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JavaRSS.Beans.User;
import com.JavaRSS.Interfaces.DatabaseRequester;

public class Login extends HttpServlet {
	static final public String INFOS_ATTRIBUTE = "infos";
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		DatabaseRequester db = new DatabaseRequester();
		db.connect();
		User user = db.getUserWithPassword(mail, password);
		db.disconnect();
		
		if (user != null)
		{
			request.getSession().setAttribute(INFOS_ATTRIBUTE, user);
			response.getWriter().print("ok");
		}
		else
			response.getWriter().print("nok");		
	}
}
