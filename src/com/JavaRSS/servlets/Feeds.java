package com.JavaRSS.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Feeds extends HttpServlet {
public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//renvoie les flux rss
	}
public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		// permet d'ajouter un flux rss
	}
public void doDelete( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		// permet de supprimer un flux
	}
}
