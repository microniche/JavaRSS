package com.JavaRSS.Interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.JavaRSS.Beans.Article;
import com.JavaRSS.Beans.Feed;
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
			e.printStackTrace();
		}

		try
		{
			;
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			System.out.println("Connexion itinitalize");
		} catch (SQLException e)
		{
			e.printStackTrace();
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
				ignore.printStackTrace();
			}
	}

	public boolean addUser(String mail, String password)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try
		{
			connection = DriverManager.getConnection(url, utilisateur, motDePasse);
			statement = connection.prepareStatement("INSERT INTO users (mail, pwd) VALUES(?, MD5(?))");
			statement.setString(1, mail);
			statement.setString(2,  password);
			statement.executeUpdate();
			return (true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally /* ce qui est mis ici sera fait même si le premier try exit, ou après un return, donc très très utile */
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
		}
		return (false);
	}
	
	public List<Article> getArticlesFromRss(int ownerRss, int ownerUser)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try
		{
			connection = DriverManager.getConnection(url, utilisateur, motDePasse);
			statement = connection.prepareStatement("SELECT * FROM article WHERE rss_id = ? AND user_id = ?");
			statement.setInt(1, ownerRss);
			statement.setInt(2, ownerUser);
			ResultSet result = statement.executeQuery();
			List<Article> list = new LinkedList<Article>();
			while (result.next())
			{
				Article article = new Article(result.getInt("id"), 
												result.getInt("rss_id"), 
												result.getInt("user_id"),
												result.getString("title"),
												result.getString("link"), 
												result.getDate("pubDate"), 
												result.getString("description"),
												result.getDate("dateAdded"),
												result.getBoolean("isRead"),
												result.getString("guid"));
				list.add(article);
			}
			result.close();
			return (list);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally /* ce qui est mis ici sera fait même si le premier try exit, ou après un return, donc très très utile */
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
		}
		return (null);
	}
	
	public List<Article> getArticlesFromLastUpdate(Date lastUpdate, int ownerUser)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try
		{
			connection = DriverManager.getConnection(url, utilisateur, motDePasse);
			statement = connection.prepareStatement("SELECT * FROM article WHERE dateAdded > ? AND user_id = ?");
			statement.setTimestamp(1, new Timestamp(lastUpdate.getTime()));
			statement.setInt(2, ownerUser);
			ResultSet result = statement.executeQuery();
			List<Article> list = new LinkedList<Article>();
			while (result.next())
			{
				Article article = new Article(result.getInt("id"), 
						result.getInt("rss_id"), 
						result.getInt("user_id"),
						result.getString("title"),
						result.getString("link"), 
						result.getDate("pubDate"), 
						result.getString("description"),
						result.getDate("dateAdded"),
						result.getBoolean("isRead"),
						result.getString("guid"));
				list.add(article);
			}
			result.close();
			return (list);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally /* ce qui est mis ici sera fait même si le premier try exit, ou après un return, donc très très utile */
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
		}
		return (null);
	}
	
	public List<Article> getArticlesFromUser(int ownerUser)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try
		{
			connection = DriverManager.getConnection(url, utilisateur, motDePasse);
			statement = connection.prepareStatement("SELECT * FROM article WHERE user_id = ?");
			statement.setInt(1, ownerUser);
			ResultSet result = statement.executeQuery();
			List<Article> list = new LinkedList<Article>();
			while (result.next())
			{
				Article article = new Article(result.getInt("id"), 
						result.getInt("rss_id"), 
						result.getInt("user_id"),
						result.getString("title"),
						result.getString("link"), 
						result.getDate("pubDate"), 
						result.getString("description"),
						result.getDate("dateAdded"),
						result.getBoolean("isRead"),
						result.getString("guid"));
				list.add(article);
			}
			result.close();
			return (list);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally /* ce qui est mis ici sera fait même si le premier try exit, ou après un return, donc très très utile */
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
		}
		return (null);
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
			}
			else
			{
				result.close();
				return (null);
			}
		}
		catch (SQLException e)
		{
			System.out.println("bug  getConnection ou prepareStatement : " + e.getMessage());
		}
		finally /* ce qui est mis ici sera fait même si le premier try exit, ou après un return, donc très très utile */
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ignore)
				{
					ignore.printStackTrace();
				}
			}
		}
		return (null);
	}
public List<String> addFeed(HttpServletRequest request, String url, String title, int user_id)
	{
		this.connect();
		
		try
		{
			PreparedStatement statement = connexion.prepareStatement("INSERT INTO feeds (url, title, user_id, lastBuildDate) VALUES (?, ?, ?, NULL);");
			statement.setString(1, url);
			statement.setString(2, title);
			statement.setInt(3, user_id);
			int statut = statement.executeUpdate();
			messages.add("resultat de la requete" + statut);
			statement.close();
			this.disconnect();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return messages;
	}

	public List<String> deleteFeed(HttpServletRequest request, int feed_id)
	{
		try
		{
			statement = connexion.createStatement();
			int statut = statement.executeUpdate("Delete From feeds where id=" + feed_id + ";");
			statement.close();
			statement = null;
		} catch (SQLException ignore)
		{
			ignore.printStackTrace();
		}
		return messages;
	}
	
	public void deleteArticlesFromFeed(int owner_id, int feed_id)
	{
		try
		{
			PreparedStatement statement = connexion.prepareStatement("DELETE FROM article WHERE rss_id = ? AND user_id = ?");
			statement.setInt(1, feed_id);
			statement.setInt(2, owner_id);
			statement.executeUpdate();
			statement.close();
			statement = null;
		} catch (SQLException ignore)
		{
			ignore.printStackTrace();
		}
	}
	
	public List<Feed> getFeeds(int ownerUser)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try
		{
			connection = DriverManager.getConnection(url, utilisateur, motDePasse);
			statement = connection.prepareStatement("SELECT * FROM feeds WHERE user_id = ?");
			statement.setInt(1, ownerUser);
			ResultSet result = statement.executeQuery();
			List<Feed> list = new LinkedList<Feed>();
			while (result.next())
			{
				Feed feed = new Feed(result.getInt("id"),
										ownerUser,
										result.getString("url"),
										result.getString("title"),
										result.getDate("lastBuildDate"));
				list.add(feed);
			}
			result.close();
			return (list);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally /* ce qui est mis ici sera fait même si le premier try exit, ou après un return, donc très très utile */
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (SQLException ignore)
				{
					System.out.println("bug close statement");
				}
			}
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException ignore)
				{
					System.out.println("bug close connection");
				}
			}
		}
		return (null);
	}
	
	public List<String> toLogin(HttpServletRequest request)
	{
		this.connect();
		/* Cr�ation de l'objet g�rant les requ�tes */
		try
		{
			statement = connexion.createStatement();
			result = statement.executeQuery( "SELECT id, mail, pwd FROM users;" );			
		      while ( result.next() ) {
		            int idUtilisateur = result.getInt( "id" );
		            String emailUtilisateur = result.getString( "mail" );
		            String motDePasseUtilisateur = result.getString( "pwd" );
		            messages.add( "Donn�es retourn�es par la requ�te : id = " + idUtilisateur + ", email = " + emailUtilisateur
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
	public int addArticle(int rss_id, int user_id, String title, String link, Date pubDate, String description, String guid)
	{
		this.connect();
		//int id = -1;
		try
		{
			PreparedStatement statement = connexion.prepareStatement("INSERT INTO article (rss_id, user_id, title, link, pubDate, description, dateAdded, isRead, guid) VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, ?);");
			statement.setInt(1, rss_id);
			statement.setInt(2, user_id);
			statement.setString(3, title);
			statement.setString(4, link);
			statement.setTimestamp(5, new Timestamp(pubDate.getTime()));
			statement.setString(6, description);
			statement.setBoolean(7, false);
			statement.setString(8, guid);
			int statut = statement.executeUpdate();
			/*ResultSet keys = statement.getGeneratedKeys();
			if (statut == 1 && keys.next())
				id = keys.getInt(1);*/
			statement.close();
			this.disconnect();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		//System.out.println("addArticle: generated Id: " + id);
		return (0);
	}
	public void setRead(int article_id, int user_id)
	{
		try
		{
			PreparedStatement statement = connexion.prepareStatement("UPDATE article SET isRead = true WHERE id = ? AND user_id = ?;");
			statement.setInt(1, article_id);
			statement.setInt(2, user_id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
