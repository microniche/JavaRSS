package com.JavaRSS.Interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.JavaRSS.Beans.User;

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

	public User getUserWithPassword(String mail, String password)
	{
		Connection connection = null;
		PreparedStatement statement = null;

		try
		{
			connection = DriverManager.getConnection(url, utilisateur, motDePasse);
			statement = connection.prepareStatement("SELECT * FROM users WHERE mail = ? AND pwd = MD5(?)");
			statement.setString(1, mail);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				User user = new User();
				user.setId(result.getInt("id"));
				user.setEmail(result.getString("mail"));
				result.close();
				return (user);
			} else
			{
				result.close();
				return (null);
			}
		} catch (SQLException e)
		{
			System.out.println("bug  getConnection ou prepareStatement : " + e.getMessage());
		} finally /*
					 * ce qui est mis ici sera fait même si le premier try exit,
					 * ou après un return, donc très très utile
					 */
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				} catch (SQLException ignore)
				{
					System.out.println("bug close statement");
				}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				} catch (SQLException ignore)
				{
					System.out.println("bug close connection");
				}
			}
		}
		return (null);
	}

	// result = statement.executeQuery( "INSERT INTO users id, mail, pwd FROM
	// users;" );
	// while ( result.next() ) {
	// int idUtilisateur = result.getInt( "id" );
	// String emailUtilisateur = result.getString( "mail" );
	// String motDePasseUtilisateur = result.getString( "pwd" );
	// messages.add( "Donn�es retourn�es par la requ�te : id = " +
	// idUtilisateur + ", email = " + emailUtilisateur
	// + ", motdepasse = "
	// + motDePasseUtilisateur);
	// }

	public List<String> addFeed(HttpServletRequest request, String url, String title, int user_id)
	{
		this.connect();

		try
		{
			statement = connexion.createStatement();
			int statut = statement.executeUpdate(
					"INSERT INTO feeds (url, title, user_id) VALUES (" + url + ", " + title + ", " + user_id + ");");
			messages.add("resultat de la requete" + statut);
			statement.close();
			statement = null;
		} catch (SQLException ignore)
		{
			System.out.println("Error INSERT");
		}

		return messages;
	}

	public List<String> toLogin(HttpServletRequest request)
	{
		this.connect();
		/* Cr�ation de l'objet g�rant les requ�tes */
		try
		{
			statement = connexion.createStatement();
			result = statement.executeQuery("SELECT id, mail, pwd FROM users;");
			while (result.next())
			{
				int idUtilisateur = result.getInt("id");
				String emailUtilisateur = result.getString("mail");
				String motDePasseUtilisateur = result.getString("pwd");
				messages.add("Donn�es retourn�es par la requ�te : id = " + idUtilisateur + ", email = "
						+ emailUtilisateur + ", motdepasse = " + motDePasseUtilisateur);
			}
			statement.close();
			result.close();
			statement = null;
			result = null;
		} catch (SQLException ignore)
		{
		}

		System.out.println("Return Messages");
		this.disconnect();
		return messages;
	}

}
