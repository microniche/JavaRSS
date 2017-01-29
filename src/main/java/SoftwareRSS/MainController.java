package SoftwareRSS;

import com.JavaRSS.Beans.Article;
import com.JavaRSS.Beans.DataState;
import com.JavaRSS.Beans.Feed;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Aurélien on 08/01/2017.
 */
public class MainController
{
    @FXML
    private Label logLabel;

    @FXML
    private Button logButton;

    @FXML
    private ListView articlesView;

    @FXML
    private ListView rssFeedsView;

    @FXML
    private TextField rssField;

    private VBox layout = null;

    private DataState dataState = null;
    private ObservableList<Feed> feedsList = null;
    private ObservableList<Article> articlesList = null;

    static private final String rssUrl = "http://localhost:8080/ServeurJavaRSS/feeds";
    static private final String articleUrl = "http://localhost:8080/ServeurJavaRSS/articles";
    static private final String articleReadUrl = "http://localhost:8080/ServeurJavaRSS/articleread";

    public MainController()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainWindow.fxml"));
        loader.setController(this);
        try
        {
            layout = loader.load();

            articlesView.setCellFactory(new Callback<ListView<Article>, ListCell>() {
                @Override
                public ListCell call(ListView param) {
                    return new CustomArticleListCell();
                }
            });
            rssFeedsView.setCellFactory(new Callback<ListView<Feed>, ListCell>() {
                @Override
                public ListCell call(ListView param) {
                    return new CustomRssFeedListCell();
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updateArticles()
    {
        List<Article> articles = Utils.deserializeArticlesList(HttpHandler.sendGetInputVersion(articleUrl));
        System.out.println("display: " + articles.size());
        articlesList = FXCollections.observableArrayList(articles);
        articlesView.setItems(articlesList);
    }


    public void updateRssFeeds()
    {
        //System.out.print(HttpHandler.sendGet(rssUrl));
        List<Feed> feeds = Utils.deserializeFeedsList(HttpHandler.sendGetInputVersion(rssUrl));
        System.out.println("display: " + feeds.size());
        feedsList = FXCollections.observableArrayList(feeds);
        rssFeedsView.setItems(feedsList);
    }

    public void removeOneFeed(Feed feed)
    {
        feedsList.remove(feed);
        dataState.getFeeds().remove(feed);
        System.out.println("remove feed, remaining articles before: " + dataState.getArticles().size());
        DataState.removeArticlesFromDeadFeeds(dataState.getArticles(), dataState.getFeeds());
        articlesList = FXCollections.observableArrayList(dataState.getArticles());
        System.out.println("remove feed, remaining articles: " + articlesList.size());
        articlesView.setItems(articlesList);
    }

    @FXML
    private void loginOrLogout()
    {
        if (HttpHandler.cookiesToProvide == null)
            LoginController.showLoginWindow();
        else
        {
            LoginController.sendLogoutRequest();
            HttpHandler.cookiesToProvide = null;
            updateLogTexts("You are not logged in", "Login");
            saveDataState();
        }
    }

    public void updateLogTexts(String labelText, String buttonText)
    {
        logLabel.setText(labelText);
        logButton.setText(buttonText);
    }

    public ObservableList<Feed> getFeedList()
    {
        return (feedsList);
    }

    @FXML
    private void addRss()
    {
        if (HttpHandler.cookiesToProvide == null)
            System.out.println("You are not logged");
        else
        {
            HttpHandler.sendPost(rssUrl, "url", rssField.getText());
            updateDisplay();
        }
        System.out.println(rssField.getText());
    }

    private void sortArticles()
    {
        Comparator<Article> comp = Comparator.comparingLong(Article::getPubDateValue).reversed();
        articlesList.sort(comp);
    }
    public void updateDisplay()
    {
        dataState.setFeeds(Utils.deserializeFeedsList(HttpHandler.sendGetInputVersion(rssUrl)));
        Date date = dataState.getLastUpdate();
        List<Article> recentsArticles;
        if (date != null){
            System.out.println("date not null");
            recentsArticles = Utils.deserializeArticlesList(HttpHandler.sendGetInputVersion(articleUrl, "lastUpdate", String.valueOf(date.getTime())));
            System.out.println("received: " + recentsArticles.size());
        }
        else {
            System.out.println("date null");
            recentsArticles = Utils.deserializeArticlesList(HttpHandler.sendGetInputVersion(articleUrl));
            System.out.println("received: " + recentsArticles.size());
        }
        List<Article> articles = dataState.getArticles();
        articles.addAll(recentsArticles);
        dataState.setArticles(articles);
        feedsList = FXCollections.observableArrayList(dataState.getFeeds());
        articlesList = FXCollections.observableArrayList(articles);
        sortArticles();
        articlesView.setItems(articlesList);
        rssFeedsView.setItems(feedsList);
        System.out.println("articles: " + articlesList.size() + " feeds: " + feedsList.size());
    }
    public void initOnline()
    {
        DataState dataState = DataState.getDatas();
        if (dataState != null) {
            System.out.println("datastate not null: " + dataState.toString());
            List<Feed> feeds = Utils.deserializeFeedsList(HttpHandler.sendGetInputVersion(rssUrl));
            List<Article> articles = dataState.getArticles();
            DataState.removeArticlesFromDeadFeeds(articles, feeds);
            Date date = dataState.getLastUpdate();
            List<Article> recentsArticles;
            dataState.setLastUpdate(new Date());
            if (date != null)
                recentsArticles = Utils.deserializeArticlesList(HttpHandler.sendGetInputVersion(articleUrl, "lastUpdate", String.valueOf(date.getTime())));
            else
                recentsArticles = Utils.deserializeArticlesList(HttpHandler.sendGetInputVersion(articleUrl));
            System.out.println("received: " + recentsArticles.size());
            articles.addAll(recentsArticles);
            dataState.setArticles(articles);
            this.dataState = dataState;
            feedsList = FXCollections.observableArrayList(feeds);
            articlesList = FXCollections.observableArrayList(articles);
        }
        else
        {
            List<Feed> feeds = Utils.deserializeFeedsList(HttpHandler.sendGetInputVersion(rssUrl));
            List<Article> articles = Utils.deserializeArticlesList(HttpHandler.sendGetInputVersion(articleUrl));
            this.dataState = new DataState(articles, feeds, new Date());
            feedsList = FXCollections.observableArrayList(feeds);
            articlesList = FXCollections.observableArrayList(articles);
        }
        sortArticles();
        articlesView.setItems(articlesList);
        rssFeedsView.setItems(feedsList);
        System.out.println("articles: " + articlesList.size() + " feeds: " + feedsList.size());
    }
    public void saveDataState()
    {
        DataState.saveDatas(dataState);
    }
    public VBox getLayout()
    {
        return (layout);
    }
}
