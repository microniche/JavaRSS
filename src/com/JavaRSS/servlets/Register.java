package com.JavaRSS.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JavaRSS.Interfaces.DatabaseRequester;

public class Register extends HttpServlet {
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		DatabaseRequester db = new DatabaseRequester();
		db.connect();
		String mail = request.getParameter("mail");
		String pwd = request.getParameter("pwd");
		db.addUser(mail, pwd);
		db.disconnect();
	}
}
