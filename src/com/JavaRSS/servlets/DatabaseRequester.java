package com.JavaRSS.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DatabaseRequester
{
	private Connection connexion = null;
	private ResultSet result = null;
	private Statement statement = null;
	private String url = "jdbc:mysql://localhost:3306/bdd_rss";
	private String utilisateur = "root";
	private String motDePasse = "root";
	private List<String> messages = new ArrayList<String>();

	public void connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
		}

		try
		{
			;
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			System.out.println("Connexion itinitalize");
		} catch (SQLException e)
		{
			System.out.println("Connexion failure");
		}
	}

	public void disconnect()
	{
		if (connexion != null)
			try
			{
				connexion.close();
				connexion = null;
			} catch (SQLException ignore)
			{
			}
	}

	
	public List<String> addFeed(HttpServletRequest request)
	{
		this.connect();
		
		try
		{
			statement = connexion.createStatement();
			int statut = statement.executeUpdate("INSERT INTO feeds (url, user_id) VALUES ('http://www.01net.com/rss/info/flux-rss/flux-toutes-les-actualites/', 4242)");
//			result = statement.executeQuery( "INSERT INTO users id, mail, pwd FROM users;" );			
//		      while ( result.next() ) {
//		            int idUtilisateur = result.getInt( "id" );
//		            String emailUtilisateur = result.getString( "mail" );
//		            String motDePasseUtilisateur = result.getString( "pwd" );
//		            messages.add( "Données retournées par la requête : id = " + idUtilisateur + ", email = " + emailUtilisateur
//		                    + ", motdepasse = "
//		                    + motDePasseUtilisateur);
//		      }
			messages.add("resultat de la requete" + statut);
		      statement.close();
		      statement = null;
		}
		catch ( SQLException ignore ) {
			System.out.println("Error INSERT");
        }

		
		return messages;
	}
	
	
	public List<String> toLogin(HttpServletRequest request)
	{
		this.connect();
		/* Création de l'objet gérant les requêtes */
		try
		{
			statement = connexion.createStatement();
			result = statement.executeQuery( "SELECT id, mail, pwd FROM users;" );			
		      while ( result.next() ) {
		            int idUtilisateur = result.getInt( "id" );
		            String emailUtilisateur = result.getString( "mail" );
		            String motDePasseUtilisateur = result.getString( "pwd" );
		            messages.add( "Données retournées par la requête : id = " + idUtilisateur + ", email = " + emailUtilisateur
		                    + ", motdepasse = "
		                    + motDePasseUtilisateur);
		      }
		      statement.close();
		      result.close();
		      statement = null;
		      result = null;
		}
		catch ( SQLException ignore ) {
        }

		System.out.println("Return Messages");
		this.disconnect();
		return messages;
	}

}
