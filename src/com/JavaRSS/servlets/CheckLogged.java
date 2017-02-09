package com.JavaRSS.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JavaRSS.Beans.User;

public class CheckLogged extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		User user = (User) request.getSession().getAttribute(Login.INFOS_ATTRIBUTE);
		if (user == null)
			response.getWriter().print("You are not logged in.");
		else
			response.getWriter().print("You are logged in as " + user.getEmail());
		if (request.getCookies() == null)
			response.getWriter().print("no cookies");
		else
			for (Cookie cookie : request.getCookies())
				response.getWriter().print("cookie: " + cookie.getName() + cookie.getValue());
	}
}
